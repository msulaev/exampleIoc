package pdffiller.pages.loginpage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPageChrome implements LoginPage {
    private SelenideElement logIn = $(byText("Log in"));
    private SelenideElement emailInpt = $x("//input[@type='email' and @name='username' and @data-for='t2']");
    private SelenideElement passwordInpt = $x("//input[@type='password' and @name='password' and @data-for='t6']");
    private SelenideElement logInBtn = $x("//button[@type='submit' and contains(text(),'Log In')]");

    @Override
    public void login(String email, String pwd) {
        logIn.click();
        emailInpt.sendKeys(email);
        passwordInpt.sendKeys(pwd);
        logInBtn.click();
    }
}
