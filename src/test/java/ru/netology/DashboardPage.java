package ru.netology;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
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
        final String balanceStart = "баланс: ";
        final String balanceFinish = " р.";
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);

        return Integer.parseInt(value);
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
