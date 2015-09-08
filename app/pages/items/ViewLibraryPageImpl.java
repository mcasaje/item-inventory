package pages.items;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.ItemsController;
import controllers.items.types.ItemTypesController;
import models.items.Item;
import models.items.types.ItemType;
import models.items.utils.sorting.items.ItemFieldSortStrategy;
import models.items.utils.sorting.items.ItemSortStrategy;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.items.viewLibrary;

import javax.inject.Inject;
import java.util.List;

class ViewLibraryPageImpl extends Controller implements ViewLibraryPage {

    private final String ITEM_DETAILS_QUERY_KEY = "details";
    private final String TAG_FILTER_QUERY_KEY = "tag";
    private final String FIELD_SORT_FORM_KEY = "field";
    private final String FIELD_SORT_DIR_FORM_KEY = "field_desc";
    private final String FIELD_NAME_KEY = "field_name";
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

            DynamicForm form = Form.form().bindFromRequest();

            ItemType itemType = itemTypesController.getItemType(itemTypeId);

            // Check what to sort on
            String fieldVal = form.get(FIELD_SORT_FORM_KEY);
            Integer fieldId = fieldVal != null ? new Integer(fieldVal) : -1;

            // Check what direction to sort
            String fieldDirVal = form.get(FIELD_SORT_DIR_FORM_KEY);
            boolean fieldSortDesc = new Boolean(fieldDirVal);

            // Check if we're filtering on a specific tag
            String tagVal = form.get(TAG_FILTER_QUERY_KEY);
            Integer tagId = tagVal != null ? new Integer(tagVal) : null;

            ItemSortStrategy itemSortStrategy;

            // Special Case: Default to sorting the item name
            if (fieldId == -1 && fieldSortDesc) {
                itemSortStrategy = ItemSortStrategy.NAME_DESC;
            } else {
                itemSortStrategy = ItemSortStrategy.NAME_ASC;
            }

            List<Item> items;

            // Get items, optionally filtering on tag.
            if (tagVal != null) {
                items = itemsController.getItems(itemTypeId, username, itemSortStrategy, tagId);
            } else {
                items = itemsController.getItems(itemTypeId, username, itemSortStrategy);
            }

            // Optionally sorting items further by specified field id.
            if (fieldId != -1 && fieldSortDesc) {
                itemsController.sortItemByField(items, fieldId, ItemFieldSortStrategy.ITEM_FIELD_VALUE_ASC);
            } else if (fieldId != -1) {
                itemsController.sortItemByField(items, fieldId, ItemFieldSortStrategy.ITEM_FIELD_VALUE_DESC);
            }

            String itemTypeName = itemType.getName();
            final String pageTitle = String.format("'%s' Library", itemTypeName);

            return ok((Content) viewLibrary.render(pageTitle, true, tagId, fieldId, fieldSortDesc, itemType, items, ITEM_DETAILS_QUERY_KEY, TAG_FILTER_QUERY_KEY, FIELD_SORT_FORM_KEY, FIELD_SORT_DIR_FORM_KEY, FIELD_NAME_KEY));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

}
