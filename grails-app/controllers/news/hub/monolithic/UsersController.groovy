package news.hub.monolithic

import exceptions.NotFoundApiException
import requests.UserSignInRequest
import requests.UserSignupRequest
import utils.SessionUtil

class UsersController {
    UsersService usersService

    def signup() {
        render view: '/users/signup'
    }

    def signIn() {
        render view: '/users/signIn'
    }

    def authenticate() {
        final UserSignInRequest userSignInRequest = new UserSignInRequest(params)
        userSignInRequest.validate()

        final User user = usersService.getByEmailAndPassword(userSignInRequest.getEmail(), userSignInRequest.getPassword())
        if (!user) {
            throw new NotFoundApiException("user with email ${userSignInRequest.getEmail()}")
        }

        SessionUtil.setUserId(session, user.id)

        redirect uri: '/'
    }

    def logout() {
        session.invalidate()

        redirect uri: '/'
    }

    def save() {
        final UserSignupRequest signUpSaveRequest = new UserSignupRequest(params)
        signUpSaveRequest.validate()

        final User userToSave = signUpSaveRequest.toUser()
        usersService.save(userToSave)

        redirect uri: '/'
    }
}
