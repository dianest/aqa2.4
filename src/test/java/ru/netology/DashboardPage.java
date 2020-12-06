package ru.netology;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item");
    private final By depositBy = By.cssSelector("[data-test-id=action-deposit]");

    public DashboardPage() {
    }

    public String getFirstCardId() {
        return getCardId(0);
    }

    public String getSecondtCardId() {
        return getCardId(1);
    }

    public int getCardBalance(String id) {
        final String text = findCard(cards, id).getText();
        final int startIndex = text.indexOf("баланс: ");
        final String balanceText = text.substring(startIndex);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < balanceText.length(); i++) {
            if (Character.isDigit(balanceText.charAt(i))) {
                sb.append(balanceText.charAt(i));
            }
        }

        return Integer.parseInt(sb.toString());
    }

    public CardDepositPage depositToCard(String id) {
        findCard(cards, id).$(depositBy).click();

        return new CardDepositPage();
    }

    private String getCardId(int index) {
        return cards.get(index).find("[data-test-id]").getAttribute("data-test-id");
    }

    private SelenideElement findCard(ElementsCollection cards, String id) {
        for (int i = 0; i < cards.size(); i++) {
            if (getCardId(i).equals(id)) {
                return cards.get(i);
            }
        }

        return null;
    }
}
