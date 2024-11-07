package nl.fontys.s3.todolistbackend.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.todolistbackend.business.CreateTodoUseCase;
import nl.fontys.s3.todolistbackend.business.exception.TodoAlreadyExistsException;
import nl.fontys.s3.todolistbackend.domain.CreateTodoRequest;
import nl.fontys.s3.todolistbackend.domain.CreateTodoResponse;
import nl.fontys.s3.todolistbackend.persistence.TodoRepository;
import nl.fontys.s3.todolistbackend.persistence.entity.TodoEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTodoUseCaseImpl implements CreateTodoUseCase {

    private final TodoRepository todoRepository;

    @Override
    public CreateTodoResponse createTodoItem(CreateTodoRequest request) {
        if (todoRepository.existsByTitle(request.getTitle())) {
            throw new TodoAlreadyExistsException();
        }

        TodoEntity todoEntity = TodoEntity.builder()
                .title(request.getTitle())
                .done(false)
                .build();
        TodoEntity savedEntity = todoRepository.save(todoEntity);

        return CreateTodoResponse.builder().id(savedEntity.getId()).build();
    }
}
