package steps;

import pdffiller.pages.loginpage.LoginPage;

public class LoginStep {
    private LoginPage loginPage;

    public LoginStep(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void loginWithUser(String name, String pwd){
        loginPage.login(name, pwd);
    }
}
