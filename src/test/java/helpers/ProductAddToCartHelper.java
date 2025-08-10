package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.HeaderPage;
import pages.MainPage;
import pages.SearchResultPage;
import pages.TvCategoryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.TV_CATEGORY_URL;

public class ProductAddToCartHelper {

    SelenideElement productsGrid = $(".products.columns-4");

    ElementsCollection availableAddToCartButtons = $$(".product.instock .add_to_cart_button");

    public ProductAddToCartHelper addFIrstAvaible;
}
