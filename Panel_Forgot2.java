import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel_Forgot2 extends JPanel implements ActionListener {

	JTextField answer_field;
	String correct_answer = "";
	String password = "";
	
	public Panel_Forgot2(String id, String in_correct_answer, String in_password)
	{	
		correct_answer = in_correct_answer;
		password = in_password;
		
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 200));

		
		// label for instruction message
		JLabel info1 = new JLabel("       What is your");
		info1.setFont(new Font("Monaco", Font.BOLD, 14));
		info1.setOpaque(true);
		info1.setBackground(Color.WHITE);
		info1.setLocation(65, 80);
		info1.setSize(230, 45);	
		
		JLabel info2 = new JLabel("    	 favorite color?");
		info2.setFont(new Font("Monaco", Font.BOLD, 14));
		info2.setOpaque(true);
		info2.setBackground(Color.WHITE);
		info2.setLocation(125, 115);
		info2.setSize(230, 45);
		
		add(info2);
		add(info1);
		
		
		// label and text field for answer
		answer_field = new JTextField();
		answer_field.setSize(160, 35);
		answer_field.setLocation(125, 188);
		add(answer_field);
		
		
		// create "delete" button
		JButton delete = new JButton("OK");
		delete.setSize(80, 35);
		delete.setLocation(165, 260);
		delete.setFont(new Font("Monaco", Font.BOLD, 11));
		delete.setForeground(Color.RED);
		delete.addActionListener((ActionListener) this);
		add(delete);
		
				
		// create "go back" button
		JButton go_back = new JButton("<-");
		go_back.setSize(50, 35);
		go_back.setLocation(15, 15);
		go_back.setFont(new Font("Monaco", Font.BOLD, 11));
		go_back.addActionListener((ActionListener) this);
		add(go_back);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand() == "<-") {
			Main.frame.forgot_page();
		}
		
		else if (e.getActionCommand() == "OK") {
			String answer = answer_field.getText();
			
			if (answer.equalsIgnoreCase(correct_answer)) {
				JOptionPane.showMessageDialog(null, "Your password is '" + password + "'.");
				Main.frame.default_page();
			}
			else {
				JOptionPane.showMessageDialog(null, "Your answer is incorrect!");
				answer_field.setText("");
			}
		}	
	}
}