package requests

import exceptions.BadRequestApiException
import news.hub.monolithic.User

class UserSignupRequest extends GenericRequest {
    private String firstName
    private String lastName
    private String email
    private String password
    private String passwordRepeated

    UserSignupRequest(def params) {
        this.firstName = params?.firstName
        this.lastName = params?.lastName
        this.email = params?.email
        this.password = params?.password
        this.passwordRepeated = params?.passwordRepeated
    }

    @Override
    Map<String, Object> getMandatoryFieldsMap() {
        return [
                'firstName': this.firstName,
                'lastName': this.lastName,
                'email': this.email,
                'password': this.password,
                'passwordRepeated': this.passwordRepeated,
        ]
    }

    @Override
    protected void validateCustom() throws BadRequestApiException {
        if (this.password != this.passwordRepeated) {
            throw new BadRequestApiException("the introduced password doesn't match with the validation")
        }
    }

    User toUser() throws BadRequestApiException {
        return new User(this.firstName, this.lastName, this.email, this.password)
    }
}
