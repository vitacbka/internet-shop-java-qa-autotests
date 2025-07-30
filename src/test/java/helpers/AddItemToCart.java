package helpers;

import com.codeborne.selenide.SelenideElement;
import pages.HeaderPage;
import pages.MainPage;
import pages.SearchResultPage;
import pages.TvCategoryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.TV_CATEGORY_URL;

public class AddItemToCart {

    private final SelenideElement
    addToCartThirdItemButton = $("ul.products li.product:nth-of-type(3) a.add_to_cart_button"),
    viewDetailsButton = $("ul.products li.product:nth-of-type(3) a.added_to_cart");

    TvCategoryPage tvCategory = new TvCategoryPage();
    SearchResultPage result = new SearchResultPage();

        public void searchAndAddItemToCart() {
            open(TV_CATEGORY_URL);
            tvCategory.checkTvCategoryTitle("Телевизоры");
            result.checkFoundItem();
            addToCartThirdItemButton.shouldBe(visible).click();
            viewDetailsButton.shouldBe(visible).click();
    }
}
