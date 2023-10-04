package pl.trainingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import pl.trainingapp.controller.requests.ExerciseRequest;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.services.ExerciseService;

import java.util.List;


@RestController("/exercices")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable int id) {
        return exerciseService.getSpecificExercise(id);
    }

    @PostMapping
    public void addExercise(@RequestBody ExerciseRequest exerciseRequest) {
        exerciseService.addExercise(exerciseRequest.getName());
    }

    @DeleteMapping("/exercises/{id}")
    public void deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
    }

}
