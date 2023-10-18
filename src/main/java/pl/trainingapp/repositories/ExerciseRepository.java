package pl.trainingapp.repositories;

import org.springframework.stereotype.Repository;
import pl.trainingapp.entities.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public class ExerciseRepository {
    
    public void addExercise(String name) {
        int maxId = ExerciseDatabase.exercises.stream().mapToInt(Exercise::getId).max().orElse(0) + 1;

        ExerciseDatabase.exercises.add(new Exercise(maxId, name));
    }

    public void deleteExerciseById(int id) {
        Optional<Exercise> exercise = ExerciseDatabase
                .exercises.stream().filter(e -> e.getId() == id)
                .findFirst();

        exercise.ifPresent(e -> ExerciseDatabase.exercises.remove(e));
    }

    public List<Exercise> getExercises() {
        return ExerciseDatabase.exercises;
    }

    public Optional<Exercise> getExerciseById(int id) {
        return ExerciseDatabase
                .exercises
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
}
