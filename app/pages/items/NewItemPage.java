package pages.items;

import play.mvc.Result;

public interface NewItemPage {

    Result get(int itemTypeId);

    Result post(int itemTypeId);

}
