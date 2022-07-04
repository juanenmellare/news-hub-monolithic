package news.hub.monolithic


class User {
    String id = UUID.randomUUID().toString()
    String firstName
    String lastName
    String email
    String password

    static mapping = {
        table "users"
    }

    static constraints = {
        id generator:'assigned'
    }

    User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }
}
