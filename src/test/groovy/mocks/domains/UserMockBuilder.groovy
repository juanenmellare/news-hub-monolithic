package mocks.domains

import news.hub.monolithic.User

class UserMockBuilder {
    private String rawPassword = '12345'

    User user = new User('Juan', 'Test', 'juan@test.com', rawPassword)

    UserMockBuilder setId(String id) {
        this.user.setId(id)
        return this
    }

    UserMockBuilder setRandomId() {
        return this.setId(UUID.randomUUID().toString())
    }


    UserMockBuilder setFirstName(String firstName) {
        this.user.setFirstName(firstName)
        return this
    }

    UserMockBuilder setLastName(String lastName) {
        this.user.setLastName(lastName)
        return this
    }

    UserMockBuilder setEmail(String email) {
        this.user.setEmail(email)
        return this
    }

    UserMockBuilder setPassword(String password) {
        this.rawPassword = password
        return this
    }

    String getRawPassword() {
        return this.rawPassword
    }

    UserMockBuilder clear() {
        this.user = new User()
        return this
    }

    UserMockBuilder save() {
        this.user.save(flush: true, failOnError: true)
        return this
    }

    User build() {
        return this.user
    }
}
