package ru.netology;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardDepositPage {
    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement fromInput = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public CardDepositPage() {
    }

    public DashboardPage transfer(int amount, String from) {
        amountInput.sendKeys(String.valueOf(amount));
        fromInput.sendKeys(from);
        transferButton.click();

        return new DashboardPage();
    }

    public DashboardPage cancel() {
        cancelButton.click();

        return new DashboardPage();
    }
}
