package randopackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.*;

public class RandochooseMain {

	public static int majorVer = 0;
	public static int minorVer1 = 2;
	public static int minorVer2 = 1;
	
	public static void main(String[] args) {
		String link = "https://raw.githubusercontent.com/Tech-FZ/randochoose/main/vercheck.rdc";
		File out = new File("vercheck.rdc");
		new Thread(new RandochooseUpdate(link, out, false)).start();
		
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Randochoose");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 300);
		mainFrame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		
		JLabel alphaLabel = new JLabel("This program is an alpha! Major issues expected.");
		alphaLabel.setFont(new Font("Arial", 0, 14));
		mainPanel.add(alphaLabel);
		
		JLabel noticeLabel = new JLabel("One line is one candidate!");
		noticeLabel.setFont(new Font("Arial", 0, 20));
		mainPanel.add(noticeLabel);
		
		JPanel textPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		JTextArea randomizerText = new JTextArea();
		randomizerText.setFont(new Font("Arial", 0, 14));
		randomizerText.setRows(2);
		textPanel.add(randomizerText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(randomizerText);
		textPanel.add(scrollPane);
		
		mainPanel.add(textPanel);
		
		JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		
		JButton chooseCandidateBtn = new JButton("Choose!");
		chooseCandidateBtn.setFont(new Font("Arial", 0, 14));
		
		chooseCandidateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scanTxtField(randomizerText.getText());
			}
		});
		
		btnPanel.add(chooseCandidateBtn);
		
		JButton updateBtn = new JButton("Check for updates");
		updateBtn.setFont(new Font("Arial", 0, 14));
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String link = "https://raw.githubusercontent.com/Tech-FZ/randochoose/main/vercheck.rdc";
				File out = new File("vercheck.rdc");
				new Thread(new RandochooseUpdate(link, out, true)).start();
			}
		});
		
		btnPanel.add(updateBtn);
		
		JButton aboutBtn = new JButton("About");
		aboutBtn.setFont(new Font("Arial", 0, 14));
		
		aboutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aboutThisSoftware();
			}
		});
		
		btnPanel.add(aboutBtn);
		
		mainPanel.add(btnPanel);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public static void scanTxtField(String candidates) {
		String[] candidateArr = candidates.split("\\\n");
		
		try {
			randomizedChoice(candidateArr);
		}
		
		catch (Exception e) {
			noEntries();
		}
	}
	
	public static void noEntries() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel("Sorry, but you didn't type in anything!");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel("Please type in at least one candidate to continue.");
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
	
	public static void randomizedChoice(String[] candidates) {
		int i = candidates.length;
		
		int randomizedInt = ThreadLocalRandom.current().nextInt(0, i);
		String chosenCandidate = candidates[randomizedInt];
		
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel(chosenCandidate + " has been chosen.");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
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
	
	public static void aboutThisSoftware() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel("Randochoose");
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel("Version " + majorVer + "." + minorVer1 + "." + minorVer2);
		noticeLabel2.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(noticeLabel2);
		
		JLabel licenseHeader = new JLabel("MIT License");
		licenseHeader.setFont(new Font("Arial", 0, 14));
		noEntryPanel.add(licenseHeader);
		
		JLabel copyrightHeader = new JLabel("Copyright (c) 2022 Nicolas Lucien and Randochoose contributors");
		copyrightHeader.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(copyrightHeader);
		
		JLabel conditions1 = new JLabel("Permission is hereby granted, free of charge, to any person obtaining a copy");
		conditions1.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions1);
		
		JLabel conditions2 = new JLabel("of this software and associated documentation files (the \"Software\"), to deal");
		conditions2.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions2);
		
		JLabel conditions3 = new JLabel("in the Software without restriction, including without limitation the rights");
		conditions3.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions3);
		
		JLabel conditions4 = new JLabel("to use, copy, modify, merge, publish, distribute, sublicense, and/or sell");
		conditions4.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions4);
		
		JLabel conditions5 = new JLabel("copies of the Software, and to permit persons to whom the Software is");
		conditions5.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions5);
		
		JLabel conditions6 = new JLabel("furnished to do so, subject to the following conditions:");
		conditions6.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions6);
		
		JLabel conditions7 = new JLabel("");
		conditions7.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions7);
		
		JLabel conditions8 = new JLabel("The above copyright notice and this permission notice shall be included in all");
		conditions8.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions8);
		
		JLabel conditions9 = new JLabel("copies or substantial portions of the Software.");
		conditions9.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions9);
		
		JLabel conditions10 = new JLabel("");
		conditions10.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions10);
		
		JLabel conditions11 = new JLabel("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR");
		conditions11.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions11);
		
		JLabel conditions12 = new JLabel("IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,");
		conditions12.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions12);
		
		JLabel conditions13 = new JLabel("FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE");
		conditions13.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions13);
		
		JLabel conditions14 = new JLabel("AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER");
		conditions14.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions14);
		
		JLabel conditions15 = new JLabel("LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,");
		conditions15.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions15);
		
		JLabel conditions16 = new JLabel("OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE");
		conditions16.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions16);
		
		JLabel conditions17 = new JLabel("SOFTWARE.");
		conditions17.setFont(new Font("Arial", 0, 12));
		noEntryPanel.add(conditions17);
		
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
