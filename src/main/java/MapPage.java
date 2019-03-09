import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MapPage {
    private SelenideElement map = $("#graph > svg > g > rect");
    private ElementsCollection listEpisodes = $$(".episode");

    static MapPage open() {
        return Selenide.open("concept-map/#", MapPage.class);
    }

    MapPage clickToGuestMap(String guestName) {
        $(byText(guestName)).click();
        return this;
    }

    void clickToEpisodePage() {
        $x("//*[contains(text(),'EPISODE ')]").click();
    }


    SelenideElement getMap() {
        return map;
    }


    void listEpisodeShouldBeTheSame(ArrayList<String> expectedList) {
        for (int i = 0; i < listEpisodes.size(); i++)
            assertThat(listEpisodes.get(i).getText(), equalTo(expectedList.get(i)));
    }
}
