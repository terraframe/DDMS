package dss.vector.solutions.general;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terraframe.mojo.generation.loader.Reloadable;

public class EmailContextListener implements ServletContextListener, Reloadable {

	public void contextInitialized(ServletContextEvent arg0) {
		Email.startDaemon();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		Email.stopDaemon();
	}
}
