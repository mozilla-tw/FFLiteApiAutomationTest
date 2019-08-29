package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    public void loadEnvProperties() {
        String envPropertyFile = MessageFormat.format("src/test/resources/env/{0}.properties", System.getProperty("env", "local"));
        try (InputStream input = new FileInputStream(envPropertyFile)) {

            // load env properties file
            Properties prop = new Properties();
            prop.load(input);

            // get the property value and print it out
            for (String name : prop.stringPropertyNames()) {
                String value = prop.getProperty(name);
                System.setProperty(name, value);
                logger.info("{}:{}", name, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

