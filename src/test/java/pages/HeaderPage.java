package pages;

import com.codeborne.selenide.SelenideElement;
import testdata.HeaderTestData;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
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
            searchField = $(".search-field"),
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

    public HeaderPage clickLogoutButton() {
        logoutLinkButton.shouldBe(visible).click();
        return this;
    }

    public void clickLoginButton() {
        loginLinkButton.shouldBe(visible).shouldBe(clickable).click();
    }

    public HeaderPage loginButtonShouldBeVisible(String loginButtonText) {
        loginLinkButton.shouldBe(visible)
                .shouldHave(text(loginButtonText));
        return this;
    }

    public HeaderPage welcomeTextAtHeaderShouldBeDisplayed(String welcomeText) {
        welcomeTextAtHeader.shouldBe(visible).shouldHave(text(welcomeText));
        return this;
    }

    public HeaderPage welcomeTextAtHeaderShouldNotBeVisible() {
        welcomeTextAtHeader.shouldNotBe(visible);
        return this;
    }

    public HeaderPage phoneLinkClick() {
        phonesLink.shouldBe(visible).click();
        return this;
    }

    public HeaderPage catalogTabClick() {
        catalogTab.click();
        return this;
    }

    public HeaderPage homeTabClick() {
        homeTab.click();
        return this;
    }

    public HeaderPage accountTabClick() {
        myAccountTab.click();
        return this;
    }

    public HeaderPage clickCartTab() {
        cartTab.click();
        return this;
    }

    public HeaderPage clickPlaceAnOrderTab() {
        placeAnOrderTab.click();
        return this;
    }

    public void headerContactInfoShouldBeVisible(String contactInfoText) {
        headerContactInfo.shouldBe(visible).shouldHave(text(contactInfoText));
    }

    public HeaderPage hoverOnCatalogTab() {
        catalogTab.hover();
        return this;
    }

    public HeaderPage hoverOnHouseholdAppliances() {
        appliancesSubMenu.hover();
        return this;
    }

    public HeaderPage hoverOnElectronics() {
        electronicsSubMenu.hover();
        return this;
    }

    public HeaderPage verifyMainTabsTextIsDisplayed() {
        homeTab.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_MAIN_PAGE_TAB));
        catalogTab.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_CATALOG_TAB));
        myAccountTab.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_MY_ACCOUNT_TAB));
        cartTab.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_CART_TAB));
        placeAnOrderTab.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_PLACE_AN_ORDER_TAB));
        return this;
    }

    public HeaderPage verifyCatalogSubmenuText() {
        appliancesSubMenu.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_APPLIANCES_TEXT));
        electronicsSubMenu.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_ELECTRONICS_TEXT));
        booksLink.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_BOOKS_TAB));
        clothesLink.shouldBe(visible).shouldHave(text(HeaderTestData.EXPECTED_CLOSES_TAB));
        return this;
    }

    public HeaderPage verifyHouseholdAppliancesText() {
        refrigeratorsLink.shouldBe(visible).shouldHave(text(EXPECTED_REFRIGERATOR_TAB));
        washingMachinesLink.shouldBe(visible).shouldHave(text(EXPECTED_WASHING_MACHINE_TAB));
        return this;
    }

    public HeaderPage verifyElectronicsSubmenuTexts() {
        phonesLink.shouldBe(visible).shouldHave(text(EXPECTED_PHONES_TAB));
        tabletsLink.shouldBe(visible).shouldHave(text(EXPECTED_TABLETS_TAB));
        tvLink.shouldBe(visible).shouldHave(text(EXPECTED_TELEVISION_TAB));
        photoVideoLink.shouldBe(visible).shouldHave(text(EXPECTED_PHOTO_VIDEO_TAB));
        watchesLink.shouldBe(visible).shouldHave(text(EXPECTED_WATCH_TAB));
        return this;
    }

    public HeaderPage scrollToCatalogTab() {
        catalogTab.shouldBe(visible, Duration.ofSeconds(7)).scrollIntoView(true);
        return this;
    }

    public HeaderPage clickOnPhoneLink() {
        phonesLink.shouldBe(visible).click();
        return this;
    }
}