package com.krigersv.tests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class LocalTests extends TestBase {

    @Test
    @Tag("androidLocal")
    void searchTest() {
        back();
        step("Выполняется поиск", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Britney Spears");
        });
        step("Проверка результата", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("androidLocal")
    void checkWikipediaApplication () {
        step("Проверка первой страницы", () ->
                $(id("org.wikipedia.alpha:id/secondaryTextView"))
                        .shouldHave(text("We’ve found the following on your device:")));

        step("Переход на вторую страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("New ways to explore")));

        step("Переход на третью страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Reading lists with sync")));

        step("Переход на четвертую страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Send anonymous data")));


    }
}