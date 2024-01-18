package ru.gb.junior.java.task3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
@NoArgsConstructor
public class ToDoV2 implements Externalizable {
    private String title;
    private boolean isDone = false;

    @Serial
    private static final long serialVersionUID = 2179658779848457450L;

    public ToDoV2(String title) {
        this.title = title;
    }

    @JsonCreator
    public ToDoV2(@JsonProperty("title") String title, @JsonProperty("isDone") boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.title = (String) in.readObject();
        this.isDone = in.readBoolean();
    }
}
