package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Then;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.ApiResponse;

import static org.junit.Assert.assertEquals;

/**
 * Created by mathieu on 01.12.17.
 */
public class ApiSteps {

    // Last exception of the API. Every step needs to update it if needed
    private ApiException lastApiException;

    private int lastStatusCode;

    public ApiSteps() {

    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
        if (lastApiException != null) {
            setLastStatusCode(lastApiException.getCode());
        }
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}
