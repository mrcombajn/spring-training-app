package pl.trainingapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.trainingapp.services.ExerciseService;


@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public String getAllExercises() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(exerciseService.getAllExercises());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/exercises/{id}")
    public String getExerciseById(@PathVariable int id) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(exerciseService.getSpecificExercise(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/exercises/{name}")
    public void addExercise(@PathVariable String name) {
        exerciseService.addExercise(name);
    }

    @DeleteMapping("/exercises/{id}")
    public void deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
    }

}
