package tokens

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT

import java.time.Instant


abstract class Token {
    private Algorithm algorithm
    private Instant expiration
    private Map<String, Claim> claims = new HashMap<String, Claim>()
    private boolean isVerified = false

    protected Token(Algorithm algorithm, Instant expiration) {
        this.algorithm = algorithm
        this.expiration = expiration
    }

    protected Token(Algorithm algorithm) {
        this.algorithm = algorithm
    }

    protected void addToClaims(String key, Object value) {
        this.claims.put(key, value)
    }

    protected Algorithm getAlgorithm() {
        return this.algorithm
    }

    protected Map<String, Claim> getClaims() {
        return this.claims
    }

    Instant getExpiration() {
        return this.expiration
    }

    boolean isVerified() {
        return this.isVerified
    }

    String encode() {
        final JWTCreator.Builder tokenBuilder = JWT.create().withExpiresAt(this.getExpiration())
        this.claims.forEach((key, value) -> { tokenBuilder.withClaim(key, value) })
        final String tokenEncoded = tokenBuilder.sign(this.getAlgorithm())

        return tokenEncoded
    }

    void decode(String encodedToken) {
        if (encodedToken) {
            final JWTVerifier verifier = JWT.require(this.algorithm).build()
            final DecodedJWT tokenDecoded = verifier.verify(encodedToken)

            this.claims = tokenDecoded.claims
            this.expiration = tokenDecoded.expiresAtAsInstant
            this.isVerified = true
        }
    }
}
