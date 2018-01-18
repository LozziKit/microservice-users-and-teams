package io.lozzikit.users.repositories;

import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.entities.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, Long> {
    @Override
    List<UserTeamEntity> findAll();

    @Override
    List<UserTeamEntity> findAll(Iterable<Long> iterable);
}