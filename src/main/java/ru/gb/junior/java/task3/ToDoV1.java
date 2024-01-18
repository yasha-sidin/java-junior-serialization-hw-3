package ru.gb.junior.java.task3;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ToDoV1 implements Serializable {
    private String title;
    private boolean isDone = false;

    public ToDoV1(String title) {
        this.title = title;
    }
}
