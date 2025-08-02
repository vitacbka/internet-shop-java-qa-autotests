package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.net.HttpURLConnection;
import java.net.URL;

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

            cartHeader = $("#menu-item-29 a");

    public ElementsCollection productImages = $$(".slick-track .item-img img");

    public ElementsCollection
            saleLabels = $$x("//aside[@id='accesspress_store_product-2']//li[not(contains(@class, 'slick-cloned'))]//span[@class='onsale']"),
            newItemLabels = $$("aside#accesspress_store_product-3 ul.new-prod-slide li .label-new");


    public void openMainPage() {
        open(BASE_URL);
    }

    public boolean isImageOk(String imageUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            return code == 200;
        } catch (Exception e) {
            return false;
        }
    }
}