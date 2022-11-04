package randopackage;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.*;

public class RandochooseSettings {
	public static String[] createSettingsFile() {
		String user = System.getProperty("user.home");
		System.out.println(user);
		
		File file = new File(user + "/Randochoose/settings.rdc");
		
		File directory = new File(user + "/Randochoose");
	    if (! directory.exists()){
	        directory.mkdir();
	    }
		
		try {
			if (file.createNewFile()) {
				String[] settings = writeSettingsFile("en");
				System.out.println("The settings file was prepared successfully.");
				return settings;
			}
			else {
				String[] settings = readSettingsFile();
				System.out.println("The settings file is already available.");
				return settings;
			}
		} catch (IOException e) {
			System.out.println("An error has occured.");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[] writeSettingsFile(String langcode) {
		String user = System.getProperty("user.home");
		System.out.println(user);
		String[] settings = {langcode};
		
		try {
		      FileWriter myWriter = new FileWriter(user + "/Randochoose/settings.rdc");
		      myWriter.write("/lang " + langcode);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error has occurred.");
		      e.printStackTrace();
		}
		
		return settings;
	}
	
	public static String[] readSettingsFile() {
		String user = System.getProperty("user.home");
		System.out.println(user);
		
		try {
			  String[] settings = {""};
		      File myObj = new File(user + "/Randochoose/settings.rdc");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  String data = myReader.nextLine();
		    	  System.out.println(data);
		        
		    	  String dataClear = data.replace("\n", "");
		    	  
		    	  if (dataClear.startsWith("/lang")) {
		    		  String langData = dataClear.replace("/lang ", "");
		    		  settings[0] = langData;
		    	  }
		    	  
		      }
		      myReader.close();
		      return settings;
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      return null;
		}
	}
	
	public static void settingsGui() {
		JDialog settingsDialog = new JDialog();
		settingsDialog.setTitle("Randochoose");
		
		JPanel settingsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		String[] settings = readSettingsFile();
		
		ResourceBundle langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		
		if (settings[0].contains("en")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		}
		
		else if (settings[0].contains("de")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_de");
		}
		
		JLabel settingsLbl = new JLabel(langRb.getString("settingsTxt"));
		settingsLbl.setFont(new Font("Arial", 0, 16));
		settingsPanel.add(settingsLbl);
		
		JPanel realSettings = new JPanel(new GridLayout(0, 2, 10, 10));
		
		JLabel langLbl = new JLabel(langRb.getString("langTxt"));
		langLbl.setFont(new Font("Arial", 0, 14));
		realSettings.add(langLbl);
		
		settingsPanel.add(realSettings);
		
		settingsDialog.add(settingsPanel);
		settingsDialog.pack();
		settingsDialog.setVisible(true);
	}
}
