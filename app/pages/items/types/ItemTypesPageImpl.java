package pages.items.types;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.types.ItemTypesController;
import models.items.types.ItemType;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;

import javax.inject.Inject;
import java.util.List;

class ItemTypesPageImpl extends Controller implements ItemTypesPage {

    private final String ITEM_TYPE_NAME_ID = "item_type_name";

    private final SessionAuthController sessionAuthController;
    private final ItemTypesController itemTypesController;

    @Inject
    ItemTypesPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
    }

    @Override
    public Result get() {

        try {
            String username = sessionAuthController.getUsername(session());

            List<ItemType> itemTypes = itemTypesController.getItemTypes(username);

            final String pageTitle = "Item Types";

            return ok((Content) views.html.pages.items.types.index.render(pageTitle, null, itemTypes, ITEM_TYPE_NAME_ID));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }
}
