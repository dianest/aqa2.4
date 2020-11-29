package ru.netology;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final By codeBy = By.cssSelector("[data-test-id=code] input");
    private final By continueBy = By.cssSelector("[data-test-id=action-verify]");

    public VerificationPage(){
    }

    public DashboardPage enterValidCode(String code) {
        $(codeBy).sendKeys(code);
        $(continueBy).click();
        return new DashboardPage();
    }
}
