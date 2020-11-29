package ru.netology;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankTest {

    @Test
    public void testTransfer() {
        final User user = UserGenerator.generateValidUser();

        open("http://localhost:9999/");

        final LoginPage loginPage = new LoginPage();
        final VerificationPage verificationPage = loginPage.loginValidUser(user.getLogin(), user.getPassword());
        final DashboardPage dashboardPage = verificationPage.enterValidCode(user.getVerificationCode());

        assertThat(dashboardPage.getCardBalance(0), is("10000"));
        assertThat(dashboardPage.getCardBalance(1), is("10000"));

        CardDepositPage depositPage = dashboardPage.depositToCard(0);

        depositPage.cancel();
        assertThat(dashboardPage.getCardBalance(0), is("10000"));
        assertThat(dashboardPage.getCardBalance(1), is("10000"));

        depositPage = dashboardPage.depositToCard(0);

        depositPage.transfer("5000", "5559 0000 0000 0002");
        assertThat(dashboardPage.getCardBalance(0), is("15000"));
        assertThat(dashboardPage.getCardBalance(1), is("5000"));
    }
}
