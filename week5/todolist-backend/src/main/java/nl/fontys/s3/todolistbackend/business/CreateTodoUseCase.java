package nl.fontys.s3.todolistbackend.business;

import nl.fontys.s3.todolistbackend.domain.CreateTodoRequest;
import nl.fontys.s3.todolistbackend.domain.CreateTodoResponse;

public interface CreateTodoUseCase {
    CreateTodoResponse createTodoItem(CreateTodoRequest request);
}
