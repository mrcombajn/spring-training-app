package pl.trainingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.Assert;
import pl.trainingapp.controller.requests.ExerciseRequest;
import pl.trainingapp.entities.Exercise;
import pl.trainingapp.entities.exceptions.EntityNotFoundException;
import pl.trainingapp.services.ExerciseService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
@DirtiesContext
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
    void getUnavailableExercise() throws Exception {
        when(exerciseService.getSpecificExercise(4))
                .thenThrow(new EntityNotFoundException("Entity not found!"));

        this.mockMvc.perform(get("/4"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    void addExercise() {
        ExerciseRequest exerciseRequest = new ExerciseRequest("new Exercise");
        doAnswer( i -> {
                    Assertions.assertEquals(4, exercises.size());
                    return null;
                }
        ).when(exerciseService).addExercise(exerciseRequest);


/*        this.mockMvc.perform()
                .andExpect(status().isOk());*/
    }

    @Test
    void deleteExerciseSuccessful() throws Exception{
        doAnswer( i -> {
            Assertions.assertEquals(2, exercises.size());
            return null;
        }
        ).when(exerciseService).deleteExercise(1);


        this.mockMvc.perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteExerciseUnsuccessful() throws Exception{
        this.mockMvc.perform(delete("/4"))
                .andExpect(status().is4xxClientError());
    }
}