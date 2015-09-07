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
        bind(LibrariesPage.class).to(LibrariesPageImpl.class).asEagerSingleton();
        bind(NewLibraryPage.class).to(NewLibraryPageImpl.class).asEagerSingleton();
        bind(EditLibraryPage.class).to(EditLibraryPageImpl.class).asEagerSingleton();
    }
}
