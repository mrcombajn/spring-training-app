package pl.trainingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.trainingapp.repositories.ExerciseRepository;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public void addExercise(String name) {
        exerciseRepository.addExerise(name);
    }

    public void deleteExerciseById(int id) {
        exerciseRepository.deleteExercise(id);
    }

}
