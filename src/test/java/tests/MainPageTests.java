package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.BooksCategoryPage;
import pages.CamerasCategoryPage;
import pages.MainPage;
import pages.TabletsCategoryPage;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.sleep;
import static testdata.MainPageTestData.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTests extends BaseTest{
    MainPage mainPage = new MainPage();
    BooksCategoryPage booksCategoryPage = new BooksCategoryPage();
    TabletsCategoryPage tabletsCategoryPage = new TabletsCategoryPage();
    CamerasCategoryPage camerasCategoryPage = new CamerasCategoryPage();

    @BeforeEach
    void setup() {
        mainPage
                .openMainPage()
                .isOnMainPage(EXPECTED_MAIN_PAGE_TITLE_TEXT);
    }

    private Stream<Arguments> slideDataProvider() {
        return Stream.of(
                Arguments.of(mainPage.booksSlide, EXPECTED_BOOKS_SLIDE_TITLE,
                        booksCategoryPage.booksPageTitle, EXPECTED_BOOKS_PAGE_TITLE),
                Arguments.of(mainPage.tabletsSlide, EXPECTED_TABLETS_SLIDE_TITLE,
                        tabletsCategoryPage.tabletsPageTitle, EXPECTED_TABLETS_PAGE_TITLE),
                Arguments.of(mainPage.camerasSlide, EXPECTED_CAMERAS_SLIDE_TITLE,
                        camerasCategoryPage.camerasPageTitle, EXPECTED_CAMERAS_PAGE_TITLE)
        );
    }

    @ParameterizedTest
    @MethodSource("slideDataProvider")
    @DisplayName("Click on slide should open the corresponding page")
    void clickOnSlideShouldOpenCorrespondingPageTest(SelenideElement slide,
                                                     String expectedSlideTitle,
                                                     SelenideElement pageTitleElement,
                                                     String expectedPageTitle)
    {
        mainPage
                .openMainPage()
                .clickOnSlide(slide, expectedSlideTitle)
                .verifyPageTitleText(pageTitleElement, expectedPageTitle);
        pageTitleElement.shouldBe(Condition.visible).shouldHave(Condition.text(expectedPageTitle));
    }

    private Stream<Arguments> labelsDataProvider() {
        return Stream.of(
                Arguments.of(mainPage.saleCategoryTitle, EXPECTED_SALE_CATEGORY_TITLE,
                        mainPage.saleLabels,  EXPECTED_SALE_LABEL_TEXT),
                Arguments.of(mainPage.newArrivalTitle,  EXPECTED_NEW_ARRIVAL_CATEGORY_TITLE,
                        mainPage.newItemLabels, EXPECTED_NEW_LABEL_TEXT)
        );
    }

    @ParameterizedTest
    @MethodSource("labelsDataProvider")
    @DisplayName("Sale label and new arrivals label should be visible")
    void labelsShouldBeVisibleTest(SelenideElement sectionTitle,
                                   String expectedSectionTitle,
                                   ElementsCollection labels,
                                   String labelText) {
        mainPage
                .openMainPage()
                .scrollToElement(sectionTitle)
                .verifyElementText(sectionTitle, expectedSectionTitle)
                .labelsShouldBeVisible(labels, labelText);
    }

    @Test
    @DisplayName("Sale label should be visible at sale category")
    void saleLabelShouldBeVisibleTest() {
        mainPage
                .scrollToElement(mainPage.saleCategoryTitle)
                .verifyElementText(mainPage.saleCategoryTitle, EXPECTED_SALE_CATEGORY_TITLE)
                .labelsShouldBeVisible(mainPage.saleLabels, EXPECTED_SALE_LABEL_TEXT);
    }

    @Test
    @DisplayName("New arrivals label should be visible at new arrivals category")
    void newArrivalsLabelShouldBeVisibleTest() {
        mainPage
                .scrollToElement(mainPage.newArrivalTitle)
                .labelsShouldBeVisible(mainPage.newItemLabels, EXPECTED_NEW_LABEL_TEXT);
    }

    @Test
    @DisplayName("Click on next slide should change slide")
    void clickOnNextSlideShouldChangeSlideTest() {
        mainPage
                .scrollToElement(mainPage.saleCategoryTitle)
                .clickSaleSliderNextArrow();
        sleep(5000);
    }
}