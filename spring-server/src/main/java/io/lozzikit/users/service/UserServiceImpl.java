package io.lozzikit.users.service;

import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @brief Get a specific user in the database
     * @param username Username of the user
     * @return The user
     */
    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * @brief Get the list of all user sort by username
     * @param page page number
     * @param size number of user in a page
     * @param order ASC or DESC order
     * @return The list of user
     */
    @Override
    public List<UserEntity> getUserListWithPagination(int page, int size, Sort.Direction order) {
        return userRepository.findAll(new PageRequest(page, size, order, "username")).getContent();
    }

    @Override
    public List<UserEntity> getUserList() {
        List<UserEntity> users = new ArrayList<>();

        users.addAll(userRepository.findAll());

        return users;
    }

    @Override
    public UserEntity save(UserEntity u) {
        return userRepository.save(u);
    }
}