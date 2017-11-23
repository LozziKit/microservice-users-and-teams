package io.lozzikit.users.api.endpoints;

import io.lozzikit.users.api.AuthApi;
import io.lozzikit.users.api.model.Credentials;
import io.lozzikit.users.api.model.Token;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class AuthApiController implements AuthApi {

    @Autowired
    UserService userService;

    public ResponseEntity<Token> authUser(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials credentials) {
        UserEntity user = userService.getUserByUsername(credentials.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(user != null && passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
            //TODO Generate a real token (JWT)
            return ResponseEntity.ok(new Token());
        }
        else{
            return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
        }
    }
}
