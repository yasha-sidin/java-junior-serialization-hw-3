package ru.gb.junior.java.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserData implements Serializable {
    private String name;
    private int age;
    @JsonIgnore
    private transient String password;
}
