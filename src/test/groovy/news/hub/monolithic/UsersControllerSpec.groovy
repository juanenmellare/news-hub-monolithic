package news.hub.monolithic

import enums.HttpStatus
import exceptions.BadRequestApiException
import exceptions.EmptyFieldsBadRequestApiException
import exceptions.NotFoundApiException
import grails.testing.web.controllers.ControllerUnitTest
import mocks.domains.UserMockBuilder
import spock.lang.Specification

class UsersControllerSpec extends Specification implements ControllerUnitTest<UsersController> {

    void "test signup"() {
        when:
        controller.signup()

        then:
        response.status == HttpStatus.OK.code
        view == '/users/signup'
    }

    void "test signIn"() {
        when:
        controller.signIn()

        then:
        response.status == HttpStatus.OK.code
        view == '/users/signIn'
    }

    void "test authenticate"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {
            getByEmailAndPassword(userMock.getEmail(), userMock.getPassword()) >> userMock
        }
        controller.usersService = usersService
        controller.params.email = userMock.getEmail()
        controller.params.password = userMock.getPassword()

        when:
        controller.authenticate()

        then:
        response.status == 302
        response.redirectUrl == '/'
        session['userId'] != null
        session['userId'] == userMock.getId()
    }

    void "test authenticate, 400, missing fields"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {
            getByEmailAndPassword(userMock.getEmail(), userMock.getPassword()) >> userMock
        }
        controller.usersService = usersService

        when:
        controller.authenticate()

        then:
        final EmptyFieldsBadRequestApiException exception = thrown(EmptyFieldsBadRequestApiException)
        exception.message == 'fields [email, password] should not be empty'
    }

    void "test authenticate, 404, wrong password"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {
            getByEmailAndPassword(userMock.getEmail(), userMock.getPassword()) >> null
        }
        controller.usersService = usersService
        controller.params.email = userMock.getEmail()
        controller.params.password = userMock.getPassword()

        when:
        controller.authenticate()

        then:
        final NotFoundApiException exception = thrown(NotFoundApiException)
        exception.message == "user with email ${userMock.getEmail()} not found"
    }

    void "test logout"() {
        given:
        session['userId'] = 'foo-id'

        when:
        controller.logout()

        then:
        response.status == 302
        response.redirectUrl == '/'
        session['userId'] == null
    }

    void "test save"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {save(userMock) >> userMock }

        controller.usersService = usersService
        controller.params.firstName = userMock.getFirstName()
        controller.params.lastName = userMock.getLastName()
        controller.params.email = userMock.getEmail()
        controller.params.password = userMock.getPassword()
        controller.params.passwordRepeated = userMock.getPassword()

        when:
        controller.save()

        then:
        response.status == 302
        response.redirectUrl == '/'
    }

    void "test save, 400, missing fields"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {save(userMock) >> userMock }
        controller.usersService = usersService

        when:
        controller.save()

        then:
        final EmptyFieldsBadRequestApiException exception = thrown(EmptyFieldsBadRequestApiException)
        exception.message == 'fields [firstName, lastName, email, password, passwordRepeated] should not be empty'
    }

    void "test save, 400, mismatch password"() {
        given:
        final User userMock = new UserMockBuilder().build()
        final UsersService usersService = Stub(UsersService) {save(userMock) >> userMock }
        controller.usersService = usersService
        controller.params.firstName = userMock.getFirstName()
        controller.params.lastName = userMock.getLastName()
        controller.params.email = userMock.getEmail()
        controller.params.password = userMock.getPassword()
        controller.params.passwordRepeated = 'foo'

        when:
        controller.save()

        then:
        final BadRequestApiException exception = thrown(BadRequestApiException)
        exception.message == 'the introduced password doesn\'t match with the validation'
    }
}
