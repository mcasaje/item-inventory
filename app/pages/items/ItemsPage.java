package pages.items;

import play.mvc.Result;

public interface ItemsPage {

    Result get(String username);

    Result post(String username);

}
