package pages.items.types;

import play.mvc.Result;

public interface ItemTypeEditPage {

    Result get(int itemTypeId);

    Result post(int itemTypeId);

}
