package helpers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.MainPage;
import pages.SearchResultPage;
import pages.TvCategoryPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.BASE_URL;
import static readproperties.ConfigProvider.TV_CATEGORY_URL;

public class AddItemToCart {
    private final SelenideElement
            productsGrid = $(".products.columns-4"),
            readMoreButton = $(".added_to_cart");

    ElementsCollection availableAddToCartButtons = $$(".product.instock .add_to_cart_button");

    public void addFirstAvailableItemToCart() {
        open(TV_CATEGORY_URL);
        if (!productsGrid.is(visible)) {
            throw new RuntimeException("Страница с телевизорами не загружена или пуста.");
        }
        if (availableAddToCartButtons.isEmpty()) {
            throw new RuntimeException("Нет доступных для заказа телевизоров. Все товары отсутствуют в наличии.");
        }
        availableAddToCartButtons.first()
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();
        readMoreButton
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();
    }
}
