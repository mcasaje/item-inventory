/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 4:06 PM
 */
package pages.appusers;

import com.google.inject.AbstractModule;

public class UsersPageModule extends AbstractModule {
    protected void configure() {
        bind(SignUpPage.class).to(SignUpPageImpl.class).asEagerSingleton();
        bind(LoginPage.class).to(LoginPageImpl.class).asEagerSingleton();
        bind(LogoutPage.class).to(LogoutPageImpl.class).asEagerSingleton();
        bind(DashboardPage.class).to(DashboardPageImpl.class).asEagerSingleton();
    }
}
