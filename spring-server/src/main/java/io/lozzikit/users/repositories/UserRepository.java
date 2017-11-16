package io.lozzikit.users.repositories;

import io.lozzikit.users.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    UserEntity findByUsername(String username);
}
