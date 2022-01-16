package com.termgrid.api.appApi.clients.commons.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop != null) return prop;
        else throw new RuntimeException("property username is not specified in the data.properties file");
    }
    public String getbaseurl(){
        String prop = properties.getProperty("baseurl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseurl is not specified in the data.properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the data.properties file");
    }
}
