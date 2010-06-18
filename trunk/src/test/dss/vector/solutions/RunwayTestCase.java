package dss.vector.solutions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import com.runwaysdk.facade.Facade;

import junit.framework.TestCase;

public class RunwayTestCase extends TestCase {
	
	  private static String sessionId;

		protected void setUp() throws Exception {
			super.setUp();
		    sessionId = Facade.login("MDSS", "mdsstest2", new Locale[]{Locale.US});
		}

		protected void tearDown() throws Exception {
			super.tearDown();
		}
		
		public void testAll() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
			Method[] methods = this.getClass().getDeclaredMethods();
			for (Method method: methods) {

				if (!method.getName().startsWith("test")) {
					//System.out.println(method.getName() + " doesn't start with test");
					continue;
				}
				
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length != 1 || parameterTypes[0] != String.class) {
					//System.out.println(method.getName() + " doesn't have a single String parameter");
					continue;
				}
				
				System.out.println(method.getName());
				method.invoke(this, sessionId);
			}
			
		}

}
