/*
 * Created by IntelliJ IDEA.
 * User: mcasaje
 * Date: 9/6/2015
 * Time: 11:42 AM
 */
package controllers.appusers.sessions;

import com.google.inject.AbstractModule;

public class SessionsAuthControllerModule extends AbstractModule {
    protected void configure() {
        bind(SessionAuthController.class).to(SessionAuthControllerImpl.class);
    }
}
