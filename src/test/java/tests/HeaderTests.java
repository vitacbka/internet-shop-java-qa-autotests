package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static testdata.MyAccountPageTestData.EXPECTED_MY_ACCOUNT_TITLE;
import static testdata.CartPageTestData.EXPECTED_CART_PAGE_TITLE;
import static testdata.CatalogPageTestData.CATALOG_PAGE_TITLE;
import static testdata.HeaderTestData.HEADER_CONTACT_INFO;
import static testdata.MainPageTestData.EXPECTED_TITLE_TEXT;

public class HeaderTests {
    MainPage mainPage = new MainPage();
    HeaderPage header = new HeaderPage();
    CatalogPage catalogPage = new CatalogPage();
    MyAccountPage authPage = new MyAccountPage();
    CartPage cartPage = new CartPage();

    @BeforeAll
    static void allSetUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setup() {
        mainPage.openMainPage();
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Header tabs should be visible at main page")
    void contactInformationShouldBeVisibleAtHeaderTest() {
        header.verifyMainTabsAreVisible();
    }

    @Test
    @DisplayName("Click on home tab should open main page")
    void mainMenuTabsShouldBeVisibleTest() {
        header.homeTab.click();
        mainPage.mainPageTitle.shouldBe(visible, text(EXPECTED_TITLE_TEXT));
    }

    @Test
    @DisplayName("Click on catalog tab should open catalog page")
    void catalogMenuTabsShouldBeVisibleTest() {
        header
                .catalogTab.click();
        catalogPage
                .catalogPageTitle.shouldBe(visible, text(CATALOG_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on my account tab should open my account page")
    void clickOnMyAccountHeaderTabTest() {
        header.accountTab.click();
        authPage.MyAccountPageTitle.shouldBe(visible, text(EXPECTED_MY_ACCOUNT_TITLE));
    }

    @Test
    @DisplayName("Click on cart tab should open cart page")
    void clickOnCartHeaderTabTest() {
        header
                .cartTab.shouldBe(visible).click();
        cartPage
                .cartPageTitle.shouldBe(visible, text(EXPECTED_CART_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on place tab should open cart page")
    void clickOnPlaceHeaderTabTest() {
        header
                .placeAnOrder.shouldBe(visible).click();
        cartPage
                .cartPageTitle.shouldBe(visible, text(EXPECTED_CART_PAGE_TITLE));
    }

    @Test
    @DisplayName("Contact information should be displayed in header")
    void checkContactInfoInHeader() {
        header.headerContactInfo.shouldBe(visible, text(HEADER_CONTACT_INFO));
    }
}
