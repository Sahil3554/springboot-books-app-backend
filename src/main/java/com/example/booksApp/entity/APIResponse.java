package com.example.booksApp.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class APIResponse<T> {
    private String message;
    private String error;
    private T data;

}
