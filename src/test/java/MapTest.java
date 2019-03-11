import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
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
    void shouldSeeGuestMap(String guestName, String pathToExpectImage) throws IOException, InterruptedException {
        map.clickToGuestMap(guestName);
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), pathToExpectImage, lessThanOrEqualTo(110));
    }

    @Test
    void shouldSeeThemeMap() throws IOException {
        open("/concept-map/#environment");
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "expectedEnviromentMap.png", lessThan(5));
    }

    @Test
    void shoudSeeTreeMap() throws IOException {
        map.clickToGuestMap("Wes Jackson");
        map.showEnvThemeOnWesMap();
        Screenshot mapScreen = imageDif.takeScreenShotElement(map.getMap());
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "epectedShowEnvTheme.png", lessThan(110));

    }

    @AfterEach
    void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

