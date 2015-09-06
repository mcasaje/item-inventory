package pages.appusers;

import controllers.appusers.PasswordRequiredException;
import controllers.appusers.SignUpController;
import controllers.appusers.UsernameRequiredException;
import controllers.appusers.UsernameTakenException;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.appusers.signup;

import javax.inject.Inject;

class SignUpPageImpl extends Controller implements SignUpPage {

    private final String PAGE_TITLE = "Sign Up";
    private final String USERNAME_ID = "username";
    private final String PASSWORD_ID = "password";

    private SignUpController signUpController;

    @Inject
    SignUpPageImpl(SignUpController signUpController) {
        this.signUpController = signUpController;
    }

    @Override
    public Result get() {
        return ok((Content) signup.render(PAGE_TITLE, null, USERNAME_ID, PASSWORD_ID));
    }

    @Override
    public Result post() {

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get(USERNAME_ID);
        String password = form.get(PASSWORD_ID);

        try {
            signUpController.handleSignUp(username, password);
            return redirect(routes.LoginPage.get());

        } catch (UsernameRequiredException e) {
            return ok((Content) signup.render(PAGE_TITLE, "Username is required!", USERNAME_ID, PASSWORD_ID));
        } catch (PasswordRequiredException e) {
            return ok((Content) signup.render(PAGE_TITLE, "Password is required!", USERNAME_ID, PASSWORD_ID));
        } catch (UsernameTakenException e) {
            return ok((Content) signup.render(PAGE_TITLE, "Username already exists!", USERNAME_ID, PASSWORD_ID));
        }

    }
}
