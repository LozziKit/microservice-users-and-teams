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

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;

    private UserApi api;

    List<User> users;
    NewUser user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String location;

    public CreationSteps(Environment environment) throws ApiException {
        this.environment = environment;
        this.api = environment.getApi();
        this.user = new io.lozzikit.users.api.dto.NewUser();
        this.users = api.getUsers();
    }

    @Given("^there is a users server$")
    public void there_is_a_users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        assertNotNull(user);
    }

    @Given("^The users list exists$")
    public void the_user_list_exists() throws Throwable {
        assertNotNull(users);
    }

    @Given("^the firstName is not empty$")
    public void the_firstName_is_not_empty() throws Throwable {
        user.setFirstName("test");
    }

    @Given("^the firstName is empty$")
    public void the_firstName_is_empty() throws Throwable {
        user.setFirstName(null);
    }

    @Given("^the lastname is not empty$")
    public void the_lastname_is_not_empty() throws Throwable {
        user.setLastName("test");
    }

    @Given("^the lastname is empty$")
    public void the_lastname_is_empty() throws Throwable {
        user.setLastName(null);
    }

    @Given("^the email is not empty$")
    public void the_email_is_not_empty() throws Throwable {
        user.setEmail("test@test.com");
    }

    @Given("^the email is empty$")
    public void the_email_is_empty() throws Throwable {
        user.setEmail(null);
    }

    @Given("^the username is not empty$")
    public void the_username_is_not_empty() throws Throwable {
        user.setUsername("test");
    }

    @Given("^the username is empty$")
    public void the_username_is_empty() throws Throwable {
        user.setUsername(null);
    }

    @Given("^the password is not empty$")
    public void the_password_is_not_empty() throws Throwable {
        user.setPassword("pass");
    }

    @Given("^the password is empty$")
    public void the_password_is_empty() throws Throwable {
        user.setPassword(null);
    }

    @Given("^the username doesn't exists in the database$")
    public void the_username_doesn_t_exists_in_the_database() throws Throwable {
        User u = new User();
        u.setFirstName("testUsername");
        u.setLastName("testUsername");
        u.setEmail("testUsername@test.com");
        u.setUsername("testUsername");
        users.add(u);
    }

    @Given("^the username exists in the database$")
    public void the_username_exists_in_the_database() throws Throwable {
        User u = new User();
        u.setFirstName("testUsername");
        u.setLastName("testUsername");
        u.setEmail("test2@test.com");
        u.setUsername("test");
        users.add(u);
    }

    @Given("^the email doesn't exists in the database$")
    public void the_email_exists_in_the_database() throws Throwable {
        User u = new User();
        u.setFirstName("testEmail");
        u.setLastName("testEmail");
        u.setEmail("testEmail@test.com");
        u.setUsername("testEmail");
        users.add(u);
    }

    @Given("^the email exists in the database$")
    public void the_email_exists() throws Throwable {
        User u = new User();
        u.setFirstName("testEmail");
        u.setLastName("testEmail");
        u.setEmail("test@test.com");
        u.setUsername("test3");
        users.add(u);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createUserWithHttpInfo(user);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            Map<String, List<String>> headers = lastApiResponse.getHeaders();
            location = headers.get("Location").get(0);
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
            Map<String, List<String>> headers = lastApiException.getResponseHeaders();
            location = null;
            if (headers.get("Location") != null) {
                location = headers.get("Location").get(0);
            }
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
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
