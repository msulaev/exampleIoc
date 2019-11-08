import pdffiller.pages.loginpage.LoginPageMozila;
import pdffiller.pages.loginpage.LoginPage;
import pdffiller.pages.loginpage.LoginPageChrome;

import static java.lang.System.*;

public class Utils {
    public static LoginPage browserFactory(String browser) {
        if (browser.equals("chrome")) {
            out.println("CHROME");
            return new LoginPageChrome();
        } else
            out.println("MOZILA");
        return new LoginPageMozila();
    }
}
