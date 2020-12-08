package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeInput = $("[data-test-id=code] input");
    private final SelenideElement continueButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
    }

    public DashboardPage enterValidCode(String code) {
        codeInput.sendKeys(code);
        continueButton.click();

        return new DashboardPage();
    }
}
