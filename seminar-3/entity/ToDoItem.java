package entity;

import java.time.Instant;

public record ToDoItem(
        Long id,
        String name,
        Instant createdAt,
        Boolean done
) {}
