package com.abc.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WrapService {
    private String before;
    private String after;

    public String wrap(String word) {
        return before + word + after;
    }
}
