package io.lozzikit.users.utils;

import io.lozzikit.users.api.model.NewTeam;
import io.lozzikit.users.api.model.NewUser;
import io.lozzikit.users.api.model.Team;
import io.lozzikit.users.api.model.User;
import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.entities.UserTeamEntity;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DaoDtoConverter {

    private ModelMapper modelMapper;

    public DaoDtoConverter() {
        modelMapper = new ModelMapper();
    }

    public UserEntity toUserEntity(NewUser user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public UserEntity toUserEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }

    public TeamEntity toTeamEntity(Team team) {
        return modelMapper.map(team, TeamEntity.class);
    }

    public Team toTeam(TeamEntity entity) {
        Team team = modelMapper.map(entity, Team.class);

        List<User> users = new ArrayList<>();

        for(UserTeamEntity ute : entity.getMembers()) {
            users.add(toUser(ute.getUser()));
        }

        team.setMembers(users);
        return team;
    }

    public TeamEntity toTeamEntity(NewTeam team) { return modelMapper.map(team, TeamEntity.class);}
}
