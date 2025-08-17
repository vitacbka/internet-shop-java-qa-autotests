package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.CATALOG_PAGE_URL;

public class CatalogPage {
    private final SelenideElement
            pageTitle = $("#title_bread_wrap"),
            actualCategoryPageTitleText = $(".entry-title.ak-container"),
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
        actualCategoryPageTitleText.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public CatalogPage pageTitleGetText() {
        pageTitle.text();
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

    public CatalogPage isSearchResultVisibleAndContainsSearchText(String searchText) {
        actualCategoryPageTitleText.shouldBe(visible)
                .shouldHave(text(searchText));
        return this;
    }
}