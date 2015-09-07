package pages.items.types;

import play.mvc.Result;

public interface EditItemTypePage {

    Result get(int itemTypeId);

    Result postField(int itemTypeId);

    Result postTag(int itemTypeId);

}
