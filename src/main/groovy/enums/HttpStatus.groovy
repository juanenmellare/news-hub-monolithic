package enums

enum HttpStatus {
    OK(200, "ok"),
    BAD_REQUEST(400, "bad request"),
    NOT_FOUND(404, "not found")


    final private int code
    final private String status

    HttpStatus(int code, String status) {
        this.code = code
        this.status = status
    }

    int getCode() {
        this.code
    }

    String getStatus() {
        this.status
    }
}