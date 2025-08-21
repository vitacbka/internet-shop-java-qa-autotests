package helpers;

import pages.HeaderPage;

import static com.codeborne.selenide.Condition.visible;
import static org.openqa.selenium.Keys.ENTER;

public class SearchFieldHelper {

    HeaderPage headerPage = new HeaderPage();

    public void searchProductByName(String productName) {
        headerPage.searchField.shouldBe(visible)
                .setValue(productName)
                .pressEnter();
    }
}
