package io.lozzikit.users.repositories;

import io.lozzikit.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    @Override
    List<UserEntity> findAll();

    @Override
    List<UserEntity> findAll(Iterable<Long> iterable);
}