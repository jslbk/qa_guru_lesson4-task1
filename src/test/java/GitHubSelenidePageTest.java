import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class GitHubSelenidePageTest {

    @Test
    void softAssertionsSectionTest() {
        open("https://github.com/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $("[data-testid=results-list]").shouldBe(visible);
        openFirstResultOnList();
        $("#wiki-tab").parent().click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $$(".wiki-rightbar:not([style='display: none'])").shouldHave(size(1));
    }

    private void openFirstResultOnList() {
        $$("[data-testid=results-list] a").first().click();
        $("[itemprop=name]").shouldBe(visible).shouldHave(text("selenide"));
    }

}
