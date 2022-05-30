package news.hub.monolithic

import grails.gorm.transactions.Transactional

@Transactional
class UsersService {

    User findByEmail(String email) {
        return User.findByEmail(email)
    }

    User save(User user) {
        final User userWithEmail = this.findByEmail(user.email)
        if (userWithEmail) {
            throw new RuntimeException("the email is already in use")
        }
        final User userSaved = user.save()
        return userSaved
    }

    User getByEmailAndPassword(String email, String password) {
        final User user = this.findByEmail(email)
        return password == user?.password ? user : null
    }
}
