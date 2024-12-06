package com.example.booksApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    public ObjectId id;
    public String name;
    public Integer pagesCount;

//    public ObjectId getId() {
//        return id;
//    }

    @JsonProperty("id")
    public String getId() {
        return id != null ? id.toHexString() : null; // Convert ObjectId to its string representation
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }
}
