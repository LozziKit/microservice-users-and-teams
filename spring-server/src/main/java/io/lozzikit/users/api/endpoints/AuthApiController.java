package io.lozzikit.users.api.endpoints;

import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.lozzikit.users.api.AuthApi;
import io.lozzikit.users.api.model.Credentials;
import io.lozzikit.users.api.security.SecurityConstants;
import io.lozzikit.users.entities.UserEntity;
import io.lozzikit.users.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class AuthApiController implements AuthApi {

    @Autowired
    UserService userService;

    public ResponseEntity<String> authUser(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials credentials) {
        UserEntity user = userService.getUserByUsername(credentials.getUsername());

        if(user != null && Argon2Factory.create().verify(user.getPassword(), credentials.getPassword())){

            String JWT = Jwts.builder()
                    .setSubject(credentials.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                    .compact();


            return ResponseEntity.ok(JWT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
