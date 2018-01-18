package io.lozzikit.users.repositories;

import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.entities.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, Long> {

    UserTeamEntity findById(Long id);

    @Override
    List<UserTeamEntity> findAll();

    @Override
    List<UserTeamEntity> findAll(Iterable<Long> iterable);
}