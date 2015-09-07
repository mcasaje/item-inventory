package pages;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public Result index() {
        return redirect(pages.items.libraries.routes.LibrariesPage.get());
    }

}
