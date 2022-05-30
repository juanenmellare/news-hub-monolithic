package requests

import exceptions.BadRequestApiException
import exceptions.EmptyFieldsBadRequestApiException

abstract class GenericRequest {

    abstract Map<String, Object> getMandatoryFieldsMap()

    protected void validateCustom() throws BadRequestApiException {}

    void validate() throws BadRequestApiException {
        final Map<String, Object> mandatoryFields = this.getMandatoryFieldsMap()

        List<String> missingFields = new ArrayList<String>()

        mandatoryFields.each {name, value ->
            if (!value) {
                missingFields.add(name)
            }
        }

        if (missingFields) {
            throw new EmptyFieldsBadRequestApiException(missingFields)
        }

        this.validateCustom()
    }
}
