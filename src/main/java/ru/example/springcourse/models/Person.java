package ru.example.springcourse.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 10, max = 100, message = "Name should be between 10 and 100 characters")
    private String fullName;

    @Min(value = 1900, message = "год рождения больше 1900")
    private int dateBirth;
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be at this format: Country, City, index(6 digits)")
//    private String address;

    public Person() {

    }

    public Person(int id, String fullName, int dateBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateBirth = dateBirth;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }

}
