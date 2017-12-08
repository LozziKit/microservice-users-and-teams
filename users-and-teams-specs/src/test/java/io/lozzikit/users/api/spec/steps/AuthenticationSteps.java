package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.AuthApi;
import io.lozzikit.users.api.dto.Credentials;
import io.lozzikit.users.api.dto.NewUser;
import io.lozzikit.users.api.spec.helpers.Environment;
import io.lozzikit.users.auth.ApiKeyAuth;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mathieu on 01.12.17.
 */
public class AuthenticationSteps {
    // Our environment
    private Environment environment;

    // The object to encapsulate the API steps
    private ApiSteps apiSteps;

    // Last API response
    private ApiResponse lastApiResponse;


    // Credentials
    private AuthApi authApi;
    private Credentials credentials;
    private String lastUsername;
    private String lastPassword;
    private String token;

    // The user we will be creating
    NewUser credentialUser;

    public AuthenticationSteps(Environment environment, ApiSteps apiSteps) {
        // Global API
        this.environment = environment;
        this.apiSteps = apiSteps;

        // Specific utilities
        this.authApi = environment.getAuthApi();
        this.credentials = new io.lozzikit.users.api.dto.Credentials();
        this.token = null;
        this.credentialUser = new NewUser();
    }

    /*
    * Partie authentification temporairement ici en attendant de check comment
    * gérer les dépendances avec un second fichier
    */
    @Given("^I have a valid credentials payload$")
    public void i_have_a_valid_credentials_payload() throws Throwable {
        assertNotNull(credentialUser);

        // Populate the payload to have data to work with
        credentialUser.username(UUID.randomUUID().toString());
        credentialUser.password(UUID.randomUUID().toString().replace("-", ""));
        credentialUser.firstName("Jean");
        credentialUser.lastName("Charles");
        credentialUser.email(UUID.randomUUID().toString() + "@test.com");

        // Create the user to work with
        environment.getUserApi().createUserWithHttpInfo(credentialUser);

        // Save the credentials
        credentials.setUsername(credentialUser.getUsername());
        credentials.setPassword(credentialUser.getPassword());
    }

    @When("^I POST it to the /auth endpoint$")
    public void i_POST_it_to_the_auth_endpoint() throws Throwable {
        // We POST the user to create to the server
        try {
            lastApiResponse = authApi.authUserWithHttpInfo(credentials);
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(lastApiResponse.getStatusCode());
            token = ((List<String>)lastApiResponse.getHeaders().get("Authorization")).get(0);
        } catch (ApiException e) {
            apiSteps.setLastApiException(e);
            Map<String, List<String>> headers = e.getResponseHeaders();
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

    @Given("^I have a valid Authorization token$")
    public void i_have_a_valid_token_payload() throws Throwable {
        i_have_a_valid_credentials_payload();
        i_POST_it_to_the_auth_endpoint();

        // We retrieve the authentication "Bearer" from the API, and we set its value
        ApiKeyAuth auth = (ApiKeyAuth) environment.getUserApi().getApiClient().getAuthentication("Bearer");
        assertNotNull(auth);
        assertNotNull(token);
        auth.setApiKey(token);
    }

    @Given("^I don't have a valid Authorization token$")
    public void i_don_t_have_a_valid_Authorization_token() throws Throwable {
        ApiKeyAuth auth = (ApiKeyAuth) environment.getUserApi().getApiClient().getAuthentication("Bearer");
        assertNotNull(auth);
        auth.setApiKey(null);
    }
}
