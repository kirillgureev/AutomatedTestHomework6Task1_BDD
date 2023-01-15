package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;


import static com.codeborne.selenide.Selenide.$;

public class CardReplenishmentPage {

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fieldFrom = $("[data-test-id=\"from\"] input");
    private SelenideElement topUpButton = $("[data-test-id=\"action-transfer\"]");


    public void transferFromCardToCard(int transferAmount, String card) {
        amountField.setValue(String.valueOf(transferAmount));
        fieldFrom.setValue(String.valueOf(card));
        topUpButton.click();
    }
}