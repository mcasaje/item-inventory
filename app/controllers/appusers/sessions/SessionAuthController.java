package controllers.appusers.sessions;

import play.mvc.Http;

/**
 * Checks that the user is authenticated. All methods that throw {@link UnauthorizedException} will check
 * that the session is valid and that the user is authenticated.
 */
public interface SessionAuthController {

    String USER_ID_SESS_KEY = "session_auth_user_id";
    String USERNAME_SESS_KEY = "session_auth_username";

    void checkAuth(Http.Session session) throws UnauthorizedException;

    int getUserId(Http.Session session) throws UnauthorizedException;

    String getUsername(Http.Session session) throws UnauthorizedException;

}
