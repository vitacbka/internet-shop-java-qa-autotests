package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.BASE_URL;
import static readproperties.ConfigProvider.BOOKS_CATEGORY_URL;
import static testdata.MainPageTestData.EXPECTED_BOOKS_PAGE_TITLE;
import static testdata.MainPageTestData.EXPECTED_MAIN_PAGE_TITLE_TEXT;

public class MainPage {

    public final SelenideElement
            mainPageTitle = $(".site-text"),
            booksSlide = $("#accesspress_storemo-2 .widget-title"),
            tabletsSlide = $("#accesspress_storemo-3 .widget-title"),
            camerasSlide = $("#accesspress_storemo-4 .widget-title"),
            saleCategoryTitle = $("#accesspress_store_product-2 h2.prod-title"),
            newArrivalTitle = $x("(//h2[@class='prod-title'])[2]");

    public ElementsCollection
            saleLabels = $$x("//aside[@id='accesspress_store_product-2']//li[not(contains(@class, 'slick-cloned'))]//span[@class='onsale']"),
            newItemLabels = $$("aside#accesspress_store_product-3 ul.new-prod-slide li .label-new");


    public void openMainPage() {
        open(BASE_URL);
    }

    public void isOnMainPage() {
        webdriver().shouldHave(url(BASE_URL));
        mainPageTitle.shouldBe(visible).shouldHave(text(EXPECTED_MAIN_PAGE_TITLE_TEXT));
    }

    public void clickOnSlide(SelenideElement element, String expectedTitle) {
        element.shouldBe(visible).shouldHave(text(expectedTitle)).click();
    }

    public void scrollToElement(SelenideElement element, String expectedText) {
        element.scrollIntoView("{behavior: 'smooth', block: 'center'}")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
    }
}