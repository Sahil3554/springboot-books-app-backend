package com.example.booksApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class Book {
    @Id
    @Getter(AccessLevel.NONE)
    public ObjectId id;
    public String name;
    public Integer pagesCount;

    @JsonProperty("id")
    public String getId() {
        return id != null ? id.toHexString() : null; // Convert ObjectId to its string representation
    }

}
