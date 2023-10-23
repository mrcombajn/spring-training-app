package pl.trainingapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pl.trainingapp.entities.Exercise;
import pl.trainingapp.entities.exceptions.EntityNotFoundException;
import pl.trainingapp.repositories.ExerciseRepository;
import pl.trainingapp.controller.requests.ExerciseRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return (List<Exercise>) exerciseRepository.findAll();
    }

    public Exercise getSpecificExercise(long id) throws EntityNotFoundException {
        return exerciseRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find entity in database."));
    }

    public void addExercise(ExerciseRequest exerciseRequest) {
        exerciseRepository.save(new Exercise(exerciseRequest));
    }

    public void deleteExercise(long id) {
        exerciseRepository.deleteById(id);
    }

}
