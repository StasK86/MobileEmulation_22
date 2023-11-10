package com.krigersv.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import com.krigersv.drivers.BrowserstackDriver;
import com.krigersv.drivers.LocalAndroidDriver;
import com.krigersv.helpers.Attach;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    private final static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {
        switch (deviceHost) {
            case "stack":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "local":
                Configuration.browser = LocalAndroidDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void tearDown() {
        switch (deviceHost) {
            case "stack":
                String sessionId = Selenide.sessionId().toString();

                Attach.pageSource();
                Attach.addVideo(sessionId);
                break;
            case "local":
                Attach.screenshotAs("Last screenshot");
                Attach.pageSource();
        }
        closeWebDriver();
    }
}