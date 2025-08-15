package pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.*;

public class CartPage {

    public final SelenideElement
            cartPageTitle = $("span.current"),
            cartIsEmptyMessage = $("p.cart-empty"),
            itemInCart = $(".woocommerce-cart-form__cart-item"),
            toPayPrice = $("tr[class='order-total'] bdi:nth-child(1)"),
            couponInputField = $("#coupon_code"),
            removeCouponButton = $(".woocommerce-remove-coupon"),
            placingAnOrderPageTitle = $("div#accesspress-breadcrumb span.current"),
            applyCouponButton = $x("//button[contains(text(),'Применить купон')]"),
            removeItemFromCartButton = $("a[aria-label='Remove this item']"),
            restoreItemButton = $x("//a[@class='restore-item' and text()='Вернуть?']"),
            toPayButton = $(".checkout-button.button.alt.wc-forward"),
            placeAnOrderButton = $(".checkout-button.button.alt.wc-forward"),
            productNameInCart = $x("(//*[@class='product-name'])[2]"),

    //Coupon messages locators
            successfulCouponMessage = $(".woocommerce-message"),
            emptyCouponMessage = $("ul[role='alert'] li"),
            errorCouponMessage = $(".woocommerce-error li"),
            couponAlreadyAppliedMessage = $(".woocommerce-error li"),
            deletedCouponMessage = $x("//div[@role='alert' and contains(text(), 'Купон удален')]");

    public void openCartPage() {
        open(CART_PAGE_URL);
    }

    public void cartPageTitleShouldBeVisible(String title) {
        cartPageTitle.shouldBe(visible).shouldHave(text(title));
    }

    public void clearCart() {
        removeItemFromCartButton.shouldBe(visible).click();
    }

    public SelenideElement getCartPageTitle() {
        return cartPageTitle;
    }

    public void setCouponData(String coupon) {
        couponInputField
                .shouldBe(visible)
                .setValue(coupon);
    }

    public void clickOnApplyCouponButton() {
        applyCouponButton.shouldBe(visible).click();
    }

    public void clickRemoveCouponButton() {
        removeCouponButton.shouldBe(visible).click();
    }

    public double getTotalPriceAsDouble() {
        String priceText = toPayPrice.text()
                .replaceAll("[^\\d,]", "")
                .replace(",", ".");

        return Double.parseDouble(priceText);
    }

    public void twiceApplyValidCoupon(String coupon) {
        couponInputField
                .shouldBe(visible)
                .setValue(coupon);
        clickOnApplyCouponButton();
        couponInputField
                .shouldBe(visible)
                .clear();
        couponInputField.setValue(coupon);
        clickOnApplyCouponButton();
    }

    public void waitForPriceChange(double oldPrice) {
        toPayPrice.shouldNotHave(text(String.valueOf(oldPrice)), Duration.ofSeconds(10));
    }

    public void successfulCouponMessage (String message) {
        successfulCouponMessage.shouldBe(visible).shouldHave(text(message));
    }

    public void couponMessage(SelenideElement element, String expectedText) {
        element
                .shouldBe(visible)
                .shouldHave(text(expectedText));
    }

    public void cartIsEmptyMessage(String message) {
        cartIsEmptyMessage.shouldBe(visible).shouldHave(text(message));
    }

    public void successfulApplyCouponMessage(String message) {
        successfulCouponMessage.shouldBe(visible).shouldHave(text(message));
    }
}