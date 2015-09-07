package pages.items;

import play.mvc.Result;

public interface ViewItemPage {

    Result get(int itemTypeId, int itemId);

}
