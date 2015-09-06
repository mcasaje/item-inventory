/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/6/2015
 * Time: 11:20 AM
 */
package controllers.items;

import com.google.inject.AbstractModule;

public class ItemsControllerModule extends AbstractModule {
    protected void configure() {
        bind(ItemsController.class).to(ItemsControllerImpl.class);
    }
}
