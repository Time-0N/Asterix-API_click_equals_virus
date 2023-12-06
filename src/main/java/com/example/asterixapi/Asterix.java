package com.example.asterixapi;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Product(
        String id,
        String title,
        int price
) {
}
