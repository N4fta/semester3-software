package nl.fontys.s3.todolistbackend.persistence;

import nl.fontys.s3.todolistbackend.persistence.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    boolean existsByTitle(String title);
}
