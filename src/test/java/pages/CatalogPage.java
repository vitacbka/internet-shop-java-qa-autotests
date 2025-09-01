package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.CART_PAGE_URL;
import static readproperties.ConfigProvider.CATALOG_PAGE_URL;

public class CatalogPage {
    private final SelenideElement
            pageTitle = $("#title_bread_wrap"),
            actualCategoryPageTitleText = $(".entry-title.ak-container"),
            categoryItemsList = $("#primary"),
            categoryNamesList = $("#woocommerce_product_categories-2"),
            sortDropdown = $("select.orderby option"),
            itemsNotFoundMessage = $(".woocommerce-info"),
            phonesLinkCategory = $x("//*[@id='woocommerce_product_categories-2'] //a[contains(@href, 'phones')]");

    private final ElementsCollection categoryLinks = $$("#woocommerce_product_categories-2 a");

    private final ElementsCollection
            productCategories = $$("#primary .product-category"),
            products = $$("li.product");

    public CatalogPage openCatalogPage() {
        open(CATALOG_PAGE_URL);
        return this;
    }
    public CatalogPage isOnCatalogPage(String title) {
        pageTitle.shouldBe(visible).shouldHave(text(title));
        webdriver().shouldHave(url(CATALOG_PAGE_URL));
        return this;
    }

    public CatalogPage catalogPageTitleShouldBeVisible(String title) {
        actualCategoryPageTitleText.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public String getActualOpenedCategoryPageTitle() {
        return actualCategoryPageTitleText.text();
    }

    public CatalogPage categoryNamesListShouldBeVisible(String[] expectedCategories) {
        categoryNamesList.shouldBe(visible);
        for (String category : expectedCategories) {
            categoryNamesList.shouldHave(text(category));
        }
        return this;
    }

    public CatalogPage categoryItemsListShouldBeVisible() {
        categoryItemsList.shouldBe(visible);
        categoryItemsList.shouldNotBe(empty);
        return this;
    }

    public CatalogPage selectCategory(String categoryName) {
        categoryLinks.findBy(exactText(categoryName))
                .shouldBe(visible)
                .click();

        $("ul.products").shouldBe(visible);
        return this;
    }

    public CatalogPage isSearchResultVisibleAndContainsSearchText(String searchText) {
        actualCategoryPageTitleText.shouldBe(visible)
                .shouldHave(text(searchText));
        return this;
    }

    public CatalogPage isNonExistingSearchResultVisible(String notFoundMessage) {
        itemsNotFoundMessage.shouldBe(visible).shouldHave(text(notFoundMessage));
        return this;
    }

    public CatalogPage clickPhonesLinkCategory() {
        phonesLinkCategory.shouldBe(visible).shouldHave(text("Телефоны")).click();
        return this;
    }
}