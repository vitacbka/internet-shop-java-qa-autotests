package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderPage;
import pages.MainPage;
import pages.SearchResultPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class SearchTests {
MainPage mainPage = new MainPage();
HeaderPage header = new HeaderPage();
SearchResultPage search = new SearchResultPage();

    @Test
    @DisplayName("Item should be found by name at search field")
    void itemShouldBeFoundByNameTest() {
//        mainPage.openMainPage();
//        header.searchItemByNameAtSearchInputField("Телевизор");
//
//        search.checkSearchTitle("Телевизор");
//
//        search.checkFoundItem();
//
//        search.foundElementsCountText
//                .shouldBe(visible)
//                .shouldHave(text("Показ всех"));
    }
}
