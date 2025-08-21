package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FooterPage {

    public final SelenideElement
            footerContactInfo = $(".banner-text.wow.fadeInLeft"),
            footerWidgetLinksTitle = $("#pages-2");

    public ElementsCollection footerPageLinks = $$("#pages-2 ul li a");

    public void contactInFooterShouldBeVisible(String expectedText) {
        footerContactInfo.scrollTo()
                .shouldBe(visible)
                .shouldHave(text(expectedText));
    }

    public void widgetLinksTitleInFooterShouldBeVisible(String expectedTitle) {
        footerWidgetLinksTitle
                .scrollTo()
                .shouldBe(visible)
                .shouldHave(text(expectedTitle));
    }

    public void allFooterLinksShouldBeVisible(String[] expectedLinkTexts) {
        footerWidgetLinksTitle.scrollTo();
        footerPageLinks.shouldHave(CollectionCondition.size(expectedLinkTexts.length));
        footerPageLinks.forEach(el -> el.shouldBe(visible));
        footerPageLinks.shouldHave(CollectionCondition.texts(expectedLinkTexts));
    }
}