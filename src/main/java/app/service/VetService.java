package app.service;

import app.model.Vet;
import app.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }

    public Vet getById(int id) {
        return vetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vet not found: " + id));
    }

    public List<Vet> getAll() {
        return vetRepository.findAll();
    }

    public void deleteById(int id) {
        vetRepository.deleteById(id);
    }
}
