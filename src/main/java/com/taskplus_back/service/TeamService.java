package com.taskplus_back.service;

import com.taskplus_back.dto.TeamDTO;
import com.taskplus_back.entity.Team;
import com.taskplus_back.exception.BusinessException;
import com.taskplus_back.exception.EntityNotFoundException;
import com.taskplus_back.repository.TeamRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + id));
    }

    public Team save(TeamDTO teamDTO) {
        validateTeamData(teamDTO);

        Team team = convertToEntity(teamDTO);

        try {
            return teamRepository.save(team);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Já existe um time com este nome");
        }
    }

    public Team update(Long id, TeamDTO teamDTO) {
        validateTeamData(teamDTO);

        Team existing = getTeamOrThrow(id);
        updateTeamFields(existing, teamDTO);

        try {
            return teamRepository.save(existing);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Já existe um time com este nome");
        }
    }

    public void deleteTeam(Long id) {
        Team existing = getTeamOrThrow(id);
        try {
            teamRepository.delete(existing);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Não é possível excluir o time pois existem membros associados");
        }
    }

    private Team getTeamOrThrow(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + id));
    }

    private void updateTeamFields(Team existing, TeamDTO teamDTO) {
        if (teamDTO.getName() != null && !teamDTO.getName().trim().isEmpty()) {
            existing.setName(teamDTO.getName().trim());
        }
    }

    private Team convertToEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName().trim());
        return team;
    }

    private void validateTeamData(TeamDTO teamDTO) {
        if (teamDTO.getName() == null || teamDTO.getName().trim().isEmpty()) {
            throw new BusinessException("O nome do time não pode ser vazio");
        }
    }
}