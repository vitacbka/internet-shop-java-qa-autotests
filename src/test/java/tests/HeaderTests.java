package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static testdata.HeaderTestData.*;
import static testdata.MyAccountPageTestData.EXPECTED_MY_ACCOUNT_TITLE;
import static testdata.CartPageTestData.EXPECTED_CART_PAGE_TITLE;
import static testdata.CatalogPageTestData.CATALOG_PAGE_TITLE;
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
        header.mainMenu().shouldBe(visible);
        header.homeTab().shouldBe(visible);
        header.catalogTab().shouldBe(visible);
        header.myAccountTab().shouldBe(visible);
        header.cartTab().shouldBe(visible);
        header.placeAnOrderTab().shouldBe(visible);
    }

    @Test
    @DisplayName("Click on home tab should open main page")
    void mainMenuTabsShouldBeVisibleTest() {
        header.homeTabClick();
        mainPage.mainPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_TITLE_TEXT));
    }

    @Test
    @DisplayName("Click on catalog tab should open catalog page")
    void catalogMenuTabsShouldBeVisibleTest() {
        header.catalogTabClick();
        catalogPage.getCatalogPageTitle()
                .shouldBe(visible, text(CATALOG_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on my account tab should open my account page")
    void clickOnMyAccountHeaderTabTest() {
        header.accountTabClick();
        authPage.MyAccountPageTitle.shouldBe(visible, text(EXPECTED_MY_ACCOUNT_TITLE));
    }

    @Test
    @DisplayName("Click on cart tab should open cart page")
    void clickOnCartHeaderTabTest() {
        header.cartTabClick();
        cartPage.getCartPageTitle()
                .shouldBe(visible, text(EXPECTED_CART_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on place tab should open cart page")
    void clickOnPlaceHeaderTabTest() {
        header.clickPlaceAnOrderTab();
        cartPage.getCartPageTitle()
                .shouldBe(visible, text(EXPECTED_CART_PAGE_TITLE));
    }

    @Test
    @DisplayName("Contact information should be displayed in header")
    void checkContactInfoInHeaderTest() {
        header.getHeaderContactInfo()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HEADER_CONTACT_INFO));
    }

    @Test
    @DisplayName("Header tabs should correct text")
    void headerTabsShouldHaveCorrectTextTest() {
        header.homeTab().shouldHave(text(EXPECTED_MAIN_PAGE_TAB));
        header.catalogTab().shouldHave(text(EXPECTED_CATALOG_TAB));
        header.myAccountTab().shouldHave(text(EXPECTED_MY_ACCOUNT_TAB));
        header.cartTab().shouldHave(text(EXPECTED_CART_TAB));
        header.placeAnOrderTab().shouldHave(text(EXPECTED_PLACE_AN_ORDER_TAB));
    }

    @Test
    @DisplayName("Catalog submenu should be displayed with correct text")
    void catalogSubmenuShouldDisplayCorrectTextTest() {
        header.hoverOnCatalogTab();
        header.verifyCatalogSubmenuText();
    }

    @Test
    @DisplayName("Household appliances submenu should be displayed with correct text")
    void householdAppliancesShouldBeDisplayedWithCorrectTextTest() {
        header.hoverOnCatalogTab();
        header.hoverOnHouseholdAppliances();
        header.verifyHouseholdAppliancesText();
    }

    @Test
    @DisplayName("Electronics submenu should be displayed with correct text")
    void electronicsSubmenuShouldBeDisplayedWithCorrectTextTest() {
        header.hoverOnCatalogTab();
        header.hoverOnElectronics();
        header.verifyElectronicsSubmenuTexts();
    }
}