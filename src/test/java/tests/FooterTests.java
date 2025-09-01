package tests;

import com.codeborne.selenide.WebDriverRunner;
import helpers.ProductAddToCartHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.FooterPage;
import pages.MainPage;
import pages.PlaceAnOrderPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Named.named;
import static readproperties.ConfigProvider.*;
import static testdata.CartPageTestData.EXPECTED_CART_PAGE_TITLE;
import static testdata.FooterTestData.*;
import static testdata.PlaceAnOrderTestData.EXPECTED_PLACE_AN_ORDER_PAGE_TITLE;

public class FooterTests extends BaseTest {
    MainPage mainPage = new MainPage();
    FooterPage footerPage = new FooterPage();
    ProductAddToCartHelper addToCartHelper = new ProductAddToCartHelper();
    PlaceAnOrderPage placeAnOrderPage = new PlaceAnOrderPage();
    CartPage cartPage = new CartPage();

    @BeforeEach
    void openMainPage() {
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Contact information in footer should be displayed")
    void checkContactInfoInFooter() {
        footerPage.verifyContactInfoFooter(EXPECTED_FOOTER_TEXT);
    }

    @Test
    @DisplayName("All page links should be displayed at footer")
    void allFooterPageLinksShouldBeVisibleAndCorrectText() {
        footerPage.verifyVisibleAllFooterLinks(All_FOOTER_TEXTS_LINKS);
    }

    @Test
    @DisplayName("Widget Links title should be displayed at footer")
    void widgetLinksTitleShouldBeDisplayedAtFooter() {
        footerPage.verifyWidgetLinkDisplayed(WIDGET_LINKS_TITLE);
    }

    static Stream<Arguments> footerLinksProvider() {
        return Stream.of(
                Arguments.of("Все товары", ALL_ITEMS_PAGE_URL),
                Arguments.of("Главная", BASE_URL),
                Arguments.of("Корзина", CART_PAGE_URL),
                Arguments.of("Мой аккаунт", MY_ACCOUNT_PAGE_URL),
                Arguments.of("Регистрация", REGISTRATION_PAGE_URL)
        );
    }

    @ParameterizedTest
    @MethodSource("footerLinksProvider")
    @DisplayName("Should navigate to correct page when clicking footer link")
    public void shouldNavigateToExpectedPageTest(String linkText, String expectedUrl) {
        footerPage.footerWidgetLinksTitle.scrollIntoView(true);
        $(byText(linkText)).click();
        assertEquals(expectedUrl, WebDriverRunner.url());
    }

    static Stream<Arguments> checkoutLinkProvider() {
        return Stream.of(
                Arguments.arguments(named("Item in cart", true)),
                Arguments.arguments(named("Cart is empty", false))
        );
    }

    @ParameterizedTest
    @MethodSource("checkoutLinkProvider")
    @DisplayName("Should redirect from checkout to cart if empty, or open checkout if has items")
    void shouldRedirectCheckoutBasedOnCartState(boolean isCartNotEmpty) {
        footerPage.footerWidgetLinksTitle.scrollIntoView(true);
        if (isCartNotEmpty) {
            addToCartHelper.addPhoneToCart();
        }

        footerPage.clickOnPlaceAnOrderLink();

        if (isCartNotEmpty) {
            placeAnOrderPage.isOnPlaceAnOrderPage(EXPECTED_PLACE_AN_ORDER_PAGE_TITLE, PLACE_AN_ORDER_URL);
        } else {
            cartPage.isOnCartPage(EXPECTED_CART_PAGE_TITLE);
        }
    }
}