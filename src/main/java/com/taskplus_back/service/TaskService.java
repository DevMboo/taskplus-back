package com.taskplus_back.service;

import com.taskplus_back.component.JwtTokenProvider;
import com.taskplus_back.dto.TaskDTO;
import com.taskplus_back.entity.Task;
import com.taskplus_back.entity.User;
import com.taskplus_back.enums.StatusTask;
import com.taskplus_back.exception.BusinessException;
import com.taskplus_back.exception.UnauthorizedException;
import com.taskplus_back.repository.TaskRepository;
import com.taskplus_back.repository.UserRepository;
import com.taskplus_back.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       JwtTokenProvider jwtTokenProvider) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private User getUsuarioFromToken(String token) {
        try {
            String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UnauthorizedException("Credenciais inválidas ou usuário não encontrado"));
        } catch (Exception e) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }
    }

    public Task createTask(TaskDTO taskDTO, String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        User responsavel = getUserOrThrow(taskDTO.getResponsibleId());

        if (!responsavel.getTeam().getId().equals(usuarioLogado.getTeam().getId())) {
            throw new BusinessException("O responsável deve pertencer à mesma equipe",
                    Map.of(
                            "current_team_id", usuarioLogado.getTeam().getId(),
                            "responsible_team_id", responsavel.getTeam().getId()
                    ));
        }

        Task task = convertToEntity(taskDTO);
        task.setTeam(usuarioLogado.getTeam());
        task.setStatus(StatusTask.PENDENTE);

        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, TaskDTO taskDTO, String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        Task existing = getTaskFromUserTeamOrThrow(taskId, usuarioLogado);

        if (taskDTO.getResponsibleId() != null) {
            User novoResponsavel = getUserOrThrow(taskDTO.getResponsibleId());
            if (!novoResponsavel.getTeam().getId().equals(usuarioLogado.getTeam().getId())) {
                throw new BusinessException("O novo responsável deve pertencer à mesma equipe");
            }
            existing.setResponsibleId(novoResponsavel);
        }

        updateTaskFields(existing, taskDTO);
        return taskRepository.save(existing);
    }

    public List<Task> findAllTasksFromUserTeam(String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        return taskRepository.findByTeamId(usuarioLogado.getTeam().getId());
    }

    public List<Task> findTasksByStatusFromUserTeam(StatusTask status, String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        return taskRepository.findByTeamIdAndStatus(usuarioLogado.getTeam().getId(), status);
    }

    public Task changeTaskStatus(Long taskId, StatusTask novoStatus, String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        Task task = getTaskFromUserTeamOrThrow(taskId, usuarioLogado);
        task.setStatus(novoStatus);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, String token) {
        User usuarioLogado = getUsuarioFromToken(token);
        Task task = getTaskFromUserTeamOrThrow(taskId, usuarioLogado);
        taskRepository.delete(task);
    }

    private Task getTaskFromUserTeamOrThrow(Long taskId, User usuarioLogado) {
        return taskRepository.findByIdAndTeamId(taskId, usuarioLogado.getTeam().getId())
                .orElseThrow(() -> new BusinessException("Tarefa não encontrada ou você não tem permissão para acessá-la"));
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com ID: " + userId));
    }

    private void updateTaskFields(Task existing, TaskDTO taskDTO) {
        if (taskDTO.getTitle() != null) {
            existing.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            existing.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getDueDate() != null) {
            existing.setDueDate(taskDTO.getDueDate());
        }
        if (taskDTO.getStatus() != null) {
            existing.setStatus(taskDTO.getStatus());
        }
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .dueDate(taskDTO.getDueDate())
                .responsibleId(getUserOrThrow(taskDTO.getResponsibleId()))
                .build();
    }
}