package ru.netology;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("[data-test-id=login] input");
    private final SelenideElement passwordInput = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");

    public LoginPage() {
    }

    public VerificationPage loginValidUser(String userName, String password) {
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginButton.click();

        return new VerificationPage();
    }
}
