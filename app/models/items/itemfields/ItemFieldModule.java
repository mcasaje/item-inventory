/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 9:13 PM
 */
package models.items.itemfields;

import com.google.inject.AbstractModule;

public class ItemFieldModule extends AbstractModule {
    protected void configure() {
        bind(ItemFieldFactory.class).to(ItemFieldFactoryImpl.class);
        bind(ItemFieldRepository.class).to(ItemFieldRepositoryImpl.class);
    }
}
