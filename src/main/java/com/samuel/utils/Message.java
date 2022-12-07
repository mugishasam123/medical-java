package com.samuel.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Message<T> {
    String message;
    T payload;
}
