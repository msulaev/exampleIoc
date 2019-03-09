package ScreenshotMaker;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {
    public void makeFullScreen() throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)) //viewportRetina(100, 0, 0, 2))
                .takeScreenshot(WebDriverRunner.getWebDriver());
        ImageIO.write(screenshot.getImage(), "PNG",
                new File(System.getProperty("user.dir") + "\\screenshots\\fullpagescrn.png"));
        screenshot.getImage();
    }

    public void makeElementScreen(SelenideElement element) throws IOException {
        Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)) //viewportRetina(100, 0, 0, 2))
                .takeScreenshot(WebDriverRunner.getWebDriver(), element);
        ImageIO.write(screenshot.getImage(), "PNG",
                new File(System.getProperty("user.dir") + "\\screenshots\\element.png"));
    }
}
