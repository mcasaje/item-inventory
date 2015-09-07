/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/7/2015
 * Time: 12:24 AM
 */
package controllers.items.fields;

import com.google.inject.AbstractModule;

public class FieldsControllerModule extends AbstractModule {
    protected void configure() {
        bind(FieldsController.class).to(FieldsControllerImpl.class);
    }
}
