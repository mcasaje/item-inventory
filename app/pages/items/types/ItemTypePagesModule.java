/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/6/2015
 * Time: 4:13 PM
 */
package pages.items.types;

import com.google.inject.AbstractModule;

public class ItemTypePagesModule extends AbstractModule {
    protected void configure() {
        bind(ItemTypesPage.class).to(ItemTypesPageImpl.class).asEagerSingleton();
        bind(NewItemTypePage.class).to(NewItemTypePageImpl.class).asEagerSingleton();
        bind(EditItemTypePage.class).to(EditItemTypePageImpl.class).asEagerSingleton();
    }
}
