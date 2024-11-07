package nl.fontys.s3.todolistbackend.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.todolistbackend.business.CreateTodoUseCase;
import nl.fontys.s3.todolistbackend.business.GetTodosUseCase;
import nl.fontys.s3.todolistbackend.domain.CreateTodoRequest;
import nl.fontys.s3.todolistbackend.domain.CreateTodoResponse;
import nl.fontys.s3.todolistbackend.domain.GetTodosResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class ToDoController {
    private GetTodosUseCase getTodosUseCase;
    private CreateTodoUseCase createTodoUseCase;

    @GetMapping()
    public ResponseEntity<GetTodosResponse> getTodos() {
        GetTodosResponse response = getTodosUseCase.getTodos();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        CreateTodoResponse response = createTodoUseCase.createTodoItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
