package controller.command;

import controller.constants.Const;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static Properties properties;
    static {
        try (InputStream is = new FileInputStream("src/main/resources/dbConnect.properties")) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        System.out.println(properties.getProperty(Const.URL));
    }
}
//dbConnect.properties
//        src/main/resources/dbConnect.properties