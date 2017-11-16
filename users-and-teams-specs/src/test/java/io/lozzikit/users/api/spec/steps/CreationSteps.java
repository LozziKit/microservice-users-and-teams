package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.dto.NewUser;
import io.lozzikit.users.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;

    private UserApi api;

    NewUser user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
        this.user = new io.lozzikit.users.api.dto.NewUser();
    }

    @Given("^there is a users server$")
    public void there_is_a_users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        assertNotNull(user);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        user.firstName("testFirst");
        user.lastName("testLast");
        user.email("test@test.com");
        user.username("test");
        // cryptez le mdp
        user.password("test");
        try {
            lastApiResponse = api.createUserWithHttpInfo(user);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertNotNull(lastStatusCode);
        switch (lastStatusCode) {
            case 201:
                i_have_a_link_to_this_user();
                break;
            case 409:
                i_don_t_have_a_link_to_this_user();
                break;
            default:
                break;
        }
    }

    @Then("^I have a link to this user$")
    public void i_have_a_link_to_this_user() throws Throwable {
        // check if the link is in the response
        // assertNotNull();
    }

    @Then("^I don't have a link to this user$")
    public void i_don_t_have_a_link_to_this_user() throws Throwable {
        // check if the link is in the response
        // assertNotNull();
    }
}
