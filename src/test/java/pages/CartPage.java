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

    public CartPage openCartPage() {
        open(CART_PAGE_URL);
        return this;
    }

    public CartPage cartPageTitleShouldBeVisible(String title) {
        cartPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public CartPage clearCart() {
        removeItemFromCartButton.shouldBe(visible).click();
        return this;
    }

    public CartPage productNameInCartShouldBeVisible() {
        productNameInCart.shouldBe(visible);
        return this;
    }

    public CartPage setCouponData(String coupon) {
        couponInputField
                .shouldBe(visible)
                .setValue(coupon);
        return this;
    }

    public CartPage clickOnApplyCouponButton() {
        applyCouponButton.shouldBe(visible).click();
        return this;
    }

    public CartPage clickRemoveCouponButton() {
        removeCouponButton.shouldBe(visible).click();
        return this;
    }

    public double getTotalPriceAsDouble() {
        String priceText = toPayPrice.text()
                .replaceAll("[^\\d,]", "")
                .replace(",", ".");

        return Double.parseDouble(priceText);
    }

    public CartPage twiceApplyValidCoupon(String coupon) {
        couponInputField
                .shouldBe(visible)
                .setValue(coupon);
        clickOnApplyCouponButton();
        couponInputField
                .shouldBe(visible)
                .clear();
        couponInputField.setValue(coupon);
        clickOnApplyCouponButton();
        return this;
    }

    public CartPage waitForPriceChange(double oldPrice) {
        toPayPrice.shouldNotHave(text(String.valueOf(oldPrice)), Duration.ofSeconds(10));
        return this;
    }

    public CartPage successfulCouponMessageShouldBeVisible(String message) {
        successfulCouponMessage.shouldBe(visible).shouldHave(text(message));
        return this;
    }
    public CartPage errorCouponMessageShouldBeVisible(String message) {
        errorCouponMessage.shouldBe(visible)
                .shouldHave(text(message));
        return this;
    }

    public CartPage invalidCouponMessageShouldBeVisible(String message) {
        errorCouponMessage.shouldBe(visible);
        return this;
    }

    public CartPage couponMessage(SelenideElement element, String expectedText) {
        element.shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public CartPage emptyCouponMessageShouldBeVisible(String message) {
        emptyCouponMessage.shouldBe(visible);
        return this;
    }

    public CartPage deletedCouponMessageShouldBeVisible(String message) {
        deletedCouponMessage.shouldBe(visible);
        return this;
    }

    public CartPage cartIsEmptyMessage(String message) {
        cartIsEmptyMessage.shouldBe(visible).shouldHave(text(message));
        return this;
    }

    public CartPage alreadyApplyCouponMessageShouldBeVisible(String message) {
        couponAlreadyAppliedMessage.shouldBe(visible);
        return this;
    }

    public CartPage successfulApplyCouponMessage(String message) {
        successfulCouponMessage.shouldBe(visible).shouldHave(text(message));
        return this;
    }
}