package tests;

import org.junit.jupiter.api.*;
import pages.*;

import static readproperties.ConfigProvider.CART_PAGE_URL;
import static testdata.HeaderTestData.*;
import static testdata.MainPageTestData.EXPECTED_MAIN_PAGE_TITLE_TEXT;
import static testdata.MyAccountPageTestData.EXPECTED_MY_ACCOUNT_TITLE;
import static testdata.CartPageTestData.EXPECTED_CART_PAGE_TITLE;
import static testdata.CatalogPageTestData.CATALOG_PAGE_TITLE;

public class HeaderTests extends BaseTest{
    MainPage mainPage = new MainPage();
    HeaderPage headerPage = new HeaderPage();
    CatalogPage catalogPage = new CatalogPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    CartPage cartPage = new CartPage();

    @BeforeEach
    void setup() {
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Click on home tab should open main page")
    void mainMenuTabsShouldBeVisibleTest() {
        headerPage.homeTabClick();
        mainPage.mainPageTitleShouldBeVisible(EXPECTED_MAIN_PAGE_TITLE_TEXT);
    }

    @Test
    @DisplayName("Click on catalog tab should open catalog page")
    void catalogMenuTabsShouldBeVisibleTest() {
        headerPage.catalogTabClick();
        catalogPage.catalogPageTitleShouldBeVisible(CATALOG_PAGE_TITLE);
    }

    @Test
    @DisplayName("Click on my account tab should open my account page")
    void clickOnMyAccountHeaderTabTest() {
        headerPage.accountTabClick();
        myAccountPage.myAccountPageTitleShouldBeVisible(EXPECTED_MY_ACCOUNT_TITLE);
    }

    @Test
    @DisplayName("Click on cart tab should open cart page")
    void clickOnCartHeaderTabTest() {
        headerPage.clickCartTab();
        cartPage.isOmCartPage(EXPECTED_CART_PAGE_TITLE, CART_PAGE_URL);
    }

    @Test
    @DisplayName("Click on place on order tab should open cart page")
    void clickOnPlaceHeaderTabTest() {
        headerPage.clickPlaceAnOrderTab();
        cartPage.isOmCartPage(EXPECTED_CART_PAGE_TITLE, CART_PAGE_URL);
    }

    @Test
    @DisplayName("Contact information should be displayed in header")
    void checkContactInfoInHeaderTest() {
        headerPage.headerContactInfoShouldBeVisible(EXPECTED_HEADER_CONTACT_INFO);
    }

    @Test
    @DisplayName("Catalog submenu should be displayed with correct text")
    void catalogSubmenuShouldDisplayCorrectTextTest() {
        headerPage.hoverOnCatalogTab();
        headerPage.verifyCatalogSubmenuText();
    }

    @Test
    @DisplayName("Household appliances submenu should be displayed with correct text")
    void householdAppliancesShouldBeDisplayedWithCorrectTextTest() {
        headerPage.hoverOnCatalogTab();
        headerPage.hoverOnHouseholdAppliances();
        headerPage.verifyHouseholdAppliancesText();
    }

    @Test
    @DisplayName("Electronics submenu should be displayed with correct text")
    void electronicsSubmenuShouldBeDisplayedWithCorrectTextTest() {
        headerPage.hoverOnCatalogTab();
        headerPage.hoverOnElectronics();
        headerPage.verifyElectronicsSubmenuTexts();
    }

    @Test
    @DisplayName("Main page tabs should be visible")
    void mainPageTabsShouldBeVisible() {
        headerPage.verifyMainTabsText();
    }
}