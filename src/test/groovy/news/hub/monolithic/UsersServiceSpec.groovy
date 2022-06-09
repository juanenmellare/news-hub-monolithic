package news.hub.monolithic

import exceptions.EmailInUseBadRequestApiException
import exceptions.EmptyFieldsBadRequestApiException
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import mocks.domains.UserMockBuilder
import spock.lang.Specification

class UsersServiceSpec extends Specification implements ServiceUnitTest<UsersService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [User] as Class[]
    }

    void "test findByEmail, founded"() {
        given:
        final User userExpected = new UserMockBuilder().build()
        userExpected.save()

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
        final User userExpected = new UserMockBuilder().build()
        userExpected.save()

        when:
        final User userFounded =
                service.getByEmailAndPassword(userExpected.getEmail(), userExpected.getPassword())

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
