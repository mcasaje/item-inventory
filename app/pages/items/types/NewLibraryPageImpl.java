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

import javax.inject.Inject;

class NewLibraryPageImpl extends Controller implements NewLibraryPage {

    private final String ITEM_TYPE_NAME_ID = "item_type_name";
    private final SessionAuthController sessionAuthController;
    private final ItemTypesController itemTypesController;

    @Inject
    NewLibraryPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
    }

    @Override
    public Result post() {

        try {

            String username = sessionAuthController.getUsername(session());

            DynamicForm form = Form.form().bindFromRequest();
            String name = form.get(ITEM_TYPE_NAME_ID);

            ItemType itemType = itemTypesController.createItemType(name, username);
            Integer id = itemType.getId();

            return redirect(pages.items.types.routes.EditLibraryPage.get(id));

        } catch (UnauthorizedException e) {
            return redirect(routes.LoginPage.get());
        }

    }
}
