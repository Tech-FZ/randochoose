package randopackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class RandochooseMain {

	public static int majorVer = 0;
	public static int minorVer1 = 0;
	public static int minorVer2 = 1;
	
	public static void main(String[] args) {		
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Randochoose");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton chooseCandidateBtn = new JButton("Choose!");
		chooseCandidateBtn.setFont(new Font("Arial", 0, 14));
		
		chooseCandidateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scanTxtField(randomizerText.getText());
			}
		});
		
		mainPanel.add(chooseCandidateBtn);
		
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
		
		int randomizedInt = ThreadLocalRandom.current().nextInt(0, i - 1);
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

}
