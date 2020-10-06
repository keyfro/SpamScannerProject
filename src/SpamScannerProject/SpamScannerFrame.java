package SpamScannerProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SpamScannerFrame extends JFrame {
	
    private int spamCount, wordCount;
	private JTextArea messageArea;
    private JButton deleteButton, checkButton;
    private String words = "Number of words: %s";
    private String found = "Spam words found: %s";


    public SpamScannerFrame() {
        super("Spam Scanner");
        setLayout(new BorderLayout());
        
        messageArea = new JTextArea(20, 50);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.addActionListener((ActionEvent e) -> {
            
            int result = JOptionPane.showConfirmDialog(deleteButton,"Sure you want to Delete?", "Swing Tester",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
            	  messageArea.setText("");
            }
        });

        SpamScanner spamFilter = new SpamScanner();
        checkButton = new JButton("Check");
        checkButton.setBackground(Color.GREEN);
        checkButton.addActionListener((ActionEvent e) -> {
        	wordCount = SpamScanner.countWords(messageArea.getText());
            JOptionPane.showMessageDialog(getContentPane(), String.format(words, wordCount));
            messageArea.grabFocus();
            messageArea.selectAll();
            spamCount = spamFilter.countSpam(messageArea.getText());
            JOptionPane.showMessageDialog(getContentPane(), String.format(found, spamCount));
            messageArea.grabFocus();
            messageArea.selectAll();
            
            
            if (spamCount >= wordCount || spamCount >= 6) {
            	JOptionPane.showMessageDialog(this,"Spam Probability: Likely");  
            }
            
            else {
            	JOptionPane.showMessageDialog(this,"Spam Probability: Not Likely");              
            }
        });
     
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Enter email text:"));
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(headerPanel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JScrollPane textScrollPane = new JScrollPane(messageArea);
        textPanel.add(textScrollPane);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(checkButton);
  
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
       
    }

}