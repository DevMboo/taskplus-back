package com.taskplus_back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskplus_back.enums.ProfileUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo email é obrigatório")
    private String email;

    @NotBlank(message = "Campo senha é obrigatório")
    private String password;

    @NotNull(message = "O time do usuário é obrigatório")
    private Long teamId;

    private ProfileUser perfil;
}
