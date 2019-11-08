import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import steps.LoginStep;

import static com.codeborne.selenide.Selenide.open;

 class FabricTest extends BaseTest{
    private LoginStep step = new LoginStep(Utils.browserFactory(Configuration.browser));

    @Test
     void login(){
        open("https://pdffiller.com");
        step.loginWithUser("132", "123");
    }
}
