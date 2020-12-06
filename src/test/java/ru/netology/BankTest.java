package ru.netology;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class BankTest {

    @Test
    public void testTransfer() {
        final User user = DataGenerator.generateValidUser();

        open("http://localhost:9999/");

        final LoginPage loginPage = new LoginPage();
        final VerificationPage verificationPage = loginPage.loginValidUser(user.getLogin(), user.getPassword());
        final DashboardPage dashboardPage = verificationPage.enterValidCode(DataGenerator.getVerificationCode());

        final String firstCardId = dashboardPage.getFirstCardId();
        final String secondCardId = dashboardPage.getSecondtCardId();

        final int transferAmount = 5000;
        final int firstCardBalance = dashboardPage.getCardBalance(firstCardId);
        final int secondCardBalance = dashboardPage.getCardBalance(secondCardId);

        CardDepositPage depositPage = dashboardPage.depositToCard(firstCardId);
        depositPage.transfer(transferAmount, DataGenerator.getSecondCardNumber());

        final int firstCardExpectedBalance = firstCardBalance + transferAmount;
        final int secondCardExpectedBalance = secondCardBalance - transferAmount;

        final int firstCardNewBalance = dashboardPage.getCardBalance(firstCardId);
        final int secondCardNewBalance = dashboardPage.getCardBalance(secondCardId);

        assertThat(firstCardNewBalance, is(firstCardExpectedBalance));
        assertThat(secondCardNewBalance, is(secondCardExpectedBalance));
    }

    @Test
    public void testOverflowTransfer() {
        final User user = DataGenerator.generateValidUser();

        open("http://localhost:9999/");

        final LoginPage loginPage = new LoginPage();
        final VerificationPage verificationPage = loginPage.loginValidUser(user.getLogin(), user.getPassword());
        final DashboardPage dashboardPage = verificationPage.enterValidCode(DataGenerator.getVerificationCode());

        final String firstCardId = dashboardPage.getFirstCardId();
        final String secondCardId = dashboardPage.getSecondtCardId();

        final int firstCardBalance = dashboardPage.getCardBalance(firstCardId);
        final int secondCardBalance = dashboardPage.getCardBalance(secondCardId);
        final int transferAmount = secondCardBalance + 1;

        CardDepositPage depositPage = dashboardPage.depositToCard(firstCardId);
        depositPage.transfer(transferAmount, DataGenerator.getSecondCardNumber());

        final int secondCardNewBalance = dashboardPage.getCardBalance(secondCardId);

        assertThat(secondCardNewBalance, CoreMatchers.is(greaterThanOrEqualTo(0)));
    }
}
