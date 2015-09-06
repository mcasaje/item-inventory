/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 4:06 PM
 */
package pages.appusers;

import com.google.inject.AbstractModule;

public class SignUpPageModule extends AbstractModule {
    protected void configure() {
        bind(SignUpPage.class).to(SignUpPageImpl.class).asEagerSingleton();
    }
}
