package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import lombok.val;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;


import java.util.concurrent.Callable;

import static com.codeborne.selenide.Selenide.*;


public class MoneyTransferTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void shouldLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

    }

    @Test
    void TransferFomFirstCardToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        int transferAmount = 300;
        String card = String.valueOf(DataHelper.getCardNumber1());
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        //new DashboardPage()
                //.topUpCardButton2();
        var dashboardPage = new DashboardPage();
        var Card1Balance= dashboardPage.getCard1Balance();
        var Card2Balance = dashboardPage.getCard2Balance();
        dashboardPage.topUpCardButton2();
        new CardReplenishmentPage()
                .transferFromCardToCard(transferAmount, card);
        var Card1Balance1= dashboardPage.getCard1Balance();
        var Card2Balance2 = dashboardPage.getCard2Balance();
        Assertions.assertEquals(Card1Balance - transferAmount, Card1Balance1);
        Assertions.assertEquals(Card2Balance + transferAmount, Card2Balance2);
    }
    @Test
    void TransferFromTheSecondCardToTheFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        int transferAmount = 5000;
        String card = String.valueOf(DataHelper.getCardNumber2());
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        //new DashboardPage()
                //.topUpCardButton1();
        val dashboardPage = new DashboardPage();
        var Card1Balance= dashboardPage.getCard1Balance();
        var Card2Balance = dashboardPage.getCard2Balance();
        dashboardPage.topUpCardButton1();
        new CardReplenishmentPage()
                .transferFromCardToCard(transferAmount, card);
        var Card1Balance1= dashboardPage.getCard1Balance();
        var Card2Balance2 = dashboardPage.getCard2Balance();
        Assertions.assertEquals(Card2Balance + transferAmount, Card2Balance2);
        Assertions.assertEquals(Card1Balance - transferAmount, Card1Balance1);
    }
}