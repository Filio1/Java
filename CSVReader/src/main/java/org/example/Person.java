package org.example;

public class Person {
    private int id;
    private String name;
    private String email;
    private String country;

    public Person(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public void PrintPerson() {
        System.out.printf("%d %s %s %s\n", id, name, email, country);
    }
}
