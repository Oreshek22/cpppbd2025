package app.model;

import java.time.LocalDate;

public record Animal(
        int id,
        Integer typeId,
        String gender,
        java.time.LocalDate dateOfBirth,
        double price
) {}
