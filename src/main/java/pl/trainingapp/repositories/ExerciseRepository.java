package pl.trainingapp.repositories;

import org.springframework.stereotype.Repository;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public class ExerciseRepository {
    
    public void addExercise(String name) {
        int maxId = ExerciseDatabase.exercises.stream().mapToInt(Exercise::getId).max().orElse(1);

        ExerciseDatabase.exercises.add(new Exercise(maxId, name));
    }

    public void deleteExerciseById(int id) throws EntityNotFoundException {
        Optional<Exercise> exercise = Optional.ofNullable(ExerciseDatabase
                .exercises.stream().filter(e -> e.getId() == id)
                .findFirst().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Cannot find entity in database.")));

        ExerciseDatabase.exercises.remove(exercise.get());
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
