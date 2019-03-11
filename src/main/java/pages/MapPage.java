package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MapPage {
    private SelenideElement map = $("#graph > svg > g > rect");
    private ElementsCollection listEpisodes = $$(".episode");
    private SelenideElement envTheme = $("#graph > svg > g > g.nodes > g:nth-child(16)");

    public static MapPage open() {
        return Selenide.open("/concept-map/#", MapPage.class);
    }

    public MapPage clickToGuestMap(String guestName) {
        $(byText(guestName)).click();
        return this;
    }

    public void clickToEpisodePage() {
        $x("//*[contains(text(),'EPISODE ')]").click();
    }

    public SelenideElement getMap() {
        return map;
    }

    @Step("Click to enviroment, on the Was Map")
    public void showEnvThemeOnWesMap() {
        envTheme.click();
    }

    public void listEpisodeShouldBeTheSame(ArrayList<String> expectedList) {
        for (int i = 0; i < listEpisodes.size(); i++)
            assertThat(listEpisodes.get(i).getText(), equalTo(expectedList.get(i)));
    }
}
