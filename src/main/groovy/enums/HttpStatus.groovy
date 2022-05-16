package enums

enum HttpStatus {
    OK(200,"ok"),

    final int code
    final String status

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