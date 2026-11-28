package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement codeInput = $("[data-test-id='code'] input");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private final SelenideElement dashboardHeading = $("h2.heading");

    public void verify(String code) {
        codeInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(code);
        verifyButton.click();
    }

    public void waitForDashboard() {
        dashboardHeading
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(15));
    }
}