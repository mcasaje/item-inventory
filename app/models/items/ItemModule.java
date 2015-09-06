/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 9:13 PM
 */
package models.items;

import com.google.inject.AbstractModule;

public class ItemModule extends AbstractModule {
    protected void configure() {
        bind(ItemFactory.class).to(ItemFactoryImpl.class);
        bind(ItemRepository.class).to(ItemRepositoryImpl.class);

        bind(TagFactory.class).to(TagFactoryImpl.class);
        bind(TagRepository.class).to(TagRepositoryImpl.class);

        bind(FieldFactory.class).to(FieldFactoryImpl.class);
        bind(FieldRepository.class).to(FieldRepositoryImpl.class);
    }
}
