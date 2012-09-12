package com.asya.gotovskaya.trainingtask.dbconnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author asya
 */
public class PropertyReader {

    private static final String FILE_NAME = "database.properties";

    public static Map<String, String> getDatabaseProperties() {
        Map<String, String> mapProperties = new HashMap<String, String>();
        try {
            InputStream inputStream = PropertyReader.class.getClassLoader().getResource(FILE_NAME).openStream();

            Properties properties = new Properties();
            properties.load(inputStream);

            String database = properties.getProperty("database");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            mapProperties.put("database", database);
            mapProperties.put("user", user);
            mapProperties.put("password", password);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapProperties;
    }

}
