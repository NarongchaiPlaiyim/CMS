package com.cms.utils;

import lombok.Getter;

@Getter
public enum AttributeName {
    USER_DETAIL("userDetail"),
    TEACHER_ID("teacherId"),
    STUDENT_SUBJECT("studentSubject"),
    AUTHORIZE("authorize");
    private String name;
    private AttributeName(String name) {
        this.name = name;
    }
}
