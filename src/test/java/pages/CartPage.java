package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.*;

public class CartPage {

    public final SelenideElement
            cartPageTitle = $("span.current"),
            cartIsEmptyMessage = $("p.cart-empty"),
            itemNameInCart = $(".woocommerce-cart-form__cart-item"),
            toPayPrice = $("tr[class='order-total'] bdi:nth-child(1)"),
            couponInputField = $("#coupon_code"),
            removeCouponButton = $(".woocommerce-remove-coupon"),
            placingAnOrderPageTitle = $("div#accesspress-breadcrumb span.current"),
            applyCouponButton = $x("//button[contains(text(),'Применить купон')]"),
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

    public ElementsCollection removeItemFromCartButtons = $$("a[aria-label='Remove this item']");

    public CartPage openCartPage() {
        open(CART_PAGE_URL);
        return this;
    }

    public CartPage isOnCartPage(String title, String expectedUrl) {
        cartPageTitle.shouldBe(visible).shouldHave(text(title));
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    public CartPage itemInCartShouldBeVisible(String itemName) {
        itemNameInCart.shouldBe(visible).shouldHave(text(itemName));
        return this;
    }

    public String getItemNameInCart() {
        return itemNameInCart.shouldBe(visible).getText();
    }

    public CartPage clickOnRestoreItemButton() {
        restoreItemButton.shouldBe(visible).click();
        return this;
    }

    public CartPage removeItemFromCart() {
        ElementsCollection removeButtons = $$("a[aria-label='Remove this item']");

        while (!removeButtons.isEmpty()) {
            int initialSize = removeButtons.size();

            SelenideElement button = removeButtons.first();
            button.scrollTo().click();
            // Ждём, что количество кнопок уменьшилось
            removeButtons.shouldHave(size(initialSize - 1), Duration.ofSeconds(5));
        }
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
        Wait().withTimeout(Duration.ofSeconds(10))
                .until(driver -> getTotalPriceAsDouble() != oldPrice);
        return this;
    }

    public CartPage successfulCouponMessageShouldBeVisible(String message) {
        successfulCouponMessage.shouldBe(visible).shouldHave(text(message));
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

    public CartPage cartIsEmptyMessageShouldBeVisible(String message) {
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