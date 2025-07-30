package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderPage;
import pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static testdata.MainPageTestData.EXPECTED_HEADER_TEXT;

public class HeaderTests {
    MainPage mainPage = new MainPage();
    HeaderPage header = new HeaderPage();

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        mainPage.openMainPage();
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Contact information should be visible at header")
    void contactInformationShouldBeVisibleAtHeaderTest() {
        header
                .headerContactInfo
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HEADER_TEXT));
    }

    @Test
    @DisplayName("Header menu tabs should be visible at main page")
    void mainMenuTabsShouldBeVisibleTest() {
        header.
                tabsInHeader
                .shouldBe(visible);
    }


}
