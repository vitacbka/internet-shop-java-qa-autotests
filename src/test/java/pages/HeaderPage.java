package pages;

import com.codeborne.selenide.SelenideElement;
import testdata.HeaderTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static testdata.HeaderTestData.*;

public class HeaderPage {

    public SelenideElement
            mainMenu = $("ul#menu-primary-menu"),
            homeTab = $("li#menu-item-26 a"),
            catalogTab = $("li#menu-item-46 a"),
            myAccountTab = $("li#menu-item-30 a"),
            cartTab = $("li#menu-item-29 a"),
            placeAnOrderTab = $("li#menu-item-31 a"),
            headerContactInfo = $("#custom_html-2"),
            loginLinkButton = $(".account"),
            welcomeTextAtHeader = $(".welcome-user"),
            logoutLinkButton = $(".logout");
    

    public SelenideElement
            appliancesSubMenu = $("li#menu-item-119 a"),
            refrigeratorsLink = $("li#menu-item-120 a"),
            washingMachinesLink = $("li#menu-item-121 a");

    public SelenideElement
            electronicsSubMenu = $("li#menu-item-47 a"),
            phonesLink = $("li#menu-item-114 a"),
            tabletsLink = $("li#menu-item-116 a"),
            tvLink = $("li#menu-item-118 a"),
            photoVideoLink = $("li#menu-item-117 a"),
            watchesLink = $("li#menu-item-115 a");

    public SelenideElement
            booksLink = $("li#menu-item-180 a"),
            clothesLink = $("li#menu-item-48 a");


    public SelenideElement mainMenu() {
        return  mainMenu;
    }

    public SelenideElement catalogTab() {
        return catalogTab;
    }

    public SelenideElement homeTab() {
        return homeTab;
    }

    public SelenideElement myAccountTab() {
        return myAccountTab;
    }

    public SelenideElement cartTab() {
        return cartTab;
    }

    public SelenideElement placeAnOrderTab() {
        return placeAnOrderTab;
    }

    public void clickLogoutButton() {
        logoutLinkButton.click();
    }

    public SelenideElement getLoginButtonText() {
        return loginLinkButton;
    }

    public SelenideElement getWelcomeTextAtHeader() {
        return welcomeTextAtHeader;
    }

    public SelenideElement getLoginLinkButton() {
        return loginLinkButton;
    }

    public void catalogTabClick() {
        catalogTab.click();
    }

    public void homeTabClick() {
        homeTab.click();
    }

    public void accountTabClick() {
        myAccountTab.click();
    }

    public void cartTabClick() {
        cartTab.click();
    }

    public void clickPlaceAnOrderTab() {
        placeAnOrderTab.click();
    }

    public SelenideElement getHeaderContactInfo() {
        return headerContactInfo;
    }

    public void hoverOnCatalogTab() {
        catalogTab.hover();
    }

    public void hoverOnHouseholdAppliances() {
        appliancesSubMenu.hover();
    }

    public void hoverOnElectronics() {
        electronicsSubMenu.hover();
    }

    public void clickRefrigeratorLonk() {
        refrigeratorsLink.click();
    }

    public void clickPhoneLink() {
        phonesLink.click();
    }

    public void verifyMainTabsText() {
        homeTab.shouldHave(text(HeaderTestData.EXPECTED_MAIN_PAGE_TAB));
        catalogTab.shouldHave(text(HeaderTestData.EXPECTED_CATALOG_TAB));
        myAccountTab.shouldHave(text(HeaderTestData.EXPECTED_MY_ACCOUNT_TAB));
        cartTab.shouldHave(text(HeaderTestData.EXPECTED_CART_TAB));
        placeAnOrderTab.shouldHave(text(HeaderTestData.EXPECTED_PLACE_AN_ORDER_TAB));
    }

    public void verifyCatalogSubmenuText() {
        appliancesSubMenu.shouldHave(text(HeaderTestData.EXPECTED_APPLIANCES_TEXT));
        electronicsSubMenu.shouldHave(text(HeaderTestData.EXPECTED_ELECTRONICS_TEXT));
        booksLink.shouldHave(text(HeaderTestData.EXPECTED_BOOKS_TAB));
        clothesLink.shouldHave(text(HeaderTestData.EXPECTED_CLOSES_TAB));
    }

    public void verifyHouseholdAppliancesText() {
        refrigeratorsLink.shouldHave(text(EXPECTED_REFRIGERATOR_TAB));
        washingMachinesLink.shouldHave(text(EXPECTED_WASHING_MACHINE_TAB));
    }

    public void verifyElectronicsSubmenuTexts() {
        phonesLink.shouldHave(text(EXPECTED_PHONES_TAB));
        tabletsLink.shouldHave(text(EXPECTED_TABLETS_TAB));
        tvLink.shouldHave(text(EXPECTED_TELEVISION_TAB));
        photoVideoLink.shouldHave(text(EXPECTED_PHOTO_VIDEO_TAB));
        watchesLink.shouldHave(text(EXPECTED_WATCH_TAB));
    }
}