package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;


public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * load user profile depends on environment
     */
    public void loadUserProfile() throws IOException {
        String userPropertyFile = MessageFormat.format("userProfile/{0}User.properties", System.getProperty("env"));
        Properties ret = PropertiesLoaderUtils.loadAllProperties(userPropertyFile);
        System.setProperties(ret);
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

