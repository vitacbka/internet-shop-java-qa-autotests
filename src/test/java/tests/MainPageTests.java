package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import pages.BooksCategoryPage;
import pages.CamerasCategoryTitle;
import pages.MainPage;
import pages.TabletsCategoryPage;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static testdata.MainPageTestData.*;

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

    @Test
    @DisplayName("Main page title should be visible")
    void mainPageTitleshouldBEVisibleTest() {
        mainPage
                .mainPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_TITLE_TEXT));
    }

    @Test
    @DisplayName("Click on books slide on main page and check books that title is visible")
    void clickOnBooksSlideTest() {
        mainPage
                .booksSlide
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_BOOKS_SLIDE_TITLE))
                .click();
        booksCategoryPage
                .booksPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_BOOKS_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on tablets slide on main page and check that tablets page title is visible")
    void clickOnTabletsSlideTest() {
        mainPage
                .tabletsSlide
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_TABLETS_SLIDE_TITLE))
                .click();
        tabletsCategoryPage
                .tabletsPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_TABLETS_PAGE_TITLE));
    }

    @Test
    @DisplayName("Click on cameras slide on main page and check that cameras page title is visible")
    void clickOnCamerasSlideTest() {
        mainPage
                .camerasSlide
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_CAMERAS_SLIDE_TITLE))
                .click();

        camerasCategoryTitle
                .camerasPageTitle
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_CAMERAS_PAGE_TITLE));
    }



    @Test
    @DisplayName("Contact information should be visible at footer")
    void contactInformationShouldBeVisibleTest() {
        mainPage
                .footerContactInfo
                .scrollTo()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_FOOTER_TEXT));
    }

    @Test
    @DisplayName("Sale label should be visible at sale category")
    void saleLabelShouldBeVisibleTest() {
        mainPage.saleCategoryTitle
                .scrollIntoView("{behavior: 'smooth', block: 'center'}")
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_SALE_CATEGORY_TITLE));
        mainPage
                .saleLabels
                .filterBy(Condition.visible)
                .findBy(text(EXPECTED_SALE_LABEL))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("New arrivals label should be visible at new arrivals category")
    void newArrivalsLabelShouldBeVisibleTest() {
        mainPage.newArrivalTitle
                .scrollTo()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_NEW_ARRIVAL_CATEGORY_TITLE));


        mainPage.newItemLabels
                .filter(Condition.visible)
                .findBy(text(EXPECTED_NEW_LABEL))
                .shouldBe(visible);
    }
}