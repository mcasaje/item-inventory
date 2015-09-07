package pages.appusers;

import controllers.appusers.*;
import controllers.appusers.sessions.SessionAuthController;
import models.appusers.AppUser;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pages.appusers.login;

import javax.inject.Inject;

class LoginPageImpl extends Controller implements LoginPage {

    private final String USERNAME_ID = "username";
    private final String PASSWORD_ID = "password";
    private LoginController loginController;

    @Inject
    LoginPageImpl(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public Result get() {
        final String pageTitle = "Login";
        return ok(login.render(pageTitle, null, USERNAME_ID, "", PASSWORD_ID));
    }

    @Override
    public Result post() {

        final String pageTitle = "Login";

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get(USERNAME_ID);
        String password = form.get(PASSWORD_ID);

        try {

            AppUser appUser = loginController.handleAuthentication(username, password);

            session(SessionAuthController.USER_ID_SESS_KEY, appUser.getId().toString());
            session(SessionAuthController.USERNAME_SESS_KEY, appUser.getUsername());

            return redirect(routes.DashboardPage.get());

        } catch (UsernameRequiredException e) {
            return ok(login.render(pageTitle, "Username is required!", USERNAME_ID, username, PASSWORD_ID));
        } catch (PasswordRequiredException e) {
            return ok(login.render(pageTitle, "Password is required!", USERNAME_ID, username, PASSWORD_ID));
        } catch (UserDoesNotExistException e) {
            return ok(login.render(pageTitle, "User " + username + " does not exist!", USERNAME_ID, username, PASSWORD_ID));
        } catch (InvalidPasswordException e) {
            return ok(login.render(pageTitle, "Invalid password!", USERNAME_ID, username, PASSWORD_ID));
        }

    }
}
