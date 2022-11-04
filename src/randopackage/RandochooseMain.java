package randopackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.*;
import java.util.*;
import java.util.List;

public class RandochooseMain {

	public static int majorVer = 2022;
	public static int minorVer1 = 11;
	public static int minorVer2 = 0;
	public static int verCode = 10;
	static boolean groupModeSelected = false;
	static int maxCandidatesInGroup = 2;
	
	public static void main(String[] args) {		
		String link = "https://raw.githubusercontent.com/Tech-FZ/randochoose/main/vercheck.rdc";
		String link2 = "https://codeberg.org/lucien-rowan/randochoose/raw/branch/main/vercheck.rdc";
		File out = new File("vercheck.rdc");
		new Thread(new RandochooseUpdate(link, link2, out, false)).start();
		
		String[] settings = RandochooseSettings.createSettingsFile();
		
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Randochoose");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		/*JLabel alphaLabel = new JLabel("This program is an alpha! Major issues expected.");
		alphaLabel.setFont(new Font("Arial", 0, 14));
		mainPanel.add(alphaLabel);*/
		
		ResourceBundle langRb =  ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		
		if (settings[0].contains("en")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		}
		
		else if (settings[0].contains("de")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_de");
		}
		
		
		JLabel noticeLabel = new JLabel(langRb.getString("candidateInstruction"));
		noticeLabel.setFont(new Font("Arial", 0, 20));
		mainPanel.add(noticeLabel);
		
		JRadioButton normalMode = new JRadioButton();
		JRadioButton groupMode = new JRadioButton();
		ButtonGroup randomizerGroup = new ButtonGroup();
		
		JSpinner groupModeSpinner = new JSpinner();
		groupModeSpinner.setEnabled(false);
		
		normalMode.setText(langRb.getString("normalRandomizationTxt"));
		groupMode.setText(langRb.getString("groupRandomizationTxt"));
		
		normalMode.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == normalMode) {
					if (e.getStateChange() == 1) {
						groupModeSpinner.setEnabled(false);
						groupModeSelected = false;
					}
				}
				else if (e.getSource() == groupMode) {
					if (e.getStateChange() == 1) {
						groupModeSpinner.setEnabled(true);
						groupModeSelected = true;
					}
				}
			}
		});
		
		groupMode.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == normalMode) {
					if (e.getStateChange() == 1) {
						groupModeSpinner.setEnabled(false);
						groupModeSelected = false;
					}
				}
				else if (e.getSource() == groupMode) {
					if (e.getStateChange() == 1) {
						groupModeSpinner.setEnabled(true);
						groupModeSelected = true;
					}
				}
			}
		});
		
		mainPanel.add(normalMode);
		mainPanel.add(groupMode);
		
		randomizerGroup.add(normalMode);
		randomizerGroup.add(groupMode);
		
		normalMode.setSelected(true);
		
		JPanel groupModeSettingsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		JLabel groupModeLbl = new JLabel(langRb.getString("groupRandomizationCountTxt"));
		groupModeLbl.setFont(new Font("Arial", 0, 12));
		groupModeSettingsPanel.add(groupModeLbl);
		
		groupModeSettingsPanel.add(groupModeSpinner);
		
		mainPanel.add(groupModeSettingsPanel);
		
		JPanel textPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		JTextArea randomizerText = new JTextArea();
		randomizerText.setFont(new Font("Arial", 0, 14));
		randomizerText.setRows(2);
		textPanel.add(randomizerText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(randomizerText);
		textPanel.add(scrollPane);
		
		mainPanel.add(textPanel);
		
		JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		JButton chooseCandidateBtn = new JButton(langRb.getString("chooseBtnTxt"));
		chooseCandidateBtn.setFont(new Font("Arial", 0, 14));
		
		chooseCandidateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maxCandidatesInGroup = (int) groupModeSpinner.getValue();
				scanTxtField(randomizerText.getText());
			}
		});
		
		btnPanel.add(chooseCandidateBtn);
		
		JButton updateBtn = new JButton(langRb.getString("checkUpdBtnTxt"));
		updateBtn.setFont(new Font("Arial", 0, 14));
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String link = "https://raw.githubusercontent.com/Tech-FZ/randochoose/main/vercheck.rdc";
				String link2 = "https://codeberg.org/lucien-rowan/randochoose/raw/branch/main/vercheck.rdc";
				File out = new File("vercheck.rdc");
				new Thread(new RandochooseUpdate(link, link2, out, true)).start();
			}
		});
		
		btnPanel.add(updateBtn);
		
		JButton aboutBtn = new JButton(langRb.getString("aboutBtnTxt"));
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
			e.printStackTrace();
			noEntries();
		}
	}
	
	public static void noEntries() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose");
		
		String[] settings = RandochooseSettings.readSettingsFile();
		
		ResourceBundle langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		
		if (settings[0].contains("en")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		}
		
		else if (settings[0].contains("de")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_de");
		}
		
		JPanel noEntryPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		
		JLabel noticeLabel = new JLabel(langRb.getString("sryTxtP1"));
		noticeLabel.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel);
		
		JLabel noticeLabel2 = new JLabel(langRb.getString("sryTxtP2"));
		noticeLabel2.setFont(new Font("Arial", 0, 16));
		noEntryPanel.add(noticeLabel2);
		
		JButton okBtn = new JButton(langRb.getString("okBtnTxt"));
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
		for (int h = 0; h < candidates.length; h++) {
			// Creating a object for Random class
			Random r = new Random();
          
        	// Start from the last element and swap one by one. We don't
        	// need to run for the first element that's why i > 0
        	for (int i = candidates.length - 1; i > 0; i--) {
            	// Pick a random index from 0 to i
            	int j = r.nextInt(i);
              
            	// Swap arr[i] with the element at random index
            	String temp = candidates[i];
            	candidates[i] = candidates[j];
            	candidates[j] = temp;
        	}
		}
 
        // Prints the random array
        System.out.println(Arrays.toString(candidates));
        
        String[] settings = RandochooseSettings.readSettingsFile();
        
        ResourceBundle langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
        
        if (settings[0].contains("en")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		}
		
		else if (settings[0].contains("de")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_de");
		}
		
        if (groupModeSelected == false) {
        	int randomizedInt = ThreadLocalRandom.current().nextInt(0, candidates.length);
    		String chosenCandidate = candidates[randomizedInt];
    		
    		JDialog noEntryDialog = new JDialog();
    		noEntryDialog.setTitle("Randochoose");
    		
    		JPanel noEntryPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    		
    		JLabel noticeLabel = new JLabel(chosenCandidate + " " + langRb.getString("chosenCandidateTxt"));
    		noticeLabel.setFont(new Font("Arial", 0, 16));
    		noEntryPanel.add(noticeLabel);
    		
    		JButton okBtn = new JButton(langRb.getString("okBtnTxt"));
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
        
        else {
        	JDialog noEntryDialog = new JDialog();
    		noEntryDialog.setTitle("Randochoose");
    		
    		JPanel noEntryPanel = new JPanel(new GridLayout(0, 1, 10, 10));
    		
    		JLabel noticeLabel = new JLabel(langRb.getString("groupsChosenTxt"));
    		noticeLabel.setFont(new Font("Arial", 0, 16));
    		noEntryPanel.add(noticeLabel);
    		
    		JPanel tablePanel = new JPanel(new GridLayout(0, 2, 10, 10));
    		
    		Object[] columns = new Object[maxCandidatesInGroup];
    		Object[][] groups = new Object[(int) Math.ceil(candidates.length / maxCandidatesInGroup) + 1][maxCandidatesInGroup];
    		System.out.println((int) Math.ceil(candidates.length / maxCandidatesInGroup));
    		
    		for (int i = 1; i <= maxCandidatesInGroup; i++) {
    			columns[i - 1] = String.valueOf(groupModeSelected);
    		}
    		
    		int k = 0;
    		
    		for (int i = 0; i < candidates.length; i+=maxCandidatesInGroup) {
    			Object[] tempObj = new Object[maxCandidatesInGroup];
    			for (int j = 0; j < maxCandidatesInGroup; j++) {
    				try {
    				tempObj[j] = candidates[i+j];
    				}
    				catch (Exception e) {
    					try {
    						tempObj[j] = candidates[i+j];
    					}
    					catch (Exception e1) {
    						try {
    							tempObj[j] = candidates[i+j];
    						}
    						catch (Exception e2) {
    							if (candidates.length % maxCandidatesInGroup != 0) {
    								tempObj[j] = "";
    							}
    						}
    					}
    				}
    			}
    			groups[k] = tempObj;
    			k++;
    		}
    		
    		int alreadyAssigned = 0;
    		Object[] alreadyAssignedCandidates = new Object[candidates.length];
    		
    		for (Object[] group : groups) {
				for (Object object : group) {
					System.out.println(object);
					
					/*if (object == null) {
						for (int i = 0; i < groups.length; i++) {
							if (groups[i] == group) {
								for (int j = 0; j < group.length; j++) {
									if (groups[i][j] == object) {
										for (int k1 = 0; k1 < candidates.length; k1++) {
											for (int l = 0; l < alreadyAssignedCandidates.length; l++) {
												if (candidates[k1] != alreadyAssignedCandidates[l]) {
													groups[i][j] = candidates[k1];
													break;
												}
											}
										}
									}
								}
							}
						}
					}
					
					if (object != null) {
						alreadyAssignedCandidates[alreadyAssigned] = object;
						alreadyAssigned++;
					}*/
				}
			}
    		
    		List<String> candidateAsList = new ArrayList<>(Arrays.asList(candidates));
    		int posInCandidateObj = 0;
    		int posInCandidateObj2 = 0;
    		
    		for (int i = 0; i < groups.length; i++) {
    			for (int j = 0; j < groups[i].length; j++) {
    				if (groups[i][j] == null) {
    					for (int l = 0; l < candidates.length; l++) {
    						for (Object[] group : groups) {
    							for (Object object : group) {
    								if (object != candidates[l] && candidateAsList.contains(object) == false) {
    									boolean candidateAlreadyPresent = false;
    									for (int m = 0; m < groups.length; m++) {
    										for (int n = 0; n < groups[m].length; n++) {
    											if (groups[m][n] == candidates[l]) {
    												candidateAlreadyPresent = true;
    											}
    											posInCandidateObj = n;
    										}
    										posInCandidateObj2 = m;
    										
    										if (candidateAlreadyPresent == false) {
    											groups[i][j] = candidates[l];
    										}
    									}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    		
    		
    		JTable table = new JTable(groups, columns);
    		
    		JScrollPane scrollPane = new JScrollPane(table);
    		table.setFillsViewportHeight(true);
    		
    		tablePanel.add(table);
    		tablePanel.add(scrollPane);
    		
    		noEntryPanel.add(tablePanel);
    		
    		JButton okBtn = new JButton(langRb.getString("okBtnTxt"));
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
	
	public static void aboutThisSoftware() {
		JDialog noEntryDialog = new JDialog();
		noEntryDialog.setTitle("Randochoose");
		
		JPanel noEntryPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		
		String[] settings = RandochooseSettings.readSettingsFile();
		
		ResourceBundle langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		
		if (settings[0].contains("en")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_en");
		}
		
		else if (settings[0].contains("de")) {
			System.out.println(settings[0]);
			langRb = ResourceBundle.getBundle("rdc_languages/resource_bundle_de");
		}
		
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
		
		JButton okBtn = new JButton(langRb.getString("okBtnTxt"));
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
