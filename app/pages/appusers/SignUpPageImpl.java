package pages.appusers;

import controllers.appusers.SignUpController;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

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
        return ok(views.html.pages.appusers.signup.index.render(PAGE_TITLE, USERNAME_ID, PASSWORD_ID));
    }

    @Override
    public Result post() {

        // Retrieve form values

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get(USERNAME_ID);
        String password = form.get(PASSWORD_ID);

        signUpController.signUpHandling(username, password);

        return redirect("/users/" + username);
    }
}
