package ru.netology;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CardDepositPage {
    private final By amountBy = By.cssSelector("[data-test-id=amount] input");
    private final By fromBy = By.cssSelector("[data-test-id=from] input");
    private final By transferBy = By.cssSelector("[data-test-id=action-transfer]");
    private final By cancelBy = By.cssSelector("[data-test-id=action-cancel]");

    public CardDepositPage() {
    }

    public DashboardPage transfer(String amount, String from) {
        $(amountBy).sendKeys(amount);
        $(fromBy).sendKeys(from);
        $(transferBy).click();

        return new DashboardPage();
    }

    public DashboardPage cancel() {
        $(cancelBy).click();

        return new DashboardPage();
    }
}
