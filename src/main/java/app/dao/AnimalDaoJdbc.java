package app.dao;

import app.model.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalDaoJdbc implements AnimalDao {

    private final JdbcTemplate jdbc;

    public AnimalDaoJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Animal> findAll() {
        String sql = """
                select id, type_id, gender, date_of_birth, price
                from animals
                order by id
                """;

        return jdbc.query(sql, (rs, rowNum) -> new Animal(
                rs.getInt("id"),
                (Integer) rs.getObject("type_id"),
                rs.getString("gender"),
                rs.getObject("date_of_birth", LocalDate.class),
                rs.getDouble("price")
        ));
    }

    @Override
    public Optional<Animal> findById(int id) {
        String sql = """
                select id, type_id, gender, date_of_birth, price
                from animals
                where id = ?
                """;

        List<Animal> list = jdbc.query(sql, (rs, rowNum) -> new Animal(
                rs.getInt("id"),
                (Integer) rs.getObject("type_id"),
                rs.getString("gender"),
                rs.getObject("date_of_birth", LocalDate.class),
                rs.getDouble("price")
        ), id);

        return list.stream().findFirst();
    }

    @Override
    public int insert(Animal a) {
        String sql = """
                insert into animals(type_id, gender, date_of_birth, price)
                values (?, ?, ?, ?)
                """;
        return jdbc.update(sql,
                a.typeId(),
                a.gender(),
                a.dateOfBirth(),
                a.price()
        );
    }

    @Override
    public int update(Animal a) {
        String sql = """
                update animals
                set type_id = ?, gender = ?, date_of_birth = ?, price = ?
                where id = ?
                """;
        return jdbc.update(sql,
                a.typeId(),
                a.gender(),
                a.dateOfBirth(),
                a.price(),
                a.id()
        );
    }

    @Override
    public int deleteById(int id) {
        return jdbc.update("delete from animals where id = ?", id);
        //test
    }
}
