package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

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
            toPayTitle = $("tr[class='order-total'] th"),
            deleteCouponButton = $(".woocommerce-remove-coupon"),
            validCouponMessage = $("div[role='alert']"),
            invalidCouponMessage = $("ul[role='alert'] li"),
            doubleApplyCouponMessage = $("ul[role='alert'] li"),
            emptyCouponMessage = $("ul[role='alert'] li"),
            placingAnOrderPageTitle = $("div#accesspress-breadcrumb span.current"),
            applyCouponButton = $x("//button[contains(text(),'Применить купон')]"),
            deleteItemFromCartButton = $("td[class='product-remove']"),
            restoreItemButton = $x("//a[@class='restore-item' and text()='Вернуть?']"),
            toPayButton = $(".checkout-button.button.alt.wc-forward"),
            deletedCouponMessage = $x("//div[@role='alert' and contains(text(), 'Купон удален')]"),
            placeAnOrderButton = $(".checkout-button.button.alt.wc-forward"),
            productNameInCart = $(".product-name");


    public void openCartPage() {
        open(CART_PAGE_URL);
    }

    public void deleteItemFromCart() {
        deleteItemFromCartButton.shouldBe(visible).click();
        cartIsEmptyMessage.shouldBe(visible, Duration.ofSeconds(5));
    }

    public SelenideElement getCartPageTitle() {
        return cartPageTitle;
    }


}