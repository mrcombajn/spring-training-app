package pl.trainingapp.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercise {

    private int id;

    private String name;

    public Exercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Exercise() {
    }
}
