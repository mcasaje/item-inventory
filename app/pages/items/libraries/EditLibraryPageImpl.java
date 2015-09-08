package pages.items.libraries;

import controllers.appusers.sessions.SessionAuthController;
import controllers.appusers.sessions.UnauthorizedException;
import controllers.items.types.ItemTypesController;
import models.items.types.ItemType;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import views.html.pages.items.libraries.editLibrary;

import javax.inject.Inject;

class EditLibraryPageImpl extends Controller implements EditLibraryPage {

    private final String FIELD_NAME_ID = "field_name";
    private final String TAG_NAME_ID = "tag_name";
    private final SessionAuthController sessionAuthController;
    private final ItemTypesController itemTypesController;

    @Inject
    EditLibraryPageImpl(SessionAuthController sessionAuthController, ItemTypesController itemTypesController) {
        this.sessionAuthController = sessionAuthController;
        this.itemTypesController = itemTypesController;
    }

    @Override
    public Result get(int itemTypeId) {

        final String NEW_FIELD_ROUTE = routes.EditLibraryPage.postField(itemTypeId).url();
        final String NEW_TAG_ROUTE = routes.EditLibraryPage.postTag(itemTypeId).url();

        try {

            sessionAuthController.checkAuth(session());

            boolean itemTypeExists = itemTypesController.checkItemTypeExists(itemTypeId);

            if (itemTypeExists) {

                ItemType itemType = itemTypesController.getItemType(itemTypeId);
                String pageTitle = "Customize ";
                return ok((Content) editLibrary.render(pageTitle, null, itemType, NEW_FIELD_ROUTE, FIELD_NAME_ID, NEW_TAG_ROUTE, TAG_NAME_ID));

            } else {
                return notFound();
            }

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }

    }

    @Override
    public Result postField(int itemTypeId) {

        try {

            String username = sessionAuthController.getUsername(session());

            boolean itemTypeExists = itemTypesController.checkItemTypeExists(itemTypeId);

            if (!itemTypeExists) {
                return notFound();
            }

            DynamicForm form = Form.form().bindFromRequest();
            String fieldName = form.get(FIELD_NAME_ID);

            itemTypesController.addFieldToItemType(itemTypeId, username, fieldName);

            return redirect(routes.EditLibraryPage.get(itemTypeId));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }
    }

    @Override
    public Result postTag(int itemTypeId) {

        try {

            String username = sessionAuthController.getUsername(session());

            boolean itemTypeExists = itemTypesController.checkItemTypeExists(itemTypeId);

            if (!itemTypeExists) {
                return notFound();
            }

            DynamicForm form = Form.form().bindFromRequest();
            String tagName = form.get(TAG_NAME_ID);

            itemTypesController.addTagToItemType(itemTypeId, username, tagName);

            return redirect(routes.EditLibraryPage.get(itemTypeId));

        } catch (UnauthorizedException e) {
            return redirect(pages.appusers.routes.LoginPage.get());
        }
    }
}
