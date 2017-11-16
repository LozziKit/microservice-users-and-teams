package io.lozzikit.users.api.spec.helpers;

import io.lozzikit.users.api.UserApi;

import java.io.IOException;
import java.util.Properties;


/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private UserApi api = new UserApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.lozzikit.users.server.url");
        api.getApiClient().setBasePath(url);

    }

    public UserApi getApi() {
        return api;
    }


}
