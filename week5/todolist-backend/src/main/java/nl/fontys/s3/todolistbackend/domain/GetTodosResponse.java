package nl.fontys.s3.todolistbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetTodosResponse {
    private List<Todo> todos;
}
