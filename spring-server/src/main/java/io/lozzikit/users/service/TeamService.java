package io.lozzikit.users.service;

import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.entities.UserEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TeamService {
    TeamEntity getTeamById(Long id);

    List<TeamEntity> getTeamListWithPagination(int page, int size, Sort.Direction direction);

    List<TeamEntity> getTeamList();

    TeamEntity save(TeamEntity t);
}
