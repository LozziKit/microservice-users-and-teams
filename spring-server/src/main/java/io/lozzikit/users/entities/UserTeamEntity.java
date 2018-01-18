package io.lozzikit.users.entities;

import io.lozzikit.users.api.model.User;

import javax.persistence.*;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Created by Adrian on 18.01.2018.
 */
@Entity
public class UserTeamEntity implements Serializable{
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @ManyToOne
        @JoinColumn(name = "team_id")
        private TeamEntity team;

        protected PrimaryKey() {
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public TeamEntity getTeam() {
            return team;
        }

        public void setTeam(TeamEntity team) {
            this.team = team;
        }
    }

    @EmbeddedId
    PrimaryKey primaryKey = new UserTeamEntity.PrimaryKey();

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public PrimaryKey getPrimaryKey() {
        return this.primaryKey;
    }

    public UserEntity getUser() {
        return primaryKey.getUser();
    }

    public void setUser(UserEntity user) {
        primaryKey.setUser(user);
    }

    public TeamEntity getTeam() {
        return primaryKey.getTeam();
    }

    public void setTeam(TeamEntity team) {
        primaryKey.setTeam(team);
    }
}
