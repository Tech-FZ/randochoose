package randopackage;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RandochooseUpdateStageTwo {
	static String[] newestVer = new String[3];
	static int[] newestVerInt = new int[3];
	
	public static void checkStage(int majorVer, int minorVer1, int minorVer2, int verCode, boolean manual) {
		File file = new File("vercheck.rdc");
		
		if (!file.canRead() || !file.isFile()) {
			@SuppressWarnings("unused")
			BufferedReader in = null;
		}
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("vercheck.rdc"));
			String line = null;
			
			int i = 0;
			
			while ((line = in.readLine()) != null) {
				newestVer[i] = line;
				i++;
			}
			
			if (in != null) {
				try {
					in.close();
				}
				
				catch (Exception e) {
					
				}
			}
		}
		
		catch (Exception e) {
			
		}
		
		int i = 0;
		
		while (i < newestVer.length) {
			int verPart = Integer.parseInt(newestVer[i]);
			newestVerInt[i] = verPart;
			i++;
		}
		
		if (minorVer2 < newestVerInt[2] || minorVer1 < newestVerInt[1] || majorVer < newestVerInt[0] || verCode < newestVerInt[3]) {
			int[] currentVer = {majorVer, minorVer1, minorVer2};
			newVerAvailable(currentVer, newestVerInt);
		}
		
		else {
			if (manual) {
				isLatest();
			}
		}
	}
	
	public static void newVerAvailable(int[] currVer, int[] latestVer) {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose Update Management");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel("An update has been released.");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel("You're currently running version " + currVer[0] + "." + currVer[1] + "." + currVer[2]);
		noticeLabel2.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel2);
		
		JLabel noticeLabel3 = new JLabel("The latest version is " + latestVer[0] + "." + latestVer[1] + "." + latestVer[2]);
		noticeLabel3.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel3);
		
		JLabel questionLbl = new JLabel("Do you want to be redirected to the GitHub or Codeberg repository of");
		questionLbl.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(questionLbl);
		
		JLabel questionLbl2 = new JLabel("Randochoose to download the latest version?");
		questionLbl2.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(questionLbl2);
		
		JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		JButton yesBtn = new JButton("Yes, on GitHub");
		yesBtn.setFont(new Font("Arial", 0, 14));
		
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desk = Desktop.getDesktop();
				
				try {
					desk.browse(new URI("https://github.com/Tech-FZ/randochoose/releases"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				noEntryDialog.setVisible(false);
			}
		});
		
		btnPanel.add(yesBtn);
		
		JButton codebergBtn = new JButton("Yes, on Codeberg");
		codebergBtn.setFont(new Font("Arial", 0, 14));
		
		codebergBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desk = Desktop.getDesktop();
				
				try {
					desk.browse(new URI("https://codeberg.org/lucien-rowan/randochoose/releases"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				noEntryDialog.setVisible(false);
			}
		});
		
		btnPanel.add(codebergBtn);
		
		JButton noBtn = new JButton("No");
		noBtn.setFont(new Font("Arial", 0, 14));
		
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				noEntryDialog.setVisible(false);
			}
		});
		
		btnPanel.add(noBtn);
		
		noEntryPanel.add(btnPanel);
		
		noEntryDialog.add(noEntryPanel);
		noEntryDialog.pack();
		noEntryDialog.setVisible(true);
	}
	
	public static void isLatest() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose Update Management");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel("Don't know if you see this as good or bad, but...");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel("...you're already running the latest version.");
		noticeLabel2.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel2);
		
		JButton okBtn = new JButton("OK!");
		okBtn.setFont(new Font("Arial", 0, 14));
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				noEntryDialog.setVisible(false);
			}
		});
		
		noEntryPanel.add(okBtn);
		
		noEntryDialog.add(noEntryPanel);
		noEntryDialog.pack();
		noEntryDialog.setVisible(true);
	}
}
