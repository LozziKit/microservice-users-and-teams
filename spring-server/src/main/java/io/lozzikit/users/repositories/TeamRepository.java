package io.lozzikit.users.repositories;

import io.lozzikit.users.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    TeamEntity findById(Long id);

    @Override
    List<TeamEntity> findAll();

    @Override
    List<TeamEntity> findAll(Iterable<Long> iterable);
}