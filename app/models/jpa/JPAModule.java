/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 6:06 PM
 */
package models.jpa;

import com.google.inject.AbstractModule;

public class JPAModule extends AbstractModule {
    protected void configure() {
        bind(JPAUtils.class).to(JPAUtilsImpl.class).asEagerSingleton();
    }
}
