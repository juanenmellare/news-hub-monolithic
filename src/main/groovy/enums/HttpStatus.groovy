package enums

enum HttpStatus {
    OK(200, "ok"),
    ACCEPTED(202, "accepted"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    NOT_FOUND(404, "not found")


    final private int code
    final private String status

    HttpStatus(int code, String status) {
        this.code = code
        this.status = status
    }

    int getCode() {
        return this.code
    }

    String getStatus() {
        return this.status
    }
}