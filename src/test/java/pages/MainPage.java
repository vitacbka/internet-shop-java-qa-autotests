package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.BASE_URL;

public class MainPage {

    public final SelenideElement
            mainPageTitle = $(".site-text"),
            booksSlide = $("#accesspress_storemo-2 .widget-title"),
            tabletsSlide = $("#accesspress_storemo-3 .widget-title"),
            camerasSlide = $("#accesspress_storemo-4 .widget-title"),
            saleCategoryTitle = $("#accesspress_store_product-2 h2.prod-title"),
            newArrivalTitle = $x("(//h2[@class='prod-title'])[2]"),

    // Локаторы для стрелок
             saleSliderNextArrow = $("#accesspress_store_product-2 .slick-next"),
             saleSliderPrevArrow = $("#accesspress_store_product-2 .slick-prev"),
             newArrivalSliderNextArrow = $("#accesspress_store_product-3 .slick-next"),
             newArrivalSliderPrevArrow = $("#accesspress_store_product-3 .slick-prev");


    public ElementsCollection
            saleLabels = $$("aside#accesspress_store_product-2 li:not(.slick-cloned) span.onsale"),
            newItemLabels = $$("aside#accesspress_store_product-3 li:not(.slick-cloned) span.label-new");


    public MainPage openMainPage() {
        open(BASE_URL);
        return this;
    }

    public MainPage verifyElementText(SelenideElement element, String title) {
        element.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public MainPage isOnMainPage(String title) {
        webdriver().shouldHave(url(BASE_URL));
        mainPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public MainPage clickOnSlide(SelenideElement element, String expectedTitle) {
        element.shouldBe(visible).shouldHave(text(expectedTitle)).click();
        return this;
    }

    public MainPage scrollToElement(SelenideElement element) {
        element.scrollIntoView("{behavior: 'instant', block: 'center'}")
                .shouldBe(visible);
        return this;
    }

    public MainPage verifyPageTitleText(SelenideElement pageTitleElement, String title) {
        pageTitleElement.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public MainPage labelsShouldBeVisible(ElementsCollection elementsCollection, String labelText) {
        elementsCollection.filterBy(Condition.visible)
                .findBy(text(labelText))
                .shouldBe(visible);
        return this;
    }

    public void hoverAndClickSlider(SelenideElement slider, SelenideElement arrow) {
        slider.hover();
        arrow.shouldBe(visible, enabled).click();
    }

    public MainPage clickSaleSliderNextArrow() {
        hoverAndClickSlider(saleSliderNextArrow, saleSliderNextArrow );
        return this;
    }

    public MainPage clickSaleSlidePrevArrow() {
        hoverAndClickSlider(saleSliderPrevArrow, saleSliderPrevArrow);
        return this;
    }
}