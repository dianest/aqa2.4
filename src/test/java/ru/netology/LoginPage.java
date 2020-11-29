package ru.netology;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final By usernameBy = By.cssSelector("[data-test-id=login] input");
    private final By passwordBy = By.cssSelector("[data-test-id=password] input");
    private final By loginBy = By.cssSelector("[data-test-id=action-login]");

    public LoginPage() {
    }

    public VerificationPage loginValidUser(String userName, String password) {
        $(usernameBy).sendKeys(userName);
        $(passwordBy).sendKeys(password);
        $(loginBy).click();
        return new VerificationPage();
    }
}
