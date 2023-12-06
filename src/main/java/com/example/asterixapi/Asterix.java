package com.example.asterixapi;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("character")
public record Asterix(
   String name,
   int age,
   String status
) {
}
