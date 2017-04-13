package Models;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author José Basabe
 */
public class DataSource {
    private final Configuration config = new Configuration();
    DriverManagerDataSource source = new DriverManagerDataSource();
    public DriverManagerDataSource connect(){                    
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(config.getProperty("driver_classname"));
        source.setUrl(config.getProperty("url"));
        source.setUsername(config.getProperty("user"));
        source.setPassword(config.getProperty("password"));
        config.getProperty("password");
        return source;
    }
    public void closeConnection(){
        try {
            source.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}