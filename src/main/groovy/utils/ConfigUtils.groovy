package utils


class ConfigUtils {
    static final int ENCRYPTION_ITERATIONS = getSystemValueOrDefault('ENCRYPTION_ITERATIONS', '10').toInteger().intValue()
    static final int ENCRYPTION_SALT_LENGTH = getSystemValueOrDefault('ENCRYPTION_SALT_LENGTH', '10').toInteger().intValue()
    static final int ENCRYPTION_KEY_LENGTH = getSystemValueOrDefault('ENCRYPTION_KEY_LENGTH', '10').toInteger().intValue()
    static final String ENCRYPTION_ALGORITHM = getSystemValueOrDefault('ENCRYPTION_ALGORITHM', 'PBEWithMD5AndDES')

    static final String USER_TOKEN_SECRET_KEY = getSystemValueOrDefault('USER_TOKEN_SECRET_KEY', 'hello_world')
    static final int USER_TOKEN_EXPIRATION_HOURS = getSystemValueOrDefault('USER_TOKEN_EXPIRATION_HOURS', '8').toInteger().intValue()

    private static getSystemValueOrDefault(String key, String defaultValue) {
        return System.getenv().getOrDefault(key, defaultValue)
    }
}
