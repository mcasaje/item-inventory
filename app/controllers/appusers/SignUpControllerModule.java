/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/5/2015
 * Time: 4:53 PM
 */
package controllers.appusers;

import com.google.inject.AbstractModule;

public class SignUpControllerModule extends AbstractModule {
    protected void configure() {
        bind(SignUpController.class).to(SignUpControllerImpl.class).asEagerSingleton();
    }
}
