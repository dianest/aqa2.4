package ru.netology.data;

public class DataGenerator {
    private DataGenerator() {
    }

    public static User generateValidUser() {
        return new User("vasya", "qwerty123");
    }

    public static String getVerificationCode() {
        return "12345";
    }

    public static String getFirstCardNumber() {
        return "5559 0000 0000 0001";
    }

    public static String getSecondCardNumber() {
        return "5559 0000 0000 0002";
    }
}
