package utils

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import java.security.SecureRandom


class PasswordUtils {
    private static final SecureRandom secureRandom = new SecureRandom()

    static String generateSalt() {
        final int saltLength = ConfigUtils.ENCRYPTION_SALT_LENGTH
        final byte[] salt = new byte[saltLength]
        secureRandom.nextBytes(salt)

        return Base64.getEncoder().encodeToString(salt)
    }

    static String hashPassword(String password, String salt) {
        final char[] passwordChars = password.toCharArray()
        final byte[] saltBytes = salt.getBytes()
        final int iterations = ConfigUtils.ENCRYPTION_ITERATIONS
        final int keyLength = ConfigUtils.ENCRYPTION_KEY_LENGTH
        final PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength)
        Arrays.fill(passwordChars, Character.MIN_VALUE)

        final String algorithm = ConfigUtils.ENCRYPTION_ALGORITHM
        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm)
        final byte[] securePassword = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded()
        pbeKeySpec.clearPassword()

        return Base64.getEncoder().encodeToString(securePassword)
    }

    static boolean verifyPassword(String candidatePassword, String hashedPassword, String salt) {
        final String candidatePasswordHashed = hashPassword(candidatePassword, salt)

        return candidatePasswordHashed == hashedPassword
    }
}
