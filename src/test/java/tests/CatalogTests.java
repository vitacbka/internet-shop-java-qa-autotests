package tests;

import helpers.SearchFieldHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CatalogPage;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static testdata.CatalogPageTestData.*;

public class CatalogTests extends BaseTest {
    CatalogPage catalogPage = new CatalogPage();
    SearchFieldHelper searchFieldHelper = new SearchFieldHelper();

    @BeforeEach
    void setUp() {
        catalogPage
                .openCatalogPage()
                .catalogPageTitleShouldBeVisible(CATALOG_PAGE_TITLE);
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

    @Test
    @DisplayName("Product should be find by name \"Телевизор\" at sarch in footer by name and show in catalog")
    void productShouldBeFindByNameTest() {
        searchFieldHelper.searchProductByName(TELEVISION_TEXT_FOR_SEARCH);
        catalogPage.isSearchResultVisibleAndContainsSearchText(TELEVISION_TEXT_FOR_SEARCH);
    }

    @Test
    @DisplayName("Product should not be found with non-existent name")
    void shouldNotFindProductWithNonExistentNameTest() {
        searchFieldHelper.searchProductByName(NON_EXISTENT_TEXT_FOR_SEARCH);
        catalogPage.isNonExistingSearchResultVisible(EXPECTED_NOT_FOUND_ITEMS_TEXT);
    }
}
