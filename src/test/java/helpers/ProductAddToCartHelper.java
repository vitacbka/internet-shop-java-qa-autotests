package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.HeaderPage;

import static com.codeborne.selenide.Selenide.*;

public class ProductAddToCartHelper {
    HeaderPage header = new HeaderPage();

    SelenideElement productsGrid = $(".products.columns-4");

    ElementsCollection availableAddToCartButtons = $$(".product.instock .add_to_cart_button");

    public ProductAddToCartHelper addPhoneAtCart() {
        header.hoverOnCatalogTab();
        header.hoverOnElectronics();
        header.phonesLink.click();

        availableAddToCartButtons.get(2).click();
        return this;
    }
}
