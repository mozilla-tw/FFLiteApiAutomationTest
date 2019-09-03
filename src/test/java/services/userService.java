package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;


public class userService {

    private static Logger logger = LoggerFactory.getLogger(userService.class);


    /**
     * load user profile depends on environment
     */
    public void loadUserProfile() {

        String userPropertyFile = MessageFormat.format("src/test/resources/userProfile/{0}User.properties", System.getProperty("env", "local"));
        try (InputStream input = new FileInputStream(userPropertyFile)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            for (String name : prop.stringPropertyNames()) {
                String value = prop.getProperty(name);
                System.setProperty(name, value);
//                logger.info("{}:{}", name, value);
            }
            prop.list(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

