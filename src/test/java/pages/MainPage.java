package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static readproperties.ConfigProvider.BASE_URL;

public class MainPage {

    public final SelenideElement
            mainPageTitle = $(".site-text"),
            booksSlide = $("#accesspress_storemo-2 .widget-title"),
            tabletsSlide = $("#accesspress_storemo-3 .widget-title"),
            camerasSlide = $("#accesspress_storemo-4 .widget-title"),
            saleCategoryTitle = $("#accesspress_store_product-2 h2.prod-title"),
            newArrivalTitle = $x("(//h2[@class='prod-title'])[2]"),
            footerContactInfo = $(".banner-text.wow.fadeInLeft"),
            cartHeader = $("#menu-item-29 a");

    public ElementsCollection
            saleLabels = $$x("//aside[@id='accesspress_store_product-2']//li[not(contains(@class, 'slick-cloned'))]//span[@class='onsale']"),
            newItemLabels = $$("aside#accesspress_store_product-3 ul.new-prod-slide li .label-new");


    public void openMainPage() {
        open(BASE_URL);
    }
}