package pl.trainingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.trainingapp.entities.Exercise;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
