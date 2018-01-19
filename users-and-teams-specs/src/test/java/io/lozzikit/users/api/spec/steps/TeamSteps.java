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
import io.lozzikit.users.api.dto.Team;

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

    private List<Team> receivedTeams = null;

    private long teamId;

    private Team receivedTeam;


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

    @When("^I GET teams from the /team endpoint$")
    public void i_GET_teams_from_the_team_endpoint() throws Throwable {
        try {
            ApiResponse<List<Team>> response = teamApi.getTeamsWithHttpInfo();
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(response.getStatusCode());
            receivedTeams = response.getData();
            lastApiResponse = response;
        } catch (ApiException e) {
            apiSteps.setLastApiException(e);
        }
    }

    @Then("^I've received a list of teams$")
    public void i_ve_received_a_list_of_teams() throws Throwable {
        assertNotNull(receivedTeams);
    }

    @Given("^I have a valid teamId$")
    public void i_have_a_valid_teamId() throws Throwable {
        i_have_a_valid_team_payload();
        i_POST_it_from_the_teams_endpoint();
        i_GET_teams_from_the_team_endpoint();
        i_ve_received_a_list_of_teams();

        Team t = receivedTeams.get(0);
        teamId = t.getId();
    }

    @When("^I GET team from the /team endpoint$")
    public void i_GET_team_from_the_team_endpoint() throws Throwable {
        try {
            ApiResponse<Team> response = teamApi.getTeamWithHttpInfo(teamId);
            apiSteps.setLastApiException(null);
            apiSteps.setLastStatusCode(response.getStatusCode());
            receivedTeam = response.getData();
            lastApiResponse = response;
        } catch (ApiException e) {
            apiSteps.setLastApiException(e);
        }
    }

    @Then("^I've received a team payload$")
    public void i_ve_received_a_team_payload() throws Throwable {
        assertNotNull(receivedTeam);
    }

    @Given("^I don't have a valid teamId$")
    public void i_don_t_have_a_valid_teamId() throws Throwable {
        i_GET_team_from_the_team_endpoint();
        i_haven_t_received_a_team_payload();
    }

    @Then("^I'haven't received a team payload$")
    public void i_haven_t_received_a_team_payload() throws Throwable {
        assertNull(receivedTeam);
    }

    @Given("^I have a valid teamPayload$")
    public void i_have_a_valid_teamPayload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I PUT it from the /team endpoint$")
    public void i_PUT_it_from_the_team_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I've received the team update payload$")
    public void i_ve_received_the_team_update_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I don't have a valid teamPayload$")
    public void i_don_t_have_a_valid_teamPayload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I haven't received the team update payload$")
    public void i_haven_t_received_the_team_update_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I update name of team with valid teamName$")
    public void i_update_name_of_team_with_valid_teamName() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I'v received the team update payload$")
    public void i_v_received_the_team_update_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I update the name of team and is already exists$")
    public void i_update_the_name_of_team_and_is_already_exists() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
