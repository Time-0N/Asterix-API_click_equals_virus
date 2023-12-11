package com.example.asterixapi;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("character")
@With
public record Asterix(
        String _id,
        String name,
        int age,
        String status
) {
}
