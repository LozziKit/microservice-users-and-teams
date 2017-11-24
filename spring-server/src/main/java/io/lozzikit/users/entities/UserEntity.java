package io.lozzikit.users.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 767 bytes is the stated prefix limitation for InnoDB tables in MySQL version 5.6 (and prior versions). It's
 * 1,000 bytes long for MyISAM tables. In MySQL version 5.7 and upwards this limit has been increased to 3072 bytes.
 * So for the unique key to work with all database collation (utf8_genral_ci, utf8mb4_unicode_ci, etc), the maximum
 * length of those column should be 191 and not 255 for varchar
 */
@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*@NotNull
    @Size(min=4)*/
    @Column(unique = true, length = 191)
    private String username;

    /*@NotNull
    @Size(min=2)*/
    private String firstName;

    //@NotNull
    //@Size(min=2)
    private String lastName;

    //@NotNull
    //@Size(min=6)
    @Column(unique = true, length = 191)
    private String email;

    //@NotNull
    //@Size(min=6)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
