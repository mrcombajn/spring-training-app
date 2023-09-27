package pl.trainingapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.exceptions.EntityNotFoundException;
import pl.trainingapp.repositories.ExerciseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.getExercises();
    }

    public Exercise getSpecificExercise(int id) throws EntityNotFoundException {
        return exerciseRepository
                .getExerciseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find entity in database."));
    }

    public void addExercise(String name) {
        exerciseRepository.addExercise(name);
    }

    public void deleteExercise(int id) {
        exerciseRepository.deleteExerciseById(id);
    }

}
