package nl.fontys.s3.todolistbackend.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTodoRequest {
    @NotBlank
    private String title;
}
