package app.dao;

import app.model.Animal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalDao {

    private final ConnectionFactory cf;

    public AnimalDao(ConnectionFactory cf) {
        this.cf = cf;
    }

    public List<Animal> findAll() {
        String sql = "SELECT id, type_id, gender, date_of_birth, price FROM animals ORDER BY id";
        try (Connection c = cf.open();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Animal> out = new ArrayList<>();
            while (rs.next()) out.add(map(rs));
            return out;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Animal> findById(int id) {
        String sql = "SELECT id, type_id, gender, date_of_birth, price FROM animals WHERE id = ?";
        try (Connection c = cf.open();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Animal map(ResultSet rs) throws SQLException {
        Integer typeId = (Integer) rs.getObject("type_id");
        String gender = rs.getString("gender");
        Date dob = rs.getDate("date_of_birth");
        LocalDate dateOfBirth = dob == null ? null : dob.toLocalDate();
        double price = rs.getDouble("price");
        return new Animal(rs.getInt("id"), typeId, gender, dateOfBirth, price);
    }
}
