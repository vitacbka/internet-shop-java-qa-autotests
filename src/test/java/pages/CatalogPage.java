package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.CATALOG_PAGE_URL;

public class CatalogPage {
    private final SelenideElement
            catalogPageTitle = $("#title_bread_wrap"),
            categoryItemsList = $("#primary"),
            categoryNamesList = $("#woocommerce_product_categories-2"),
            sortDropdown = $("select.orderby"),
            successMessage = $(".woocommerce-message");

    private final ElementsCollection categoryLinks = $$("#woocommerce_product_categories-2 a");

    private final ElementsCollection
            productCategories = $$("#primary .product-category"),
            products = $$("li.product");

    public CatalogPage openCatalogPage() {
        open(CATALOG_PAGE_URL);
        return this;
    }
    public CatalogPage catalogPageTitleShouldBeVisible(String title) {
        catalogPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
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
        // Для точного совпадения текста используем exactText
        $("#woocommerce_product_categories-2").$("a")
                .shouldBe(visible, enabled)
                .click();

        $("ul.products").shouldBe(visible);
        return this;
    }
    public void sortProducts(String sortOption) {
        sortDropdown.selectOption(sortOption);
    }

    public String getProductNameByIndex(int index) {
        return products.get(index).$(".woocommerce-loop-product__title").text();
    }

    public void addProductToCart(int index) {
        products.get(index).$(".add_to_cart_button").click();
    }

    public void viewCartDetails(int index) {
        products.get(index).$(".added_to_cart").click();
    }

    public int getProductsCount() {
        return products.size();
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }
}