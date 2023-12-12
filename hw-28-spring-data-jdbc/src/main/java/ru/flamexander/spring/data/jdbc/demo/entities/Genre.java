package ru.flamexander.spring.data.jdbc.demo.entities;

public enum Genre {
    FANTASY("Фэнтези"), SCIFI("Научная фантастика"), ADVENTURE("Приключение");

    private String name;

    public String getName() {
        return name;
    }

    Genre(String name) {
        this.name = name;
    }
}
