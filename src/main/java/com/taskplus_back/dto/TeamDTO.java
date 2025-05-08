package com.taskplus_back.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

}
