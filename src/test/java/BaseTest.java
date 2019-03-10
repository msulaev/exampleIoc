import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

class BaseTest {

    @BeforeAll
    void setUp() {
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.browserSize = "1366x768";
        Configuration.baseUrl = "http://www.findtheconversation.com/";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
