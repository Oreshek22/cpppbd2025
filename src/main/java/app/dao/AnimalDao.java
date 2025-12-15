package app.dao;

import app.model.Animal;
import java.util.List;
import java.util.Optional;

public interface AnimalDao {
    List<Animal> findAll();
    Optional<Animal> findById(int id);

    int insert(Animal a);
    int update(Animal a);
    int deleteById(int id);
}
