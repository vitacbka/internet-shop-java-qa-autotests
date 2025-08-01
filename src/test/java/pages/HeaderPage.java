package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HeaderPage {

    public SelenideElement
            mainMenu = $("ul#menu-primary-menu"),
            homeTab = $("li#menu-item-26 a"),
            catalogTab = $("li#menu-item-46 a"),
            accountTab = $("li#menu-item-30 a"),
            cartTab = $("li#menu-item-29 a"),
            placeAnOrder = $("li#menu-item-31 a");
    

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




    public void verifyMainTabsAreVisible() {
        mainMenu.shouldBe(visible);
        homeTab.shouldBe(visible);
        catalogTab.shouldBe(visible);
        accountTab.shouldBe(visible);
        cartTab.shouldBe(visible);
        placeAnOrder.shouldBe(visible);
    }


}