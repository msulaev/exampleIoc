import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Label;
import org.hamcrest.Matcher;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseSteps {

    @Attachment(value = "{name}", type = "image/png")
    private byte[] attachImage(String name, BufferedImage image) {
        try (ByteArrayOutputStream streem = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", streem);
            return streem.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void screenshotsShouldBeTheSame(BufferedImage actual, String expectedImagePath, Matcher matcher) throws IOException {
        BufferedImage expectedImage =  getExpectedImage(expectedImagePath);
        Allure.addLabels(new Label().withName("testType").withValue("screenshotDiff"));
        ImageDiff diff = new ImageDiffer().makeDiff(actual, expectedImage);
        attachImage("diff", diff.getMarkedImage());
        attachImage("actual", actual);
        attachImage("expected", expectedImage);
        assertThat(diff.getDiffSize(), matcher);
    }

     Screenshot takeScreenShotElement(SelenideElement element) {
        return new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)) //viewportRetina(100, 0, 0, 2))
                .takeScreenshot(WebDriverRunner.getWebDriver(), element);
    }

    Screenshot takeScreenShotFullPage() {
        return new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)) //viewportRetina(100, 0, 0, 2))
                .takeScreenshot(WebDriverRunner.getWebDriver());
    }

    private BufferedImage getExpectedImage(String screen) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return ImageIO.read(new File(Objects.requireNonNull(classLoader.getResource(screen)).getFile()));

    }
}
