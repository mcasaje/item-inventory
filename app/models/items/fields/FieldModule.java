/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 9:13 PM
 */
package models.items.fields;

import com.google.inject.AbstractModule;

public class FieldModule extends AbstractModule {
    protected void configure() {
        bind(FieldFactory.class).to(FieldFactoryImpl.class).asEagerSingleton();
        bind(FieldRepository.class).to(FieldRepositoryImpl.class).asEagerSingleton();
    }
}
