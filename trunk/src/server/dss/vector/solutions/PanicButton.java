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

import java.io.File;
import java.io.IOException;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.FileIO;

public class PanicButton implements Reloadable {
	private static final boolean enabled = initEnabled();
	
	private static boolean initEnabled() {
		File f = new File(DeployProperties.getDeployPath() + "/panicButton.txt");
		if (f.exists()) {
			return false;
		} else {
			try {
				FileIO.write(f, "This file is the equivalent of a \"panic button\" for the system.  Deleting this \n" + 
						        "file will cause the following behaviour at the next restart:\n" + 
							    "   1) The \"emergency\" menu will be activated for all diseases\n" +
							    "   2) This file will be recreated.\n");
			} catch (IOException e) {
				// Oh, well...I guess we'll be in panic mode after restart, too!
			}
			return true;
		}
	}
	
	private PanicButton() {
		super();
	}
	
	public static boolean isEnabled() {
		return enabled;
	}
}
