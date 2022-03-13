package listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utillsHelper.PropertiesHelper;

public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        //sitemap.properties
        String siteMapsLocation = context.getInitParameter("SITE_MAP_LOCATION");
        Properties siteMapsPropertiy = PropertiesHelper.getProperties(context, siteMapsLocation);
        context.setAttribute("SITE_MAP", siteMapsPropertiy);
        context.log("Deployed!!!!");

        //authentication_admin.properties
        String authen_Location = context.getInitParameter("AUTHEN_LOCATION");
        Properties authen_Property = PropertiesHelper.getProperties(context, authen_Location);
        context.setAttribute("AUTHEN_LIST", authen_Property);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Undeploying....");
    }
}
