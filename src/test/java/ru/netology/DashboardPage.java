package ru.netology;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final By listBy = By.cssSelector("ul");
    private final By listItemBy = By.cssSelector("li");
    private final By depositBy = By.cssSelector("[data-test-id=action-deposit]");

    public DashboardPage() {
    }

    public String getCardBalance(int index) {
        final String text = $(listBy).findAll(listItemBy).get(index).getText();
        final int startIndex = text.indexOf("баланс: ");
        final String balanceText = text.substring(startIndex);
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < balanceText.length(); i++) {
            if(Character.isDigit(balanceText.charAt(i))) {
                sb.append(balanceText.charAt(i));
            }
        }

        return sb.toString();
    }

    public CardDepositPage depositToCard(int index) {
        $(listBy).findAll(listItemBy).get(index).$(depositBy).click();

        return new CardDepositPage();
    }
}
