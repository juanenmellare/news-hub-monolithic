package utils


class SessionUtils {
    final static String tokenKey = 'token'

    static getToken(session) {
        return session[tokenKey]
    }

    static setToken(session, String token) {
        return session[tokenKey] = token
    }
}
