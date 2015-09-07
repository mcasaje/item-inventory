package pages.items.types;

import play.mvc.Result;

public interface EditLibraryPage {

    Result get(int itemTypeId);

    Result postField(int itemTypeId);

    Result postTag(int itemTypeId);

}
