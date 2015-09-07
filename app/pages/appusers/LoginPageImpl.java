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

    private final String PAGE_TITLE = "Login";
    private final String USERNAME_ID = "username";
    private final String PASSWORD_ID = "password";
    private LoginController loginController;

    @Inject
    LoginPageImpl(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public Result get() {
        return ok(login.render(PAGE_TITLE, null, USERNAME_ID, "", PASSWORD_ID));
    }

    @Override
    public Result post() {

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get(USERNAME_ID);
        String password = form.get(PASSWORD_ID);

        try {

            AppUser appUser = loginController.handleAuthentication(username, password);

            session(SessionAuthController.USER_ID_SESS_KEY, appUser.getId().toString());
            session(SessionAuthController.USERNAME_SESS_KEY, appUser.getUsername());

            return redirect(pages.items.routes.ItemsPage.get());

        } catch (UsernameRequiredException e) {
            return ok(login.render(PAGE_TITLE, "Username is required!", USERNAME_ID, username, PASSWORD_ID));
        } catch (PasswordRequiredException e) {
            return ok(login.render(PAGE_TITLE, "Password is required!", USERNAME_ID, username, PASSWORD_ID));
        } catch (UserDoesNotExistException e) {
            return ok(login.render(PAGE_TITLE, "User " + username + " does not exist!", USERNAME_ID, username, PASSWORD_ID));
        } catch (InvalidPasswordException e) {
            return ok(login.render(PAGE_TITLE, "Invalid password!", USERNAME_ID, username, PASSWORD_ID));
        }

    }
}
