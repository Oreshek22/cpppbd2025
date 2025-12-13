package app.service;

import app.dao.AnimalDao;
import app.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {
    @Test
    void findByIdDelegatesToDao() {
        AnimalDao dao = mock(AnimalDao.class);
        AnimalService svc = new AnimalService(dao);

        var a = new Animal(1, null, "M", null, 100.0); // если у тебя модель расширилась — подстрой конструктор
        when(dao.findById(1)).thenReturn(Optional.of(a));

        var res = svc.findById(1);

        assertTrue(res.isPresent());
        verify(dao).findById(1);
    }
}
