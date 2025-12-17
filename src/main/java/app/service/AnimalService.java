package app.service;

import app.model.Animal;
import app.model.AnimalEntity;
import app.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(AnimalRepository repo) {
        this.repo = repo;
    }

    public List<Animal> findAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<Animal> findById(int id) {
        return repo.findById(id).map(this::toDto);
    }

    public boolean add(Integer typeId, String gender, LocalDate dob, double price) {
        AnimalEntity e = new AnimalEntity();
        e.setTypeId(typeId);
        e.setGender(gender);
        e.setDateOfBirth(dob);
        e.setPrice(price);

        // В БД эти колонки есть. Если у тебя для них есть логика — позже добавим команды.
        // Пока оставим null, если БД позволяет.
        // e.setFeedingId(...);
        // e.setDateOfInspection(...);

        repo.save(e);
        return true;
    }

    public boolean edit(int id, Integer typeId, String gender, LocalDate dob, double price) {
        Optional<AnimalEntity> opt = repo.findById(id);
        if (opt.isEmpty()) return false;

        AnimalEntity e = opt.get();
        e.setTypeId(typeId);
        e.setGender(gender);
        e.setDateOfBirth(dob);
        e.setPrice(price);

        repo.save(e);
        return true;
    }

    public boolean delete(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    private Animal toDto(AnimalEntity e) {
        return new Animal(
                e.getId() == null ? 0 : e.getId(),
                e.getTypeId(),
                e.getGender(),
                e.getDateOfBirth(),
                e.getPrice() == null ? 0.0 : e.getPrice()
        );
    }
}
