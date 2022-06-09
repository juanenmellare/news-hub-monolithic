package news.hub.monolithic

import grails.testing.gorm.DomainUnitTest
import mocks.domains.UserMockBuilder
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    void "new user"() {
        when:
        final User user = new UserMockBuilder().build()

        then:
        user.id != null
        user.firstName != null
        user.lastName != null
        user.email != null
        user.password != null
    }
}
