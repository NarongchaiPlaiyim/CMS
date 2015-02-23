package com.cms.utils;

import lombok.Getter;

@Getter
public enum Type {
    TEACHER("Teacher", 0),
    STUDENT("Student", 1);

    private String text;
    private int value;

    private Type(String text, int value) {
        this.text = text;
        this.value = value;
    }
}
