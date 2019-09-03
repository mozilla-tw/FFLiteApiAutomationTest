package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class envConfigService {
    private static Logger logger = LoggerFactory.getLogger(envConfigService.class);

    /**
     * check connection to server host
     *
     * @return
     */
    public int checkConnection() {
        String host = System.getProperty("host");
        String protocol = System.getProperty("protocol");
        String hostServer = MessageFormat.format("{0}://${1}/", protocol, host);
        RestAssured.baseURI = hostServer;
        Response response = RestAssured.given().get("/");
        int statusCode = response.getStatusCode();
        return statusCode;
    }

    /**
     * load properties based on server environment
     */
    public void loadEnvProperties() throws IOException {
        String envPropertyFile = MessageFormat.format("env/{0}.properties", System.getProperty("env"));
        Properties ret = PropertiesLoaderUtils.loadAllProperties(envPropertyFile);
        System.setProperties(ret);
    }
}

