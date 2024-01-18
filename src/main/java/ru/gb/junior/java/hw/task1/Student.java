package ru.gb.junior.java.hw.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Externalizable {
    private String name;
    private int age;
    @JsonIgnore
    private transient double GPA;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }
}
