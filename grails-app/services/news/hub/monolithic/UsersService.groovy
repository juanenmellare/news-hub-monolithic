package news.hub.monolithic

import exceptions.EmailInUseBadRequestApiException
import grails.gorm.transactions.Transactional
import utils.PasswordUtils

@Transactional
class UsersService {

    User findById(String id) {
        return User.findById(id)
    }

    User findByEmail(String email) {
        return User.findByEmail(email)
    }

    User save(User user) throws EmailInUseBadRequestApiException {
        final String email = user.getEmail()
        final User userWithEmail = this.findByEmail(email)
        if (userWithEmail) {
            throw new EmailInUseBadRequestApiException(email)
        }
        final User userSaved = user.save()
        return userSaved
    }

    User getByEmailAndPassword(String email, String password) {
        final User user = this.findByEmail(email)
        if (!user) {
            return null
        }

        final boolean isPasswordOk = PasswordUtils.verifyPassword(password, user.getPassword(), user.getSalt())
        return isPasswordOk ? user : null
    }
}
