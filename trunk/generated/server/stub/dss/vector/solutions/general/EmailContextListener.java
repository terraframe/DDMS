package dss.vector.solutions.general;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terraframe.mojo.generation.loader.Reloadable;

public class EmailContextListener implements ServletContextListener, Reloadable {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Email.startDaemon();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Email.stopDaemon();
	}
}
