package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    Properties properties;
    private static PropertyReader propertyReader;
    static Logger logger = Logger.getLogger(PropertyReader.class);

    private PropertyReader(){
        String fileName = "config";
        properties = new Properties();
        try{
        properties.load(this.getClass().getClassLoader().getResourceAsStream(fileName+".properties"));
        logger.info(fileName+".properties file loaded successfully");}
        catch (FileNotFoundException e){
            logger.error(e.getMessage());
        }
        catch (IOException e){
            logger.error(e.getMessage());
        }

    }

    public static String getPropertyByKey(String key){
        return getPropertyReaderObject().properties.getProperty(key);
    }

    private static PropertyReader getPropertyReaderObject(){
        if(propertyReader==null){
            propertyReader = new PropertyReader();
        }
        return propertyReader;
    }
}

