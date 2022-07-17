package news.hub.monolithic

import exceptions.NotFoundApiException
import requests.UserSignInRequest
import requests.UserSignupRequest
import tokens.UserToken
import utils.SessionUtils

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

        final String token = new UserToken(user.id).encode()
        SessionUtils.setToken(session, token)

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

        redirect uri: '/signIn'
    }
}
