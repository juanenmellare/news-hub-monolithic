package news.hub.monolithic

import utils.PasswordUtils


class User {
    String id = UUID.randomUUID().toString()
    String firstName
    String lastName
    String email
    private String salt
    private String password

    static mapping = {
        table "users"
    }

    static constraints = {
        id generator:'assigned'
    }

    User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.salt = PasswordUtils.generateSalt()
        this.password = PasswordUtils.hashPassword(password, this.salt)
    }

    String getSalt() {
        return this.salt
    }

    String getPassword() {
        return this.password
    }
}
