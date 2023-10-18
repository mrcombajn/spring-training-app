package pl.trainingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.entities.exceptions.EntityNotFoundException;
import pl.trainingapp.services.ExerciseService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseService exerciseService;

    private ArrayList<Exercise> exercises;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initializeObjects() {
        mapper = new ObjectMapper();
    }

    @BeforeEach
    void InitializeObjectsBeforeTest() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise(1, "pompki"));
        exercises.add(new Exercise(2, "przysiady"));
        exercises.add(new Exercise(3, "pompki australijskie"));
    }

    @Test
    void getAllExercises() throws Exception {
        when(exerciseService.getAllExercises())
                .thenReturn(exercises);

        this.mockMvc.perform(get(""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(exercises)));
    }

    @Test
    void getAvailableExercise() throws Exception {
        when(exerciseService.getSpecificExercise(1))
                .thenReturn(exercises.get(1));

        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(exercises.get(1))));
    }

    @Test
    void getUnavailableExercise() {
        when(exerciseService.getSpecificExercise(4))
                .thenThrow(new EntityNotFoundException("Entity not found!"));

        try {
            this.mockMvc.perform(get("/4"))
                    .andDo(print())
                    .andExpect(status().is5xxServerError());
        } catch (Exception e) {

        }
    }

    @Test
    void addExercise() {
    }

    @Test
    void deleteExercise() {
    }
}