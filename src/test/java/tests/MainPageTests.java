package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.BooksCategoryPage;
import pages.CamerasCategoryTitle;
import pages.MainPage;
import pages.TabletsCategoryPage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static readproperties.ConfigProvider.BASE_URL;
import static testdata.MainPageTestData.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTests {
    MainPage mainPage = new MainPage();
    BooksCategoryPage booksCategoryPage = new BooksCategoryPage();
    TabletsCategoryPage tabletsCategoryPage = new TabletsCategoryPage();
    CamerasCategoryTitle camerasCategoryTitle = new CamerasCategoryTitle();

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setup() {
        mainPage.openMainPage();
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    private Stream<Arguments> slideDataProvider() {
        return Stream.of(
                Arguments.of(mainPage.booksSlide, EXPECTED_BOOKS_SLIDE_TITLE, booksCategoryPage.booksPageTitle, EXPECTED_BOOKS_PAGE_TITLE),
                Arguments.of(mainPage.tabletsSlide, EXPECTED_TABLETS_SLIDE_TITLE, tabletsCategoryPage.tabletsPageTitle, EXPECTED_TABLETS_PAGE_TITLE),
                Arguments.of(mainPage.camerasSlide, EXPECTED_CAMERAS_SLIDE_TITLE, camerasCategoryTitle.camerasPageTitle, EXPECTED_CAMERAS_PAGE_TITLE)
        );
    }

    @ParameterizedTest
    @MethodSource("slideDataProvider")
    @DisplayName("Click on slide should open the corresponding page")
    void clickOnSlideShouldOpenCorrespondingPageTest(SelenideElement slide, String expectedSlideTitle, SelenideElement pageTitleElement, String expectedPageTitle) {
        mainPage.clickOnSlide(slide, expectedSlideTitle);
        pageTitleElement.shouldBe(Condition.visible).shouldHave(Condition.text(expectedPageTitle));
    }



    @Test
    @DisplayName("Main page title should be visible")
    void mainPageTitleShouldBEVisibleTest() {
        mainPage
                .mainPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_TITLE_TEXT));
    }

    @Test
    @DisplayName("Sale label should be visible at sale category")
    void saleLabelShouldBeVisibleTest() {
        mainPage.scrollToElement(mainPage.saleCategoryTitle, EXPECTED_SALE_CATEGORY_TITLE);

        mainPage
                .saleLabels
                .filterBy(Condition.visible)
                .findBy(text(EXPECTED_SALE_LABEL))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("New arrivals label should be visible at new arrivals category")
    void newArrivalsLabelShouldBeVisibleTest() {
        mainPage.scrollToElement(mainPage.newArrivalTitle, EXPECTED_NEW_ARRIVAL_CATEGORY_TITLE);
        mainPage.newItemLabels
                .filter(Condition.visible)
                .findBy(text(EXPECTED_NEW_LABEL))
                .shouldBe(visible);
    }
}