package ru.example.springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.example.springcourse.models.Book;
import ru.example.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));

    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, date_Birth) VALUES(?,?)",
                person.getFullName(), person.getDateBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET full_name=?, date_birth=? WHERE id=?",
                updatedPerson.getFullName(), updatedPerson.getDateBirth(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBookByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    ///////////////////////////
    //////Тест пакетной вставки
    //////////////////////////

//    public void testMultipleUpdate() {
//        List<Person> people = createPeople();
//        long before = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO person VALUES(?, ?,?,?)", person.getPersonId(),
//                    person.getName(), person.getAge(), person.getEmail());
//        }
//        long after = System.currentTimeMillis();
//        System.out.println("Time taken: " + (after - before));
//
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = createPeople();
//        long before = System.currentTimeMillis();
//        jdbcTemplate.batchUpdate("INSERT INTO  person VALUES(?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                        preparedStatement.setInt(1, people.get(i).getPersonId());
//                        preparedStatement.setString(2, people.get(i).getName());
//                        preparedStatement.setInt(3, people.get(i).getAge());
//                        preparedStatement.setString(4, people.get(i).getEmail());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//        long after = System.currentTimeMillis();
//        System.out.println("Time taken: " + (after - before));
//    }
//
//    private List<Person> createPeople() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            //people.add(new Person(i, "Name" + i, 30, "test" + i + "@gmail.com"));
//        }
//        return people;
//    }


}
