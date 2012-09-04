package com.asya.gotovskaya.trainingtask.dbconnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author asya
 */
public class PropertyReader {

    private static String fileName = "database.properties";
    private static String root = System.getProperty("file.separator");
    private static Properties properties = new Properties();

    public static Map<String, String> getDatabaseProperties(){
        Map<String, String> listProperties = new HashMap<String, String>();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            properties.load(inputStream);

            String database = properties.getProperty("database");
            String user = properties.getProperty("user");

            listProperties.put("database", database);
            listProperties.put("user", user);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  listProperties;
    }

}
