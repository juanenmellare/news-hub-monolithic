package mocks.domains

import news.hub.monolithic.User

class UserMockBuilder {
    User user = new User('Juan', 'Test', 'juan@test.com', '12345')

    UserMockBuilder setFirstName(firstName) {
        this.user.setFirstName(firstName)
        return this
    }

    UserMockBuilder setLastName(lastName) {
        this.user.setLastName(lastName)
        return this
    }

    UserMockBuilder setEmail(email) {
        this.user.setEmail(email)
        return this
    }

    UserMockBuilder setPassword(password) {
        this.user.setPassword(password)
        return this
    }

    UserMockBuilder clear() {
        this.user = new User()
        return this
    }

    User build() {
        return this.user
    }
}
