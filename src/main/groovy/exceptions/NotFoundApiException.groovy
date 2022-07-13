package exceptions

import enums.HttpStatus

class NotFoundApiException extends ApiException {

    NotFoundApiException(String message = null) {
        super("${message} not found")
        this.status = HttpStatus.NOT_FOUND
    }
}
