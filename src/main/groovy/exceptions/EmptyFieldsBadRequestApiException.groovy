package exceptions

class EmptyFieldsBadRequestApiException extends BadRequestApiException {

    EmptyFieldsBadRequestApiException(List<String> fields) {
        super("fields $fields should not be empty")
    }
}
