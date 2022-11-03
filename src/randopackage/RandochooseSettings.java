package randopackage;

import java.io.File;

public class RandochooseSettings {
	public static void createSettingsFile() {
		String user = System.getProperty("user.home");
		System.out.println(user);
		
		File file = new File(user + "/Randochoose/settings.rdc");
	}
}
