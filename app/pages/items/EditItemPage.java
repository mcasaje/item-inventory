package pages.items;

import play.mvc.Result;

public interface EditItemPage {

    Result get(String username, int itemId);

    Result post(String username, int itemId);

}
