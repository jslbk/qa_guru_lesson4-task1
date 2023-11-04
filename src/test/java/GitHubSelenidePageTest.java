import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class GitHubSelenidePageTest {

    @Test
    void softAssertionsSectionTest() {
        open("https://github.com/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $("[data-testid=results-list]").shouldBe(visible);
        openFirstResultOnList();

        // Click 'Wiki' tab and check rightbar contains 'SoftAssertions' option
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-filter").shouldHave(value("SoftAssertions"));
        $$(".wiki-rightbar").filterBy(Condition.not(hidden)).shouldHave(size(1));

        // Click on the "SoftAssertions"
        $("a[href*='SoftAssertions']").click();

        // Find code
        $("h4 [href*='junit5']").shouldBe(visible, Duration.ofSeconds(5));
        $("h4 [href*='junit5']").parent().sibling(0).$("pre").shouldBe(visible);
    }

    private void openFirstResultOnList() {
        $$("[data-testid=results-list] a").first().click();
        $("[itemprop=name]").shouldBe(visible).shouldHave(text("selenide"));
    }

}
