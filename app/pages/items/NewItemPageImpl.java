package pages.items;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.ItemsController;
import controllers.items.fields.FieldsController;
import controllers.items.tags.TagsController;
import controllers.items.types.ItemTypesController;
import models.items.Item;
import models.items.fields.Field;
import models.items.tags.Tag;
import models.items.types.ItemType;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.items.newItem;

import javax.inject.Inject;

class NewItemPageImpl extends Controller implements NewItemPage {

    private final String PAGE_TITLE = "Create";
    private final String ITEM_NAME_ID = "new_item_name";
    private final String TAG_ID = "tags";

    private SessionAuthController sessionAuthController;
    private ItemTypesController itemTypesController;
    private ItemsController itemsController;
    private TagsController tagsController;
    private FieldsController fieldsController;

    @Inject
    NewItemPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController, ItemsController itemsController, TagsController tagsController, FieldsController fieldsController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
        this.itemsController = itemsController;
        this.tagsController = tagsController;
        this.fieldsController = fieldsController;
    }

    @Override
    public Result get(int itemTypeId) {

        try {
            sessionAuthController.checkAuth(session());

            ItemType itemType = itemTypesController.getItemType(itemTypeId);

            return ok((Content) newItem.render(PAGE_TITLE, null, itemType, ITEM_NAME_ID, TAG_ID));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

    @Override
    public Result post(int itemTypeId) {

        try {
            String username = sessionAuthController.getUsername(session());

            ItemType itemType = itemTypesController.getItemType(itemTypeId);

            DynamicForm form = Form.form().bindFromRequest();
            String itemName = form.get(ITEM_NAME_ID);

            if (itemName == null || itemName.length() <= 0) {
                String itemTypeName = itemType.getName();
                final String message = String.format("%s Name is required!", itemTypeName);
                return ok((Content) newItem.render(PAGE_TITLE, message, itemType, ITEM_NAME_ID, TAG_ID));
            }

            Item item = itemsController.createItem(itemName, itemTypeId, username);
            int itemId = item.getId();

            for (Tag tag : itemType.getTags()) {

                int tagId = tag.getId();
                String tagValue = form.get("tag" + tag.getId());

                if (tagValue != null) {
                    tagsController.addTagToItem(itemId, tagId, username);
                }
            }

            for (Field field : itemType.getFields()) {

                int fieldId = field.getId();
                String fieldValue = form.get("field" + fieldId);

                if (fieldValue != null && !fieldValue.equals("")) {
                    fieldsController.addFieldToItem(itemId, fieldId, username, fieldValue);
                }
            }

            return redirect(routes.ViewLibraryPage.get(itemTypeId));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

}
