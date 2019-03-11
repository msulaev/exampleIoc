import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

class BaseTest {

    @BeforeAll
    static void setUpConf() {
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.baseUrl = "http://www.findtheconversation.com/";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
