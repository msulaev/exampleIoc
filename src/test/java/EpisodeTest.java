import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.EpisodePage;
import pages.MapPage;
import ru.yandex.qatools.ashot.Screenshot;
import steps.BaseSteps;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class EpisodeTest extends BaseTest {
    private static final String GUEST = "Ethan Zuckerman",
            ALERT_MESSAGE = "You must be logged in to post a comment.";
    private EpisodePage episodePage = new EpisodePage();
    private BaseSteps imageDif = new BaseSteps();

    @BeforeEach
    void setUp() {
        MapPage.open()
                .clickToGuestMap(GUEST)
                .clickToEpisodePage();
    }

    @Test
    void shouldSeeEpisodePage() throws IOException {
        Screenshot mapScreen = imageDif.takeScreenShotFullPage();
        imageDif.screenshotsShouldBeTheSame(mapScreen.getImage(), "expectedEpisodePage.png", lessThan(5));
    }

    @Test
    void shouldPlayAndStopEpisode() {
        assertThat(episodePage.canPlayEpisode(), is(true));
        assertThat(episodePage.canStopEpisode(), is(true));
    }

    @Test
    void shouldSeeAlertMsgForAnonymous() {
        episodePage.clickToShowCommentButton();
        assertThat(episodePage.getAlertMessage(), equalTo(ALERT_MESSAGE));
    }



}
