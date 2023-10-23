package pl.trainingapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import pl.trainingapp.controller.requests.ExerciseRequest;

@Getter
@Setter
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Exercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Exercise(ExerciseRequest exerciseRequest) {
        this.name = exerciseRequest.getName();
    }

    public Exercise() {
    }
}
