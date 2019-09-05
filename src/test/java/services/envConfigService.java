package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;

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
        FileInputStream propFile = null;
        String envPropertyFile = MessageFormat.format("src/test/resources/env/{0}.properties", System.getProperty("env", "local"));
        propFile = new FileInputStream(envPropertyFile);
        Properties p = new Properties(System.getProperties());
        p.load(propFile);
        // set the system properties
        System.setProperties(p);
        // display new properties
        System.getProperties().list(System.out);
    }
}

