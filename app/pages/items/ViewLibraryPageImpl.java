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
    private final String FIELD_SORT_DIR_FORM_KEY = "field_asc";
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

            // Determine Details or List view
            String detailsVal = form.get(ITEM_DETAILS_QUERY_KEY);
            boolean showDetails = new Boolean(detailsVal);

            // Check what to sort on
            String fieldVal = form.get(FIELD_SORT_FORM_KEY);
            Integer fieldId = fieldVal != null ? new Integer(fieldVal) : -1;
            String fieldDirVal = form.get(FIELD_SORT_DIR_FORM_KEY);
            boolean fieldSortAsc = new Boolean(fieldDirVal);

            // Check if we're filtering on a specific tag
            String tagVal = form.get(TAG_FILTER_QUERY_KEY);

            Integer tagId;

            ItemType itemType = itemTypesController.getItemType(itemTypeId);

            List<Item> items = null;

            try {
                tagId = tagVal != null ? new Integer(tagVal) : null;

                ItemSortStrategy itemSortStrategy = ItemSortStrategy.NAME_ASC;

                if (fieldId == -1 && fieldSortAsc) {
                    itemSortStrategy = ItemSortStrategy.NAME_ASC;
                } else if (fieldId == -1 && !fieldSortAsc) {
                    itemSortStrategy = ItemSortStrategy.NAME_DESC;
                }

                if (tagId != null) {
                    items = itemsController.getItems(itemTypeId, username, itemSortStrategy, tagId);
                } else {
                    items = itemsController.getItems(itemTypeId, username, itemSortStrategy);
                }

                if (fieldId != -1 && fieldSortAsc) {
                    itemsController.sortItemByField(items, fieldId, ItemFieldSortStrategy.ITEM_FIELD_VALUE_ASC);
                } else if (fieldId != -1) {
                    itemsController.sortItemByField(items, fieldId, ItemFieldSortStrategy.ITEM_FIELD_VALUE_DESC);
                }

            } catch (Exception e) {
                tagId = -1;
            }

            String itemTypeName = itemType.getName();
            final String pageTitle = String.format("'%s' Library", itemTypeName);

            return ok((Content) viewLibrary.render(pageTitle, showDetails, tagId, fieldId, fieldSortAsc, itemType, items, ITEM_DETAILS_QUERY_KEY, TAG_FILTER_QUERY_KEY, FIELD_SORT_FORM_KEY, FIELD_SORT_DIR_FORM_KEY, FIELD_NAME_KEY));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

}
