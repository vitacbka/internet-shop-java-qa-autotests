package helpers;

import com.codeborne.selenide.ElementsCollection;
import pages.CartPage;
import pages.CatalogPage;
import pages.HeaderPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductAddToCartHelper {
    HeaderPage header = new HeaderPage();
    CartPage cart = new CartPage();
    CatalogPage catalogPage = new CatalogPage();
    private List<String> addedProductNames = new ArrayList<>();

    ElementsCollection availableAddToCartButtons = $$(".product.instock .add_to_cart_button");
    ElementsCollection productNameElements = $$(".product.instock h3");

    public void addPhoneToCart() {
        header.clickCartTab();
        if (cart.removeCouponButton.exists()) {
            cart.clickRemoveCouponButton();
        }
        header
                .scrollToCatalogTab()
//                .scrollToCatalogTab()
                .clickCatalogTab();
        catalogPage.clickPhonesLinkCategory();
//                .hoverOnCatalogTab()
//                .hoverOnElectronics()
//                .clickOnPhoneLink();

        availableAddToCartButtons.get(2).click();
        addedProductNames.add(productNameElements.get(2).getText());
    }

    public void addTwoPhonesToCart() {
        header.cartTab.shouldBe(visible).click();
        if (cart.removeCouponButton.exists()) {
            cart.clickRemoveCouponButton();
        }
        header
                .scrollToCatalogTab()
                .clickCatalogTab();
//        header.catalogTab.shouldBe(visible, Duration.ofSeconds(7));
//        header.catalogTab.scrollIntoView(true);
//        header.hoverOnCatalogTab();
//        header.hoverOnElectronics();
        catalogPage.clickPhonesLinkCategory();

        String firstProductName = productNameElements.get(2).getText();
        String secondProductName = productNameElements.get(3).getText();

        availableAddToCartButtons.get(2).click();
        availableAddToCartButtons.get(3).click();
        // Сохраняем названия
        addedProductNames.add(firstProductName);
        addedProductNames.add(secondProductName);
    }

    public List<String> getAddedProductNames() {
        return new ArrayList<>(addedProductNames);
    }

    public void clearAddedProductNames() {
        addedProductNames.clear();
    }
}