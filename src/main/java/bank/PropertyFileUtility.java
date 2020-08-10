package bank;

import java.io.FileReader;
import java.util.Properties;

public class PropertyFileUtility {
    //Read config property value as per given key
   public String getConfigProperties(String key) {
       String value = null;
       try {
           FileReader fileReader=new FileReader(System.getProperty("user.dir")+"/src/main/resources/config.properties");
           Properties properties=new Properties();
           properties.load(fileReader);
           value= properties.getProperty(key);
       }catch (Exception e){
           e.printStackTrace();
       }
       return value;
    }
}
