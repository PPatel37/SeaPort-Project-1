package seaport;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * File: SeaPortProgram.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This is the main class that launch the GUI for SeaPort program 
 */
public class SeaPortProgram extends JFrame{
	
	static final long serialVersionUID = 1L;

	World world = new World();
	JTextArea jta = new JTextArea();
	JComboBox <String> jcb;
	JTextField jtf;
	String token;
	Scanner scanLine;
	String line;
	/**
	 *No-arg constructor
	 */
	public SeaPortProgram(){
	        setTitle ("SeaPort Project");
	        setSize (600, 300);
	        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        setVisible (true);
	        jta.setFont (new java.awt.Font ("Monospaced", 0, 12)); 
	        JScrollPane jsp = new JScrollPane (jta);
	        add (jsp, BorderLayout.CENTER);
	        JLabel jlbl = new JLabel ("Search Target");
	        jtf = new JTextField (10);
	        //combo box
	        jcb = new JComboBox <String> ();
	        jcb.addItem ("Name");
	        jcb.addItem ("Index");
	        jcb.addItem ("Skills");
	        //Buttons
	        JButton jbr = new JButton ("Read");
	        JButton jbs = new JButton ("Search");
	        JButton jbd = new JButton ("Display");
	        
	        JPanel jp = new JPanel ();
	        jp.add (jbr);
	        jp.add (jbd);
	        jp.add (jlbl);
	        jp.add (jtf);
	        jp.add (jcb);
	        jp.add (jbs);
	        jp.add(jbd);
	        add (jp, BorderLayout.PAGE_START);
	        validate();
	        
	    jbr.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    readFile ();
                } // end required method
            } // end local definition of inner class
        ); // the anonymous inner class
        
        jbd.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    display ();
                } // end required method
            } // end local definition of inner class
        ); // the anonymous inner class
        
        jbs.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    search ((String)(jcb.getSelectedItem()), jtf.getText());
                } // end required method
            } // end local definition of inner class
        ); // the anonymous inner class
	        
	}//end constructor
	
	/**
	 * method that handles Read button
	 */
	public void readFile () {
		JFileChooser jfc = new JFileChooser ("."); 
		int returnVal = jfc.showOpenDialog(null);
		
	       if(returnVal == JFileChooser.APPROVE_OPTION) {
	         // System.out.println("You chose to open this file: " +
	         //  jfc.getSelectedFile().getName());
	         // open and read file:
	          try {
	             Scanner sfin = new Scanner (jfc.getSelectedFile()); // NOT the file name!!!!
	             while (sfin.hasNext()) {
	            	 
	            	  line = sfin.nextLine();
	            	 if(line.startsWith("//") || line.isEmpty()){
	            		 continue;
	            	 }
	            	 world.process(line);	
	            	 
	             }
	             sfin.close();
	            
	          } 
	             catch (FileNotFoundException e) {
	                JOptionPane.showMessageDialog(null, "File not found.");
	             }
	          
	       } 
    } 
    /**
     * this method handles display button
     */
    public void display () {
       
        jta.setText ("" + world.ports.toString());
    } 
    /**
     * This method handles Search button
     * @param type is a type of search user want to perform
     * @param target is entered string in textfield
     */
    public void search (String type, String target) {
    	world.doSearch(type,target);
    	if (world.searchList.isEmpty()){
    	jta.setText( type + ": " + target + " Does not exist");
    	}else{
    	jta.setText("Search Result for: " + type + ": " + target + "\n\n"+ world.searchList.toString());  
    	}
    } 
    
	/**
	 * main method that launch the SeaPort program
	 * @param args
	 */
	public static void main (String args []) {
		    SeaPortProgram screen = new SeaPortProgram ();
		   
		  } 
	 
}
