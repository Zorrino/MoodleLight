package Models.Users;

public class Student extends User{
    public Student(Long id, String firstName, String lastName, String login, String password) {
        super(id, firstName, lastName, login, password);
    }
}
