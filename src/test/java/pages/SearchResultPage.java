package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

   public final SelenideElement
           searchResultTitle = $("#title_bread_wrap h1"),
           foundElementsCountText = $(".woocommerce-result-count");

   public final ElementsCollection foundItems = $$(".wc-products .product");

   public void checkSearchTitle(String title) {
      searchResultTitle.shouldBe(visible).shouldHave(text(title));
   }

   public void checkFoundItem() {
      foundItems.first()
              .shouldBe(visible)
              .shouldNotBe(empty);
      }
}