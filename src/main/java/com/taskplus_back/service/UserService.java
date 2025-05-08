package com.taskplus_back.service;

import com.taskplus_back.dto.UserDTO;
import com.taskplus_back.entity.Team;
import com.taskplus_back.entity.User;
import com.taskplus_back.enums.ProfileUser;
import com.taskplus_back.exception.BusinessException;
import com.taskplus_back.exception.EntityNotFoundException;
import com.taskplus_back.repository.TeamRepository;
import com.taskplus_back.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, TeamRepository teamRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + id));
    }

    public User save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = converToEntity(userDTO);
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO userDTO) {
        User existing = getUserOrThrow(id);
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            userDTO.setPassword(existing.getPassword());
        }
        updateUserFields(existing, userDTO);
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        User existing = getUserOrThrow(id);
        userRepository.delete(existing);
    }

    public User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + id));
    }

    public void updateUserFields(User existing, UserDTO userDTO) {
        existing.setName(userDTO.getName());
        existing.setEmail(userDTO.getEmail());
        existing.setPassword(userDTO.getPassword());
        if(userDTO.getPerfil() != null) {
            existing.setPerfil(userDTO.getPerfil());
        }
        existing.setTeam(teamRepository.findById(userDTO.getTeamId()).orElseThrow(()
                -> new BusinessException("Time não encontrado")));
    }

    public User converToEntity(UserDTO userDTO) {
        return User.builder()
                    .name(userDTO.getName())
                        .email(userDTO.getEmail())
                            .password(userDTO.getPassword())
                                .perfil(userDTO.getPerfil() != null ? userDTO.getPerfil() : ProfileUser.COLABORADOR)
                                    .team(teamRepository.findById(userDTO.getTeamId()).orElseThrow(()
                                            -> new BusinessException("Time não encontrado com o ID: " + userDTO.getTeamId())))
                .build();
    }
}
