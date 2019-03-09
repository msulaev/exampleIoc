import ScreenshotMaker.ScreenshotMaker;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeEach
    void setUp(){
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.baseUrl = "http://www.findtheconversation.com/";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    public static void main(String[] args) throws IOException {
        open("http://www.findtheconversation.com/concept-map/#wes-jackson");

       Optional<SelenideElement> a =  $$("g.node").stream().filter(element -> element.getAttribute("r").equals("48")).findFirst();

       a.get().click();
    }
}
