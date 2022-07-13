package news.hub.monolithic

import exceptions.EmailInUseBadRequestApiException
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import mocks.domains.UserMockBuilder
import spock.lang.Specification

class UsersServiceSpec extends Specification implements ServiceUnitTest<UsersService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [User] as Class[]
    }

    void "test findById, founded"() {
        given:
        final User userExpected = new UserMockBuilder().save().build()

        when:
        final User userFounded = service.findById(userExpected.getId())

        then:
        userFounded == userExpected
    }

    void "test findById, not founded"() {
        given:
        final User userExpected = new UserMockBuilder().build()

        when:
        final User userFounded = service.findById(userExpected.getId())

        then:
        userFounded == null
    }

    void "test findByEmail, founded"() {
        given:
        final User userExpected = new UserMockBuilder().save().build()

        when:
        final User userFounded = service.findByEmail(userExpected.getEmail())

        then:
        userFounded == userExpected
    }

    void "test findByEmail, not founded"() {
        given:
        final User userExpected = new UserMockBuilder().build()

        when:
        final User userFounded = service.findByEmail(userExpected.getEmail())

        then:
        userFounded == null
    }

    void "test save, ok"() {
        given:
        final User user = new UserMockBuilder().build()

        when:
        final User userExpected = service.save(user)
        final User userSaved = User.findById(userExpected.getId())

        then:
        user == userExpected
        userSaved == userExpected
    }

    void "test save, email already in use"() {
        given:
        final User user = new UserMockBuilder().build()

        when:
        service.save(user)
        service.save(user)

        then:
        final EmailInUseBadRequestApiException exception = thrown(EmailInUseBadRequestApiException)
        exception.message == "the e-mail $user.email is already in use"
    }

    void "test getByEmailAndPassword, founded"() {
        given:
        final UserMockBuilder userMockBuilder = new UserMockBuilder()
        final User userExpected = userMockBuilder.save().build()

        when:
        final User userFounded =
                service.getByEmailAndPassword(userExpected.getEmail(), userMockBuilder.getRawPassword())

        then:
        userFounded == userExpected
    }

    void "test getByEmailAndPassword, not founded"() {
        given:
        final User userExpected = new UserMockBuilder().build()

        when:
        final User userFounded = service.getByEmailAndPassword(userExpected.getEmail(), "foo")

        then:
        userFounded == null
    }
}
