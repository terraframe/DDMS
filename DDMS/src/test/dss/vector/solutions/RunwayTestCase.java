/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
