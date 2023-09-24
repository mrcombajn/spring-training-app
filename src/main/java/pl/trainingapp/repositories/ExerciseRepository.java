package pl.trainingapp.repositories;

import org.springframework.stereotype.Repository;
import pl.trainingapp.entities.Exercise;

import java.util.List;

@Repository
public class ExerciseRepository {
    
    public void addExercise(String name) {
        
    }

    public void deleteExerciseById(int id) {
    }

    public List<Exercise> getExercises() {
        return ExerciseDatabase.exercises;
    }

    public Exercise getExerciseById(int id) {
        return ExerciseDatabase
                .exercises
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst().get();
    }
}
