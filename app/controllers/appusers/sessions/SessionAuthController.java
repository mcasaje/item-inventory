package controllers.appusers.sessions;

import play.mvc.Http;

public interface SessionAuthController {

    String USER_ID_SESS_KEY = "session_auth_user_id";
    String USERNAME_SESS_KEY = "session_auth_username";

    void checkAuth(Http.Session session) throws UnauthorizedException;

    int getUserId(Http.Session session) throws UnauthorizedException;

    String getUsername(Http.Session session) throws UnauthorizedException;

}
