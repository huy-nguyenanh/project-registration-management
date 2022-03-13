
package utillsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

public class PropertiesHelper {
    
    public static Properties getProperties(ServletContext context, String fileRelativePath) {
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties pro = null;
        try {
            pro = new Properties();
            pro.load(input);
        } catch (IOException e) {
            Logger.getLogger(PropertiesHelper.class.getName()).log(Level.SEVERE, null, e);
        }
        return pro;
    }
}
