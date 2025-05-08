package com.taskplus_back.dto;

import com.taskplus_back.enums.ProfileUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private ProfileUser perfil;
    private Long teamId;
}