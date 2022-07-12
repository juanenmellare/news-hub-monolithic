package requests

class UserSignInRequest extends GenericRequest {
    private String email
    private String password

    UserSignInRequest(def params) {
        this.email = params.email
        this.password = params.password
    }

    @Override
    Map<String, Object> getMandatoryFieldsMap() {
        return [
                'email': this.email,
                'password': this.password,
        ]
    }

    String getEmail() {
        return this.email
    }

    String getPassword() {
        return this.password
    }
}
