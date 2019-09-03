package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;


public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * load user profile depends on environment
     */
    public void loadUserProfile() throws IOException {
        FileInputStream propFile = null;
        String userPropertyFile = MessageFormat.format("src/test/resources/userProfile/{0}User.properties", System.getProperty("env", "local"));
        propFile = new FileInputStream(userPropertyFile);
        Properties p = new Properties(System.getProperties());
        p.load(propFile);
        // set the system properties
        System.setProperties(p);
        // display new properties
        System.getProperties().list(System.out);
    }

    /**
     * setup login request with firefox email
     *
     * @return
     */
    public RequestSpecification login() {
        // build request spec
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RequestSpecification requestSpec = requestSpecBuilder.build();
        return requestSpec;
    }
}

