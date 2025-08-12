package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.CartPage;
import pages.HeaderPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductAddToCartHelper {
    HeaderPage header = new HeaderPage();
    CartPage cart = new CartPage();

    ElementsCollection availableAddToCartButtons = $$(".product.instock .add_to_cart_button");

    public ProductAddToCartHelper addPhoneAtCart() {
        header.cartTab.click();
        if (cart.removeCouponButton.isDisplayed()) {
            cart.clickRemoveCouponButton();
        }

        header.catalogTab.shouldBe(visible, Duration.ofSeconds(7));
        header.catalogTab.scrollIntoView(true);
        header.hoverOnCatalogTab();
        header.hoverOnElectronics();
        header.phonesLink.click();

        availableAddToCartButtons.get(2).click();
        return this;
    }
}
