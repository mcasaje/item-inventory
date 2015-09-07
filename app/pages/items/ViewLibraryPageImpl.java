package pages.items;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.ItemsController;
import controllers.items.types.ItemTypesController;
import models.items.Item;
import models.items.types.ItemType;
import models.items.utils.sorting.items.ItemSortStrategy;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.items.viewLibrary;

import javax.inject.Inject;
import java.util.List;

class ViewLibraryPageImpl extends Controller implements ViewLibraryPage {

    private final String ITEM_DETAILS_QUERY_KEY = "details";
    private SessionAuthController sessionAuthController;
    private ItemTypesController itemTypesController;
    private ItemsController itemsController;

    @Inject
    ViewLibraryPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController, ItemsController itemsController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
        this.itemsController = itemsController;
    }

    @Override
    public Result get(int itemTypeId) {

        try {
            String username = sessionAuthController.getUsername(session());

            String[] detailsVal = request().queryString().get(ITEM_DETAILS_QUERY_KEY);
            boolean showDetails = detailsVal != null && detailsVal.length > 0 && detailsVal[0].equals("true");

            ItemType itemType = itemTypesController.getItemType(itemTypeId);
            List<Item> items = itemsController.getItems(itemTypeId, username, ItemSortStrategy.ID_DESC);

            String itemTypeName = itemType.getName();
            final String pageTitle = String.format("'%s' Library", itemTypeName);

            return ok((Content) viewLibrary.render(pageTitle, showDetails, itemType, items));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

}
