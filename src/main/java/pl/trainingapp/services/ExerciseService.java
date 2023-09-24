package pl.trainingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.repositories.ExerciseRepository;

import java.util.List;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.getExercises();
    }

    public Exercise getSpecificExercise(int id) {
        return exerciseRepository.getExerciseById(id);
    }

    public void addExercise(String name) {
        exerciseRepository.addExercise(name);
    }

    public void deleteExercise(int id) {
        exerciseRepository.deleteExerciseById(id);
    }

}
