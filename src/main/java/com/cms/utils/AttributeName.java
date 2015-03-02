package com.cms.utils;

import lombok.Getter;

@Getter
public enum AttributeName {
    USER_DETAIL("userDetail"),
    TEACHER("teacher"),
    STUDENT_SUBJECT("studentSubject"),
    AUTHORIZE("authorize");
    private String name;
    private AttributeName(String name) {
        this.name = name;
    }
}
