package tokens

import com.auth0.jwt.algorithms.Algorithm
import utils.ConfigUtils
import utils.DateUtils


class UserToken extends Token {
    private static final algorithmHMAC256 = Algorithm.HMAC256(ConfigUtils.USER_TOKEN_SECRET_KEY)
    private final String userIdClaim = 'userId'

    UserToken(String userId) {
        super(algorithmHMAC256, DateUtils.getNowPlusHours(ConfigUtils.USER_TOKEN_EXPIRATION_HOURS))
        this.addToClaims(userIdClaim, userId)
    }

    UserToken() {
        super(algorithmHMAC256)
    }

    String getUserId() {
        return this.getClaims().get(userIdClaim).asString()
    }
}
