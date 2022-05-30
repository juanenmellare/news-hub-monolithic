package news.hub.monolithic

class User {
    String id = UUID.randomUUID().toString()
    Date dateCreated
    Date lastUpdated
    String firstName
    String lastName
    String email
    String password

    static constraints = {
        id generator:'assigned'
        // email unique:'email_idx'
    }

    User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }
}
