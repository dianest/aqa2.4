package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CardDepositPage {
    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement fromInput = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

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

    public void assertTransactionFailed() {
        errorNotification.waitUntil(Condition.visible, 5000).
                shouldHave(text("Не удалось совершить перевод. Проверьте правильность введенных данных."));
    }
}
