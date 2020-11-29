package ru.netology;

public class UserGenerator {
    private UserGenerator() {
    }

    public static User generateValidUser() {
        return new User("vasya", "qwerty123", "12345");
    }
}
