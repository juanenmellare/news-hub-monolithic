package exceptions

class EmailInUseBadRequestApiException extends BadRequestApiException {

    EmailInUseBadRequestApiException(String email) {
        super("the e-mail $email is already in use")
    }
}
