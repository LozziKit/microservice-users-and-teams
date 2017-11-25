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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    // Our environment
    private Environment environment;

    // The api to use
    private UserApi api;

    // The list of users from the server
    List<User> users;

    // The user we will be creating
    NewUser user;

    // Headers
    private Map<String, List<String>> headers;

    // Utility properties to manage API responses
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String location;

    // Credentials
    private AuthApi authApi;
    private Credentials credentials;
    private String lastUsername;
    private String lastPasswrod;
    private Object token;

    public CreationSteps(Environment environment) throws ApiException {
        this.environment = environment;
        this.api = environment.getApi();
        this.authApi = environment.getAuthApi();
        this.user = new io.lozzikit.users.api.dto.NewUser();
  //      this.users = api.getUsers();
        this.credentials = new io.lozzikit.users.api.dto.Credentials();
        this.token = null;
    }

    @Given("^there is a users server$")
    public void there_is_a_users_server() throws Throwable {
        assertNotNull(api);
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

    @Given("^The users list exists$")
    public void the_user_list_exists() throws Throwable {
        assertNotNull(users);
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

    @Given("^the username doesn't exists in the database$")
    public void the_username_doesn_t_exists_in_the_database() throws Throwable {
        // We search in the list of users if one exists with the same username
        boolean doesntExistUsername = true;
        for (User u : users) {
            doesntExistUsername = doesntExistUsername
                    & !u.getUsername().equals(user.getUsername());
        }
        assertTrue(doesntExistUsername);
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
        api.createUserWithHttpInfo(u);
    }

    @Given("^the email doesn't exists in the database$")
    public void the_email_doesn_t_exists_in_the_database() throws Throwable {
        // We search in the list of users if one exists with the same email
        boolean doesntExistEmail = true;
        for (User u : users) {
            doesntExistEmail = doesntExistEmail
                    & !u.getUsername().equals(user.getEmail());
        }
        assertTrue(doesntExistEmail);
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
        api.createUserWithHttpInfo(u);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        // We POST the user to create to the server
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

    @When("^I GET users from the /users endpoint$")
    public void i_GET_users_from_the_users_endpoint() throws Throwable {
        // We retrieve the user from the endpoint /users
        try {
            ApiResponse<List<User>> usersResponse = api.getUsersWithHttpInfo();
            users = usersResponse.getData();
            lastStatusCode = usersResponse.getStatusCode();
        }catch(ApiException e){
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I've received a list of users$")
    public void i_ve_received_a_list_of_users() throws Throwable {
        assertNotNull(users);
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

    /*
    * Partie authentification temporairement ici en attendant de check comment
    * gérer les dépendances avec un second fichier
    */

    @Given("^I have a valid credentials payload$")
    public void i_have_a_valid_credentials_payload() throws Throwable {
        assertNotNull(user);

        // Populate the payload to have data to work with
        lastUsername = UUID.randomUUID().toString();
        lastPasswrod = UUID.randomUUID().toString().replace("-", "");
        user.username(lastUsername);
        user.password(lastPasswrod);
        user.firstName("Jean");
        user.lastName("Charles");
        user.email(UUID.randomUUID().toString() + "@test.com");

        // Créer un utilisateurs
        credentials.setUsername(lastUsername);
        credentials.setPassword(lastPasswrod);
        i_POST_it_to_the_users_endpoint();
    }

    @When("^I POST it to the /auth endpoint$")
    public void i_POST_it_to_the_auth_endpoint() throws Throwable {
        // We POST the user to create to the server
       try {
            lastApiResponse = authApi.authUserWithHttpInfo(credentials);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            Map<String, List<String>> headers = lastApiResponse.getHeaders();
            token = headers.get("Authorization");
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
            Map<String, List<String>> headers = lastApiException.getResponseHeaders();
            token = null;
        }
    }

    @Then("^I have a token to this user$")
    public void i_have_a_token_to_this_user() throws Throwable {
        assertNotNull(token);
    }

    @Then("^I don't have a token to this user$")
    public void i_don_t_have_a_token_to_this_user() throws Throwable {
        assertNull(token);
    }

    @Given("^the username credentials is empty$")
    public void the_username_credentials_is_empty() throws Throwable {
       credentials.setUsername(null);
    }

    @Given("^the password credentials is empty$")
    public void the_password_credentials_is_empty() throws Throwable {
        credentials.setPassword(null);
    }


    @Given("^the username credentials is bad$")
    public void the_username_credentials_is_bad() throws Throwable {
        credentials.setUsername("");
    }

    @Given("^the password credentials is bad$")
    public void the_password_credentials_is_bad() throws Throwable {
        credentials.setPassword("");
    }

    @Given("^I have a valid token payload$")
    public void i_have_a_valid_token_payload() throws Throwable {
        i_have_a_valid_credentials_payload();
        i_POST_it_to_the_auth_endpoint();
        // set les headers
    }
}
