package io.lozzikit.users.api.endpoints;

import io.lozzikit.users.api.UsersApi;
import io.lozzikit.sdk.annotation.Authentication;
import io.lozzikit.users.api.model.NewUser;
import io.lozzikit.users.api.model.Team;
import io.lozzikit.users.api.model.User;
import io.lozzikit.users.api.model.UserModified;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.entities.UserTeamEntity;
import io.lozzikit.users.service.UserService;
import io.lozzikit.users.utils.DaoDtoConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
    UserService userService;

    final DaoDtoConverter daoDtoConverter = new DaoDtoConverter();

    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody NewUser user) {
        try{
            UserEntity newUserEntity = daoDtoConverter.toUserEntity(user);

            userService.save(newUserEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{username}")
                    .buildAndExpand(newUserEntity.getUsername()).toUri();

            return ResponseEntity.created(location).build();
        }catch(DataIntegrityViolationException dive){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<List<Team>> getTeamsByUser(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathVariable("username") String username) {
        UserEntity ue = userService.getUserByUsername(username);

        if (ue == null) {
            return ResponseEntity.notFound().build();
        }

        List<Team> teams = new ArrayList<>();
        for(UserTeamEntity ute : ue.getTeams()) {
            teams.add(daoDtoConverter.toTeam(ute.getTeam()));
        }

        return ResponseEntity.ok(teams);
    }

    @Authentication
    @Override
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        UserEntity ue = userService.getUserByUsername(username);

        if (ue == null) {
            return ResponseEntity.notFound().build();
        }

        User user = daoDtoConverter.toUser(ue);
        return ResponseEntity.ok(user);
    }

    //@Authentication
    @Override
    public ResponseEntity<List<User>>  getUsers() {
        List<UserEntity> users = new ArrayList<>();
        List<User> usersToSend = new ArrayList<>();
        users = userService.getUserList();
        for (UserEntity userEntity : users) {
            usersToSend.add(daoDtoConverter.toUser(userEntity));
        }
        return ResponseEntity.ok(usersToSend);
    }

    @Authentication
    @Override
    public ResponseEntity<Void> updateUser(@ApiParam(value = "The name that needs to be fetched", required = true) @PathVariable("username") String username, @ApiParam(value = "Modified user object", required = true) @Valid @RequestBody UserModified body) {
        UserEntity ue = userService.getUserByUsername(username);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }

        ue.setPassword(body.getPassword());
        ue.setFirstName(body.getFirstName());
        ue.setLastName(body.getLastName());
        ue.setEmail(body.getEmail());

        try {
            userService.save(ue);
        }catch (DataIntegrityViolationException dive){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().build();
    }
}
