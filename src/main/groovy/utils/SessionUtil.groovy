package utils

class SessionUtil {
    final static String userIdKey = 'userId'

    static getUserId(session) {
        return session[userIdKey]
    }

    static setUserId(session, String userId) {
        return session[userIdKey] = userId
    }
}
