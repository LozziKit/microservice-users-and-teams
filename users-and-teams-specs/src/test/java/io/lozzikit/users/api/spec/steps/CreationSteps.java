package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.AuthApi;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.dto.Credentials;
import io.lozzikit.users.api.dto.NewUser;
import io.lozzikit.users.api.dto.User;
import io.lozzikit.users.api.spec.helpers.Environment;

import javax.jws.soap.SOAPBinding;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    // Our environment
    private Environment environment;

    // The object to encapsulate the API steps
    private ApiSteps apiSteps;

    // The api to use
    private UserApi userApi;

    // The user we will be creating
    NewUser user;

    // Utility properties to manage API responses
    private ApiResponse lastApiResponse;
    private String location;

    public CreationSteps(Environment environment, ApiSteps apiSteps) throws ApiException {
        // Dependencies injections
        this.environment = environment;
        this.apiSteps = apiSteps;

        // Local utilities
        this.userApi = environment.getUserApi();
        this.user = new io.lozzikit.users.api.dto.NewUser();
    }

    @Given("^there is a users server$")
    public void there_is_a_users_server() throws Throwable {
        assertNotNull(userApi);
    }

    @Given("^I have a valid user payload$")
    public void i_have_a_user_payload() throws Throwable {
        assertNotNull(user);

        // Populate the payload to have data to work with
        user.username(UUID.randomUUID().toString());
        user.password(UUID.randomUUID().toString().replace("-", ""));
        user.firstName("John");
        user.lastName("Rachid");
        user.email(UUID.randomUUID().toString() + "@test.com");
    }

    @Given("^the firstName is empty$")
    public void the_firstName_is_empty() throws Throwable {
        user.setFirstName(null);
    }

    @Given("^the lastname is empty$")
    public void the_lastname_is_empty() throws Throwable {
        user.setLastName(null);
    }

    @Given("^the email is empty$")
    public void the_email_is_empty() throws Throwable {
        user.setEmail(null);
    }

    @Given("^the username is empty$")
    public void the_username_is_empty() throws Throwable {
        user.setUsername(null);
    }

    @Given("^the password is empty$")
    public void the_password_is_empty() throws Throwable {
        user.setPassword(null);
    }

    @Given("^the username exists in the database$")
    public void the_username_exists_in_the_database() throws Throwable {
        // We intentionally create a new user with the same username as the user to create
        NewUser u = new NewUser();
        u.setFirstName("Jonathan");
        u.setLastName("Welson");
        u.setEmail(UUID.randomUUID().toString().replace("-", "") + "@test.com");
        u.setUsername(user.getUsername());
        u.setPassword("password");
        userApi.createUserWithHttpInfo(u);
    }

    @Given("^the email exists in the database$")
    public void the_email_exists_in_the_database() throws Throwable {
        // We intentionally create a new user with the same email as the user to create
        NewUser u = new NewUser();
        u.setFirstName("Jonathan");
        u.setLastName("Welson");
        u.setEmail(user.getEmail());
        u.setUsername(UUID.randomUUID().toString());
        u.setPassword("password");
        userApi.createUserWithHttpInfo(u);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        // We POST the user to create to the server
        try {
            lastApiResponse = userApi.createUserWithHttpInfo(user);
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(lastApiResponse.getStatusCode());
            Map<String, List<String>> headers = lastApiResponse.getHeaders();
            location = headers.get("Location").get(0);
        } catch (ApiException e) {
            apiSteps.setLastApiException(e);
            Map<String, List<String>> headers = e.getResponseHeaders();
            location = null;
            if (headers.get("Location") != null) {
                location = headers.get("Location").get(0);
            }
        }
    }

    @Then("^I have a link to this user$")
    public void i_have_a_link_to_this_user() throws Throwable {
        assertNotNull(location);
    }

    @Then("^I don't have a link to this user$")
    public void i_don_t_have_a_link_to_this_user() throws Throwable {
        assertNull(location);
    }
}
