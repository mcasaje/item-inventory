/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 3:32 PM
 */
package models.appusers;

import com.google.inject.AbstractModule;

public class AppUserModule extends AbstractModule {
    protected void configure() {
        bind(AppUserFactory.class).to(AppUserFactoryImpl.class).asEagerSingleton();
        bind(AppUserRepository.class).to(AppUserRepositoryImpl.class).asEagerSingleton();
        bind(AuthSecurityUtils.class).to(AuthSecurityUtilsImpl.class).asEagerSingleton();
    }
}
