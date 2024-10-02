package nl.fontys.s3.todolistbackend.business;

import nl.fontys.s3.todolistbackend.domain.GetTodosResponse;

public interface GetTodosUseCase {
    GetTodosResponse getTodos();
}
