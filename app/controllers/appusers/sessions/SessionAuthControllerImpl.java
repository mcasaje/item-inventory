package controllers.appusers.sessions;

import models.datatypes.Tuple;
import play.mvc.Http;

class SessionAuthControllerImpl implements SessionAuthController {

    @Override
    public void checkAuth(Http.Session session) throws UnauthorizedException {
        this.getUser(session);
    }

    @Override
    public int getUserId(Http.Session session) throws UnauthorizedException {
        Tuple<Integer, String> user = this.getUser(session);
        return user.getKey();
    }

    @Override
    public String getUsername(Http.Session session) throws UnauthorizedException {
        Tuple<Integer, String> user = this.getUser(session);
        return user.getVal();
    }

    private Tuple<Integer, String> getUser(Http.Session session) throws UnauthorizedException {

        String userIdSessionKey = SessionAuthController.USER_ID_SESS_KEY;
        String usernameSessionKey = SessionAuthController.USERNAME_SESS_KEY;

        boolean hasUserId = session.containsKey(userIdSessionKey);
        boolean hasUsername = session.containsKey(usernameSessionKey);

        if (hasUserId && hasUsername) {

            String userId = session.get(userIdSessionKey);
            String username = session.get(usernameSessionKey);

            boolean userIdIsSet = userId != null && userId.length() > 0;
            boolean usernameIsSet = username != null && username.length() > 0;

            if (userIdIsSet && usernameIsSet) {

                return new Tuple<>(new Integer(userId), username);

            }

        }

        throw new UnauthorizedException();

    }

}
