/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/6/2015
 * Time: 11:27 AM
 */
package pages.items;

import com.google.inject.AbstractModule;

public class ItemsPageModule extends AbstractModule {
    protected void configure() {
        bind(ViewLibraryPage.class).to(ViewLibraryPageImpl.class).asEagerSingleton();
        bind(ViewItemPage.class).to(ViewItemPageImpl.class).asEagerSingleton();
        bind(NewItemPage.class).to(NewItemPageImpl.class).asEagerSingleton();
        bind(EditItemPage.class).to(EditItemPageImpl.class).asEagerSingleton();
        bind(DeleteItemPage.class).to(DeleteItemPageImpl.class).asEagerSingleton();
    }
}
