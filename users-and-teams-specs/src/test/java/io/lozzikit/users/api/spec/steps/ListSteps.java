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

import static org.junit.Assert.assertNotNull;

/**
 * Created by mathieu on 16.11.17.
 */
public class ListSteps {

    private Environment environment;

    private UserApi api;

    List<User> users;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private int lastStatusCode;

    public ListSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I GET users from the /users endpoint$")
    public void i_GET_users_from_the_users_endpoint() throws Throwable {
        users = api.getUsers();
    }

    @Then("^I've received a list of users$")
    public void i_ve_received_a_list_of_users() throws Throwable {
        assertNotNull(users);
    }
}
