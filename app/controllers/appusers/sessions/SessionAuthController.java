package controllers.appusers.sessions;

import play.mvc.Http;

public interface SessionAuthController {

    void isSessionValid(Http.Session session) throws UnauthorizedException;

}
