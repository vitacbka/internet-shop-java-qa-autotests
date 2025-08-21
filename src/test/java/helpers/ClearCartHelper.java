package helpers;

import pages.CartPage;
import pages.HeaderPage;

import static com.codeborne.selenide.Condition.visible;

public class ClearCartHelper {
    HeaderPage header = new HeaderPage();
    CartPage cart = new CartPage();

    public ClearCartHelper removeCouponAndClearCart() {
        header.cartTab.click();
        if (cart.removeCouponButton.isDisplayed()) {
            cart.clickRemoveCouponButton();
        }
        if (!cart.cartIsEmptyMessage.isDisplayed()) {
            cart.removeItemFromCart();
        }
        cart.cartIsEmptyMessage.shouldBe(visible);
        return this;
    }

}