package io.lozzikit.users.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrian on 18.01.2018.
 */
@Entity
public class TeamEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 191, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "primaryKey.team")
    private Set<UserTeamEntity> members = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserTeamEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<UserTeamEntity> members) {
        this.members = members;
        for(UserTeamEntity ute : members) {
            ute.setTeam(this);
        }
    }
}
