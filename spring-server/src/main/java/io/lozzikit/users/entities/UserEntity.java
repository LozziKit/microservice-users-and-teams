package io.lozzikit.users.entities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(unique = true, length = 191)
    private String username;

    private String firstName;

    private String lastName;

    @Column(unique = true, length = 191)
    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primaryKey.user")
    private Set<UserTeamEntity> teams = new HashSet<>();

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

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = Argon2Factory.create().hash(2, 256, 1, password);
    }

    public Set<UserTeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(Set<UserTeamEntity> teams) {
        this.teams = teams;
        for(UserTeamEntity ute : teams) {
            ute.setUser(this);
        }
    }
}
