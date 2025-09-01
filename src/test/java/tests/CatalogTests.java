package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CatalogPage;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static readproperties.ConfigProvider.CATALOG_PAGE_URL;
import static testdata.CatalogPageTestData.ALL_CATEGORIES;
import static testdata.CatalogPageTestData.CATALOG_PAGE_TITLE;

public class CatalogTests extends BaseTest {
    CatalogPage catalogPage = new CatalogPage();

    @BeforeEach
    void setUp() {
        catalogPage
                .openCatalogPage()
                .isOnCatalogPage(CATALOG_PAGE_TITLE);
    }

    static Stream<String> allCategoriesProvider() {
        return Stream.of(ALL_CATEGORIES);
    }

    @ParameterizedTest
    @MethodSource("allCategoriesProvider")
    @DisplayName("All categories should display product cards and title of category (using method source)")
    void allCategoriesShouldDisplayProductsUsingMethodSource(String categoryName) {
        catalogPage
                .openCatalogPage()
                .selectCategory(categoryName)
                .catalogPageTitleShouldBeVisible(categoryName)
                .categoryItemsListShouldBeVisible();
        assertThat(catalogPage.getActualOpenedCategoryPageTitle().toLowerCase())
                .as("Проверка соответствия заголовка категории")
                .isEqualTo(categoryName.toLowerCase());
    }
}
