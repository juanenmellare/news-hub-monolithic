package exceptions

import enums.HttpStatus

class ApiException extends RuntimeException {
    HttpStatus status
    String message

    ApiException(String message) {
        this.message = message
    }
}
