package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.dto.NewUser;
import io.lozzikit.users.api.dto.User;
import io.lozzikit.users.api.spec.helpers.Environment;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mathieu on 01.12.17.
 */
public class ListSteps {
    // Our environment
    private Environment environment;

    // The object to encapsulate the API steps
    private ApiSteps apiSteps;

    // The UserApi instance to use
    private UserApi userApi;

    // The username of the user we want to get
    private String usernameToList;

    // The last api response
    private ApiResponse<User> lastApiResponse;

    // The received user dto
    private User receivedUser;

    public ListSteps(Environment environment, ApiSteps apiSteps) {
        // Global
        this.environment = environment;
        this.apiSteps = apiSteps;

        // Specific
        this.userApi = environment.getUserApi();
    }

    @When("^I GET users from the /users endpoint$")
    public void i_GET_users_from_the_users_endpoint() throws Throwable {
        // We retrieve the user from the endpoint /users
        try {
            apiSteps.setApiUserList(null);
            ApiResponse<List<User>> usersResponse = userApi.getUsersWithHttpInfo();
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(usersResponse.getStatusCode());
            apiSteps.setApiUserList(usersResponse.getData());
        }catch(ApiException e){
            apiSteps.setLastApiException(e);
        }
    }

    @Given("^I have an existing user username$")
    public void i_have_an_existing_user_username() throws Throwable {
        NewUser user = new NewUser();
        user.username(UUID.randomUUID().toString());
        user.password(UUID.randomUUID().toString().replace("-", ""));
        user.firstName("Jean");
        user.lastName("Charles");
        user.email(UUID.randomUUID().toString() + "@test.com");

        userApi.createUserWithHttpInfo(user);

        usernameToList = user.getUsername();
    }

    @When("^I GET the user from the /users endpoint$")
    public void i_GET_the_user_from_the_users_endpoint() {
        try {
            ApiResponse<User> response = userApi.getUserWithHttpInfo(usernameToList);
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(response.getStatusCode());
            receivedUser = response.getData();
            lastApiResponse = response;
        } catch (ApiException e) {
            apiSteps.setLastApiException(e);
        }
    }

    @Then("^I've received a user payload$")
    public void i_ve_received_a_user_payload() throws Throwable {
        assertNotNull(receivedUser);
    }

    @Given("^I have a non-existent user username$")
    public void i_have_a_non_existent_user_username() throws Throwable {
        usernameToList = UUID.randomUUID().toString();
    }

    @Then("^I'haven't received a user payload$")
    public void i_haven_t_received_a_user_payload() throws Throwable {
        assertNull(receivedUser);
    }

    @Then("^I've received a list of users$")
    public void i_ve_received_a_list_of_users() throws Throwable {
        assertNotNull(apiSteps.getApiUserList());
    }

    @Then("^I haven't received a list of users$")
    public void i_haven_t_received_a_list_of_users() throws Throwable {
        assertNull(apiSteps.getApiUserList());
    }
}
