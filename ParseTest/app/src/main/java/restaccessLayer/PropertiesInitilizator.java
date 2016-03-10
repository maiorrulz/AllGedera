package restaccessLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Alex on 1/10/2016.
 */
public class PropertiesInitilizator {

    private String pathToConfigFile;

    public PropertiesInitilizator(String pathToFile) {
        pathToConfigFile = pathToFile;
    }

    public Map<String, String> initPropertyInfo() {
        Properties prop = new Properties();
        InputStream input = null;
        Map<String, String> propertiesMap = new HashMap<String, String>();

        if (!new File(pathToConfigFile).exists()) {
            System.err.println("Config file does not exist " + pathToConfigFile);
            return null;
        }
        try {
            input = new FileInputStream(pathToConfigFile);
            prop.load(input);
            input = new FileInputStream(pathToConfigFile);
            /*propertiesMap.put("ip", prop.getProperty("ip"));
            propertiesMap.put("restApiPath", prop.getProperty("restApiPath"));
            propertiesMap.put("eventPath", prop.getProperty("eventPath"));*/
            propertiesMap.put("ip", "http://172.16.240.61:8080/gadera");
            propertiesMap.put("restApiPath", "restapi");
            propertiesMap.put("eventPath", "events/test2");
            return propertiesMap;
        } catch (IOException e) {

            e.printStackTrace();
            return null;

        } finally {

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
