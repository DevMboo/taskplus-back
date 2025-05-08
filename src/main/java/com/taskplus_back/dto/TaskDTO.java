package com.taskplus_back.dto;

import com.taskplus_back.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class TaskDTO {
    @NotBlank(message = "Campo titulo é obrigatório")
    private String title;

    @NotBlank(message = "Campo descrição é obrigatório")
    private String description;

    @NotBlank(message = "Campo data limite é obrigatório")
    private LocalDate dueDate;

    private StatusTask status;

    @NotBlank(message = "O time precisa ser definido")
    private Long teamId;

    @NotBlank(message = "Campo responsável é obrigatório")
    private Long responsibleId;
}