package tests;

import helpers.SearchFieldHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CatalogPage;
import pages.MainPage;
import static testdata.CatalogPageTestData.*;
import static testdata.MainPageTestData.EXPECTED_MAIN_PAGE_TITLE_TEXT;

public class SearchTests extends BaseTest {
    SearchFieldHelper searchFieldHelper = new SearchFieldHelper();
    CatalogPage catalogPage = new CatalogPage();
    MainPage mainPage = new MainPage();

    @BeforeEach
    void setUp() {
        mainPage
                .openMainPage()
                .isOnMainPage(EXPECTED_MAIN_PAGE_TITLE_TEXT);
    }

    @Test
    @DisplayName("Product should be find by name \"Телевизор\" at search in footer by name and show in catalog")
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
