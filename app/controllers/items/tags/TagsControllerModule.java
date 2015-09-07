/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/7/2015
 * Time: 12:24 AM
 */
package controllers.items.tags;

import com.google.inject.AbstractModule;

public class TagsControllerModule extends AbstractModule {
    protected void configure() {
        bind(TagsController.class).to(TagsControllerImpl.class);
    }
}
