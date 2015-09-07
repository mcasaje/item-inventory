package pages.items.types;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.types.ItemTypesController;
import models.items.types.ItemType;
import pages.appusers.routes;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pages.items.types.editItemType;
import views.html.pages.items.types.newItemType;

import javax.inject.Inject;

class NewItemTypePageImpl extends Controller implements NewItemTypePage {

    private final String PAGE_TITLE = "Create a New Item Type";
    private final String ITEM_TYPE_NAME_ID = "item_type_name";
    private final SessionAuthController sessionAuthController;
    private final ItemTypesController itemTypesController;

    @Inject
    NewItemTypePageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
    }

    @Override
    public Result get() {

        try {
            sessionAuthController.checkAuth(session());
            return ok(newItemType.render(PAGE_TITLE, null, ITEM_TYPE_NAME_ID));

        } catch (UnauthorizedException e) {
            return redirect(routes.LoginPage.get());
        }

    }

    @Override
    public Result post() {

        try {

            String username = sessionAuthController.getUsername(session());

            DynamicForm form = Form.form().bindFromRequest();
            String name = form.get(ITEM_TYPE_NAME_ID);

            ItemType itemType = itemTypesController.createItemType(name, username);
            Integer id = itemType.getId();

            return redirect(pages.items.types.routes.ItemTypeEditPage.get(id));

        } catch (UnauthorizedException e) {
            return redirect(routes.LoginPage.get());
        }

    }
}
