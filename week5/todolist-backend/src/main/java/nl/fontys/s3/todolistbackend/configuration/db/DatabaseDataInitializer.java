package nl.fontys.s3.todolistbackend.configuration.db;

import lombok.AllArgsConstructor;
import nl.fontys.s3.todolistbackend.persistence.TodoRepository;
import nl.fontys.s3.todolistbackend.persistence.entity.TodoEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private TodoRepository todoRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (todoRepository.count() == 0) {
            todoRepository.save(TodoEntity.builder().title("Plan vacations").done(false).build());
            todoRepository.save(TodoEntity.builder().title("Clean kitchen").done(false).build());
            todoRepository.save(TodoEntity.builder().title("Study React").done(false).build());
            todoRepository.save(TodoEntity.builder().title("Shopping groceries").done(false).build());
        }
    }
}
