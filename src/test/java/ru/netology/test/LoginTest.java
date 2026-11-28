package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
    }

    @Test
    void shouldLoginWithValidCredentials() {
        VerificationPage verificationPage = loginPage.login("vasya", "qwerty123");

        String code = DbHelper.getVerificationCode("vasya");
        verificationPage.verify(code);
        verificationPage.waitForDashboard();
    }
}