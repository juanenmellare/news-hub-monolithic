package mocks.domains

import news.hub.monolithic.User

class UserMockBuilder {
    User user = new User('Juan', 'Test', 'juan@test.com', '12345')

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
        this.user.setPassword(password)
        return this
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
