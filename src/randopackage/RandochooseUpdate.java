package randopackage;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandochooseUpdate implements Runnable {
	String link;
	File out;
	boolean manual;
	String link2;
	
	public RandochooseUpdate(String link, String link2, File out, boolean manual) {
		this.link = link;
		this.link2 = link2;
		this.out = out;
		this.manual = manual;
	}

	@Override
	public void run() {
		try {
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(out);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			int read = 0;
			
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
			}
			
			bout.close();
			in.close();
			RandochooseUpdateStageTwo.checkStage(RandochooseMain.majorVer, RandochooseMain.minorVer1, RandochooseMain.minorVer2, RandochooseMain.verCode, this.manual);
		}
		
		catch (Exception e) {
			try {
				URL url = new URL(link2);
				HttpURLConnection http = (HttpURLConnection)url.openConnection();
				BufferedInputStream in = new BufferedInputStream(http.getInputStream());
				FileOutputStream fos = new FileOutputStream(out);
				BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
				byte[] buffer = new byte[1024];
				int read = 0;
				
				while((read = in.read(buffer, 0, 1024)) >= 0) {
					bout.write(buffer, 0, read);
				}
				
				bout.close();
				in.close();
				RandochooseUpdateStageTwo.checkStage(RandochooseMain.majorVer, RandochooseMain.minorVer1, RandochooseMain.minorVer2, RandochooseMain.verCode, this.manual);
			}
			
			catch (Exception ex) {
				noInternet();
			}
		}
		
	}
	
	public static void noInternet() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose Update Management");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel("Sorry, but Randochoose failed to connect to its GitHub repository!");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel("This might have one of the following reasons:");
		noticeLabel2.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel2);
		
		JLabel causeLbl1 = new JLabel("- You're most likely not connected to the internet. Please check your");
		causeLbl1.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl1);
		
		JLabel causeLbl2 = new JLabel("  internet connection and try again.");
		causeLbl2.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl2);
		
		JLabel causeLbl3 = new JLabel("- If it still fails, your administrator might have blocked access to GitHub and Codeberg.");
		causeLbl3.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl3);
		
		JLabel causeLbl4 = new JLabel("  Contact him or her for further information.");
		causeLbl4.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl4);
		
		JLabel causeLbl5 = new JLabel("- If these are all not the case, GitHub and Codeberg servers might be down at the moment.");
		causeLbl5.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl5);
		
		JLabel causeLbl6 = new JLabel("  In this case, try again when GitHub and/or Codeberg are online again.");
		causeLbl6.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(causeLbl6);
		
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
