package pages.appusers;

import controllers.appusers.LoginController;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

class LogoutPageImpl extends Controller implements LogoutPage {

    private final String USERNAME_ID = "username";
    private final String PASSWORD_ID = "password";
    private LoginController loginController;

    @Inject
    LogoutPageImpl(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public Result post() {

        session().clear();

        return redirect(routes.LoginPage.get());

    }
}
