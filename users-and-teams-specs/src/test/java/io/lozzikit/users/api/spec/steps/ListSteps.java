package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.dto.User;
import io.lozzikit.users.api.spec.helpers.Environment;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mathieu on 01.12.17.
 */
public class ListSteps {
    private Environment environment;

    private ApiSteps apiSteps;

    private UserApi userApi;

    public ListSteps(Environment environment, ApiSteps apiSteps) {
        this.environment = environment;
        this.apiSteps = apiSteps;

        this.userApi = environment.getUserApi();
    }

    @When("^I GET users from the /users endpoint$")
    public void i_GET_users_from_the_users_endpoint() throws Throwable {
        // We retrieve the user from the endpoint /users
        try {
            apiSteps.setApiUserList(null);
            //Map<String, List<String>> headers = apiSteps.getRequestHeaders();
            //System.out.println(headers.get("Authorization").get(0));
            //userApi.getApiClient().addDefaultHeader("Authorization", headers.get("Authorization").get(0));
            ApiResponse<List<User>> usersResponse = userApi.getUsersWithHttpInfo();
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(usersResponse.getStatusCode());
            apiSteps.setApiUserList(usersResponse.getData());
        }catch(ApiException e){
            apiSteps.setLastApiException(e);
        }
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
