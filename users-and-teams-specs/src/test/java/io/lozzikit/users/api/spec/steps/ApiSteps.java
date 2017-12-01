package io.lozzikit.users.api.spec.steps;

import cucumber.api.java.en.Then;
import io.lozzikit.users.ApiException;
import io.lozzikit.users.api.dto.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This ApiSteps class encapsulates the steps that might be use by
 * the whole API test features.
 *
 * It is the responsibility of the specific steps that use this
 * class to maintain the state of the fields it contains.
 *
 * Since the whole structure (properties and steps definitions) is quite
 * a mess, you'll find the getters/setters first, and at the bottom
 * of the file, the steps definition.
 */
public class ApiSteps {

    // Last exception of the API. Every step needs to update it if needed
    private ApiException lastApiException;

    // Last status code of a request to the API
    private int lastStatusCode;

    // The headers to use with a request
    private Map<String, List<String>> requestHeaders;

    // The Headers we received from the last API response
    private Map<String, List<String>> responseHeaders;

    // The list of users from the server
    private List<User> apiUserList;

    public ApiSteps() {
        setRequestHeaders(new HashMap<>());
        setResponseHeaders(new HashMap<>());
        setApiUserList(new LinkedList<>());
    }

    /*
    ====================================================
       GETTERS and SETTERS
    ====================================================
    */

    /**
     * Get the last ApiException that occurred. We will use as a convention
     * that if the ApiException is null, it means there wasn't any exception
     * that occurred when executing the last request to the API.
     * @return null if no exception or the last ApiException that occurred
     */
    public ApiException getLastApiException() {
        return lastApiException;
    }

    /**
     * Set the last ApiException. We'll use as convention that the user of this class
     * needs to set it to null if a request was successful.
     *
     * It automatically sets the last status code if the ApiException is
     * not null.
     * @param lastApiException the last ApiException or null
     */
    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
        if (lastApiException != null) {
            setLastStatusCode(lastApiException.getCode());
        }
    }

    /**
     * Get the last request status code.
     * @return the last request status code
     */
    public int getLastStatusCode() {
        return lastStatusCode;
    }

    /**
     * Set the last request status code
     * @param lastStatusCode the last request status code
     */
    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    /**
     * Get the API user list
     * @return
     */
    public List<User> getApiUserList() {
        return apiUserList;
    }

    /**
     * Set the API user list
     * @param apiUserList
     */
    public void setApiUserList(List<User> apiUserList) {
        this.apiUserList = apiUserList;
    }


    public Map<String, List<String>> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, List<String>> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /*
    ====================================================
       STEPS DEFINITION
    ====================================================
    */

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}
