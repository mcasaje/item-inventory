package pages.appusers;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pages.appusers.dashboard;

import javax.inject.Inject;

public class DashboardPageImpl extends Controller implements DashboardPage {

    private SessionAuthController sessionAuthController;

    @Inject
    DashboardPageImpl(SessionAuthController sessionAuthController) {
        this.sessionAuthController = sessionAuthController;
    }

    @Override
    public Result get() {

        try {

            String username = sessionAuthController.getUsername(session());
            final String pageTitle = String.format("%s's Dashboard", username);
            return ok(dashboard.render(pageTitle));

        } catch (UnauthorizedException e) {
            return redirect(routes.LoginPage.get());
        }

    }
}
