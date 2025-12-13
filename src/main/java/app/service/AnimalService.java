package app.service;

import app.dao.AnimalDao;
import app.model.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private static final Logger log = LoggerFactory.getLogger(AnimalService.class);

    private final AnimalDao dao;

    public AnimalService(AnimalDao dao) { this.dao = dao; }

    public List<Animal> findAll() {
        log.info("AnimalService.findAll()");
        return dao.findAll();
    }


    public Optional<Animal> findById(int id) {
        log.info("AnimalService.findById({})", id);
        return dao.findById(id);
    }
}
