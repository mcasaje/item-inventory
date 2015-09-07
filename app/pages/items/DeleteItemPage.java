package pages.items;

import play.mvc.Result;

public interface DeleteItemPage {

    Result post(int itemTypeId, int itemId);

}
