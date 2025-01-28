package com.fontys.nameless_backend.Persistence.entities.layout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "boxLists")
public class BoxListEntity {
    private int id;
    private List<BoxEntity> boxes;
}
