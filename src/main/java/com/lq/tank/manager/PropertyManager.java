package com.lq.tank.manager;

import java.io.IOException;
import java.util.Properties;

/**
 * @author babei
 * @date 2021/10/17
 */
public class PropertyManager {

    private PropertyManager(){

    }

    static Properties prop = new Properties();

    static{
        try {
            prop.load(PropertyManager.class.getClassLoader().getResourceAsStream("config/tankwar.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(prop == null){
            return null;
        }
        return prop.get(key);
    }

    public static Integer getInt(String key){
        if(prop == null){
            return null;
        }
        return Integer.parseInt((String)prop.getProperty(key));
    }

    public static void main(String[] args) {

        System.out.println(PropertyManager.get("initTankCount"));
    }
}
