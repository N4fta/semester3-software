package nl.fontys.s3.todolistbackend.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Todo {
    private Long id;
    private String title;
    private Boolean done;
}
