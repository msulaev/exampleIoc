import com.codeborne.selenide.WebDriverRunner;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MapPage;
import ru.yandex.qatools.ashot.Screenshot;
import steps.BaseSteps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

class MapTest extends BaseTest {
    private MapPage map = new MapPage();
    private BaseSteps imageDif = new BaseSteps();

    private static Stream<Arguments> userAndExpectedImage() {
        return Stream.of(
                Arguments.of("Wes Jackson", "expectedWesJackson.png"),
                Arguments.of("Robert Zubrin", "expectedRobertZubrin.png"));
    }

    @BeforeEach
    void setUp() {
        MapPage.open();
    }

    @Test
    void shouldSeeMap() throws IOException {
        ArrayList<String> listUser = new FileReader().getExpectedListEpisode();
        map.listEpisodeShouldBeTheSame(listUser);
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "expectedMap.png", lessThan(5));
    }

    @ParameterizedTest
    @MethodSource("userAndExpectedImage")
    void shouldSeeGuestMap(String guestName, String pathToExpectImage) throws IOException {
        map.clickToGuestMap(guestName);
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), pathToExpectImage, lessThanOrEqualTo(90));
    }

    @Test
    void shouldSeeThemeMap() throws IOException {
        open("http://www.findtheconversation.com/concept-map/#environment");
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "expectedEnviromentMap.png", lessThan(5));
    }

    @Ignore
    void shoudSeeTreeMap() throws IOException {
        map.clickToGuestMap("Wes Jackson");
       $("#graph > svg > g > g.nodes > g:nth-child(7)").click();
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "epectedShowEnvTheme.png", lessThan(5));

    }

    @AfterEach
    void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

