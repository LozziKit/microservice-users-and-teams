package io.lozzikit.users.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;
import io.lozzikit.users.api.TeamApi;
import io.lozzikit.users.api.UserApi;
import io.lozzikit.users.api.spec.helpers.Environment;
import io.lozzikit.users.api.dto.NewTeam;
import io.lozzikit.users.api.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mathieu on 19.01.18.
 */
public class TeamSteps {

    // Our environment
    private Environment environment;

    // The object to encapsulate the API steps
    private ApiSteps apiSteps;

    private UpdateSteps updateSteps;

    // The UserApi instance to use
    private UserApi userApi;

    private TeamApi teamApi;

    private NewTeam newTeam;

    private ApiResponse lastApiResponse;

    private String location = null;


    public TeamSteps(Environment environment, ApiSteps apiSteps, UpdateSteps updateSteps) {
        this.environment = environment;
        this.apiSteps = apiSteps;
        this.updateSteps = updateSteps;

        this.userApi = environment.getUserApi();
        this.teamApi = environment.getTeamApi();
    }



    @Given("^I have a valid team payload$")
    public void i_have_a_valid_team_payload() throws Throwable {
        newTeam = new NewTeam();
        newTeam.setName(UUID.randomUUID().toString());

        List<User> users = new ArrayList<>();
        users.add(updateSteps.getExistingUser());
        newTeam.setMembers(users);
    }

    @Given("^I don't have a valid team payload$")
    public void i_don_t_have_a_valid_team_payload() throws Throwable {
        newTeam = new NewTeam();
        newTeam.setName("");
    }

    @Given("^The name of team is already exists$")
    public void the_name_of_team_is_already_exists() throws Throwable {
        i_have_a_valid_team_payload();
        i_POST_it_from_the_teams_endpoint();
        i_POST_it_from_the_teams_endpoint();
    }

    @When("^I POST it from the /teams endpoint$")
    public void i_POST_it_from_the_teams_endpoint() throws Throwable {
        try {
            lastApiResponse = teamApi.createTeamWithHttpInfo(newTeam);
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

    @Then("^I've received the team location$")
    public void i_ve_received_the_team_location() throws Throwable {
        assertNotNull(location);
    }

    @Then("^I haven't received the team location$")
    public void i_ve_not_received_the_team_location() throws Throwable {
        assertNull(location);
    }
}
