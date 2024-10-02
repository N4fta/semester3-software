package nl.fontys.s3.todolistbackend.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTodoResponse {
    private Long id;
}
