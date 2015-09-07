package pages.items;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.ItemsController;
import controllers.items.types.ItemTypesController;
import models.items.Item;
import models.items.types.ItemType;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.items.viewItem;

import javax.inject.Inject;

class ViewItemPageImpl extends Controller implements ViewItemPage {

    private SessionAuthController sessionAuthController;
    private ItemTypesController itemTypesController;
    private ItemsController itemsController;

    @Inject
    ViewItemPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController, ItemsController itemsController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
        this.itemsController = itemsController;
    }

    @Override
    public Result get(int itemTypeId, int itemId) {

        try {

            String username = sessionAuthController.getUsername(session());

            ItemType itemType = itemTypesController.getItemType(itemTypeId);
            Item item = itemsController.getItem(itemId, username);

            String itemTypeName = itemType.getName();
            final String pageTitle = String.format("%s Library", itemTypeName);

            return ok((Content) viewItem.render(pageTitle, item, itemType));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

}
