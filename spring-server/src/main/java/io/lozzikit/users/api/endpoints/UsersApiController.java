package io.lozzikit.users.api.endpoints;

import io.lozzikit.users.api.UsersApi;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.api.model.User;
import io.lozzikit.users.api.model.NewUser;
import io.lozzikit.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody NewUser user) {

        try{
            UserEntity newUserEntity = toUserEntity(user);
            userRepository.save(newUserEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{username}")
                    .buildAndExpand(newUserEntity.getUsername()).toUri();

            return ResponseEntity.created(location).build();
        }catch(DataIntegrityViolationException dive){
            return ResponseEntity.status(409).build();
        }
    }

    @Override
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = toUser(userRepository.findByUsername(username));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>>  getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }

        return ResponseEntity.ok(users);
    }

    private UserEntity toUserEntity(NewUser user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        return user;
    }

}
