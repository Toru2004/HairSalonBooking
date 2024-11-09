package Models;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Employee() {
        super();
    }

    public Employee(String username, String fullname, String birthday, String password,
                    String address, String phone, String email) {
        super(username, fullname, birthday, password, address, phone, email);
    }

}