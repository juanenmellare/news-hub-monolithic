package exceptions

import enums.HttpStatus

class BadRequestApiException extends ApiException {

    BadRequestApiException(String message = null) {
        super(message)
        this.status = HttpStatus.BAD_REQUEST
    }
}
