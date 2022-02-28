package com.nuwit.schedule.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private String name;

    public Student() {
        // Jackson deserialization
    }

    public Student(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
