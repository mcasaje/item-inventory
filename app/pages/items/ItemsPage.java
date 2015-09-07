package pages.items;

import play.mvc.Result;

public interface ItemsPage {

    Result get(int itemTypeId);

    Result post(int itemTypeId);

}
