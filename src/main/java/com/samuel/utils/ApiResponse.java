package com.samuel.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    String message;
    T payload;
}
