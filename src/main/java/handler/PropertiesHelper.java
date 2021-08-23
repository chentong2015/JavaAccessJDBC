package handler;

import exception.MyAccessException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesHelper {

    public static String getDbConnectionURL() throws MyAccessException {
        Path path = FileSystems.getDefault().getPath("./");
        String configPath = path.toString() + "/config.properties";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(configPath));
            return properties.getProperty("connectionURL");
        } catch (IOException e) {
            throw new MyAccessException("Can not load properties file data");
        }
    }
}
