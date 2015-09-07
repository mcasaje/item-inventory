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
        bind(ItemsPage.class).to(ItemsPageImpl.class).asEagerSingleton();
        bind(EditItemPage.class).to(EditItemPageImpl.class).asEagerSingleton();
    }
}
