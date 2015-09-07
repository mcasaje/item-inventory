/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/6/2015
 * Time: 11:20 AM
 */
package controllers.items.types;

import com.google.inject.AbstractModule;

public class ItemTypesControllerModule extends AbstractModule {
    protected void configure() {
        bind(ItemTypesController.class).to(ItemTypesControllerImpl.class);
    }
}
