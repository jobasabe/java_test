package Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class Configuration {
    private Properties propiedades = new Properties();
    private InputStream entrada = null;

    public Configuration() {
        try {   
            entrada =Configuration.class.getResourceAsStream("../configuration.properties");
            propiedades.load(entrada);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        
    public String getProperty(String propertyName){
        return propiedades.getProperty(propertyName);
    }        
}
