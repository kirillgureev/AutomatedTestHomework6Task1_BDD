package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement headind = $("[data-test-id=\"dashboard\"]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement card1= $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]").find("[data-test-id=\"action-deposit\"]");
    private SelenideElement card2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").find("[data-test-id=\"action-deposit\"]");    public DashboardPage() {
    }

    public void topUpCardButton1(){
        card1.click();
    }
    public void topUpCardButton2(){
        card2.click();
    }

    public int getCard1Balance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    public int getCard2Balance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}