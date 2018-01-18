package io.lozzikit.users.api.endpoints;

import io.lozzikit.users.api.TeamsApi;
import io.lozzikit.users.api.model.NewTeam;
import io.lozzikit.users.api.model.Team;
import io.lozzikit.users.api.model.User;
import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.entities.UserTeamEntity;
import io.lozzikit.users.repositories.UserTeamRepository;
import io.lozzikit.users.service.TeamService;
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

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Adrian on 18.01.2018.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TeamsApiController implements TeamsApi {
    private final DaoDtoConverter daoDtoConverter = new DaoDtoConverter();

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Override
    public ResponseEntity<Void> createTeam(@ApiParam(value = "Created team object", required = true) @RequestBody NewTeam body) {
        try{
            TeamEntity newTeamEntity = new TeamEntity();
            Set<UserTeamEntity> members = new HashSet<>();

            newTeamEntity.setName(body.getName());
            teamService.save(newTeamEntity);

            for(User u : body.getMembers()) {
                UserTeamEntity ute = new UserTeamEntity();
                UserEntity ue = userService.getUserByUsername(u.getUsername());
                if(ue != null) {
                    ute.setUser(ue);
                    ute.setTeam(newTeamEntity);
                    userTeamRepository.save(ute);

                    members.add(ute);
                }
            }
            newTeamEntity.setMembers(members);
            teamService.save(newTeamEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newTeamEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        }catch(DataIntegrityViolationException dive){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Team> getTeam(@ApiParam(value = "The id of the team", required = true) @PathVariable("id") Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Team>> getTeams() {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateTeam(@ApiParam(value = "The id of the team that needs to be fetched", required = true) @PathVariable("id") Long id, @ApiParam(value = "Modified team object", required = true) @RequestBody Team body) {
        return null;
    }
}
