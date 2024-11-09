package Models;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "password")
    private String password;

//    @Column(name = "image")
//    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public User() {
        super();
    }

    public User(String username, String fullname, String birthday, String password, String address, String phone, String email) {
        this.username = username;
        this.fullname = fullname;
        this.birthday = birthday;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
