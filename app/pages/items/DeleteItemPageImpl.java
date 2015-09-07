package pages.items;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.ItemsController;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

class DeleteItemPageImpl extends Controller implements DeleteItemPage {

    private SessionAuthController sessionAuthController;
    private ItemsController itemsController;

    @Inject
    DeleteItemPageImpl(SessionAuthController sessionAuthController, ItemsController itemsController) {
        this.sessionAuthController = sessionAuthController;
        this.itemsController = itemsController;
    }

    @Override
    public Result post(int itemTypeId, int itemId) {

        try {
            String username = sessionAuthController.getUsername(session());

            itemsController.deleteItem(itemId, username);

            return redirect(routes.ViewLibraryPage.get(itemTypeId));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }
}
