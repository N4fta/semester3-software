package nl.fontys.s3.todolistbackend.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.todolistbackend.business.GetTodosUseCase;
import nl.fontys.s3.todolistbackend.domain.GetTodosResponse;
import nl.fontys.s3.todolistbackend.domain.Todo;
import nl.fontys.s3.todolistbackend.persistence.TodoRepository;
import nl.fontys.s3.todolistbackend.persistence.entity.TodoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTodosUseCaseImpl implements GetTodosUseCase {
    private final TodoRepository todoRepository;

    @Override
    public GetTodosResponse getTodos() {
        List<Todo> todoList = todoRepository
                .findAll()
                .stream()
                .map(this::convertToDomain)
                .toList();
        return GetTodosResponse.builder().todos(todoList).build();
    }

    private Todo convertToDomain(TodoEntity todoEntity) {
        return Todo.builder().title(todoEntity.getTitle()).id(todoEntity.getId()).done(todoEntity.getDone()).build();
    }
}
