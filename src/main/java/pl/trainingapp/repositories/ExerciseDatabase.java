package pl.trainingapp.repositories;

import pl.trainingapp.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDatabase {

    public static List<Exercise> exercises = new ArrayList<>() {
        {
            add(new Exercise(1, "Pompki"));
            add(new Exercise(2, "Przysiady"));
            add(new Exercise(3, "Deska"));
        }
    };


}
