/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 9:13 PM
 */
package models.items.tags;

import com.google.inject.AbstractModule;

public class TagModule extends AbstractModule {
    protected void configure() {
        bind(TagFactory.class).to(TagFactoryImpl.class).asEagerSingleton();
        bind(TagRepository.class).to(TagRepositoryImpl.class).asEagerSingleton();
    }
}
