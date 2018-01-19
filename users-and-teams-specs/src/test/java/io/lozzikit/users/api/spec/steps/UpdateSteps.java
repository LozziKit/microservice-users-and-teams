package io.lozzikit.users.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.spec.helpers.Environment;
import io.lozzikit.users.api.dto.NewUser;
import io.lozzikit.users.api.dto.UserModified;
import io.lozzikit.users.api.dto.User;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mathieu on 19.01.18.
 */
public class UpdateSteps {
    // Our environment
    private Environment environment;

    // The object to encapsulate the API steps
    private ApiSteps apiSteps;

    // The UserApi instance to use
    private UserApi userApi;

    private NewUser existingUser;

    private UserModified modifiedUser;


    private ApiResponse lastApiResponse;

    public UpdateSteps(Environment environment, ApiSteps apiSteps) {
        this.environment = environment;
        this.apiSteps = apiSteps;

        this.userApi = environment.getUserApi();
    }

    @Given("^there is an existing user to update$")
    public void there_is_an_existing_user_to_update() throws ApiException {
        existingUser = new NewUser();
        existingUser.setUsername(UUID.randomUUID().toString());
        existingUser.password(UUID.randomUUID().toString().replace("-", ""));
        existingUser.firstName("John");
        existingUser.lastName("Rachid");
        existingUser.email(UUID.randomUUID().toString() + "@test.com");

        // Send
        userApi.createUserWithHttpInfo(existingUser);
    }


    @Given("^I have valid update payload$")
    public void i_have_valid_update_payload() throws Throwable {
        modifiedUser = new UserModified();
        modifiedUser.setFirstName(existingUser.getFirstName());
        modifiedUser.setLastName(existingUser.getLastName());
        modifiedUser.setEmail(existingUser.getEmail());
        modifiedUser.setPassword(existingUser.getPassword());
    }

    @Given("^I update firstname$")
    public void i_update_firstname() throws Throwable {
        modifiedUser.setFirstName(UUID.randomUUID().toString());
    }

    @Given("^I update lastname$")
    public void i_update_lastname() throws Throwable {
        modifiedUser.setLastName(UUID.randomUUID().toString());
    }

    @Given("^I update email$")
    public void i_update_email() throws Throwable {
        modifiedUser.setEmail(UUID.randomUUID().toString() + "@test.com");
    }

    @Given("^I update password$")
    public void i_update_password() throws Throwable {
        modifiedUser.setPassword(UUID.randomUUID().toString().replace("-", ""));
    }

    @When("^I PUT users from the /users endpoint$")
    public void i_PUT_users_from_the_users_endpoint() throws Throwable {

        try {
            lastApiResponse = environment.getUserApi().updateUserWithHttpInfo(existingUser.getUsername(), modifiedUser);
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(lastApiResponse.getStatusCode());
        } catch(ApiException e){
            apiSteps.setLastApiException(e);
        }
    }

    @Then("^The user has been updated$")
    public void i_ve_received_the_user_update_payload() throws Throwable {
        User user = userApi.getUser(existingUser.getUsername());

        assertEquals(modifiedUser.getFirstName(), user.getFirstName());
        assertEquals(modifiedUser.getLastName(), user.getLastName());
        assertEquals(modifiedUser.getEmail(), user.getEmail());
    }

    @Then("^The user has not been updated$")
    public void i_haven_t_received_the_user_update_payload() throws Throwable {
        User user = userApi.getUser(existingUser.getUsername());

        assertNotEquals(modifiedUser.getFirstName(), user.getFirstName());
        assertNotEquals(modifiedUser.getLastName(), user.getLastName());
        assertNotEquals(modifiedUser.getEmail(), user.getEmail());
    }
}
