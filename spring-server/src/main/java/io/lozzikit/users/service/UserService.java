package io.lozzikit.users.service;

import io.lozzikit.users.entities.UserEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserEntity getUserByUsername(String username);

    List<UserEntity> getUserListWithPagination(int page, int size, Sort.Direction direction);

    List<UserEntity> getUserList();

    UserEntity save(UserEntity u);
}
