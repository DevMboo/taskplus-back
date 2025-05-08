package com.taskplus_back.service;

import com.taskplus_back.component.JwtTokenProvider;
import com.taskplus_back.dto.LoginDTO;
import com.taskplus_back.dto.LoginResponseDTO;
import com.taskplus_back.entity.User;
import com.taskplus_back.exception.BusinessException;
import com.taskplus_back.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository
            , PasswordEncoder passwordEncoder
            , JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        User user  = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BusinessException("E-mail ou senha estão inválidos"));

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("E-mail ou senha estão inválidos");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new LoginResponseDTO(token);

    }
}
