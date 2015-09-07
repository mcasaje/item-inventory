package pages.items.types;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.types.ItemTypesController;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pages.items.types.editItemType;

import javax.inject.Inject;

class ItemTypeEditPageImpl extends Controller implements ItemTypeEditPage {

    private final String PAGE_TITLE = "Create a New Item Type";
    private final String FIELD_NAME_ID = "field_name";
    private final SessionAuthController sessionAuthController;
    private final ItemTypesController itemTypesController;

    @Inject
    ItemTypeEditPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
    }

    @Override
    public Result get(int itemTypeId) {

        try {
            sessionAuthController.checkAuth(session());

            boolean itemTypeExists = itemTypesController.checkItemTypeExists(itemTypeId);

            if (itemTypeExists) {
                return ok(editItemType.render(PAGE_TITLE, null, FIELD_NAME_ID));
            } else {
                return notFound();
            }

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

    @Override
    public Result post(int itemTypeId) {

        try {

            String username = sessionAuthController.getUsername(session());

            boolean itemTypeExists = itemTypesController.checkItemTypeExists(itemTypeId);

            if (!itemTypeExists) {
                return notFound();
            }

            DynamicForm form = Form.form().bindFromRequest();
            String fieldName = form.get(FIELD_NAME_ID);

            itemTypesController.addFieldToItemType(itemTypeId, username, fieldName);

            return ok(editItemType.render(PAGE_TITLE, null, FIELD_NAME_ID));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }
    }
}
