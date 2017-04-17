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
