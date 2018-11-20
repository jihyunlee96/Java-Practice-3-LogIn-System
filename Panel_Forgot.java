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

public class Panel_Forgot extends JPanel implements ActionListener {

	JTextField id_field;
	String correct_answer = "";
	String password = "";
	
	public Panel_Forgot()
	{	
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 200));

		
		// label for instruction message
		JLabel info1 = new JLabel("     What is your");
		info1.setFont(new Font("Monaco", Font.BOLD, 14));
		info1.setOpaque(true);
		info1.setBackground(Color.WHITE);
		info1.setLocation(85, 80);
		info1.setSize(170, 45);	
		
		JLabel info2 = new JLabel("     ID?");
		info2.setFont(new Font("Monaco", Font.BOLD, 14));
		info2.setOpaque(true);
		info2.setBackground(Color.WHITE);
		info2.setLocation(195, 115);
		info2.setSize(120, 45);
		
		add(info2);
		add(info1);
		
		
		// label and text field for id
		id_field = new JTextField();
		id_field.setSize(160, 35);
		id_field.setLocation(125, 188);
		add(id_field);
		
		
		// create "OK" button
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
			Main.frame.default_page();
		}
		
		else if (e.getActionCommand() == "OK") {
			String id = id_field.getText();
			
			if (search(id))
				Main.frame.forgot_page2(id, correct_answer, password);
			
			else
				JOptionPane.showMessageDialog(null, "ID '"+ id + "' not found.");
		}	
	}
	
	public boolean search (String id)
	{		
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    boolean result = false;
	    
	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    			    		
	    		stmt = (Statement) conn.createStatement();
	    		
	    		rs = stmt.executeQuery("select * from user_info where ID='" + id + "';");
	    		
	    		// id 를 찾지 못했을 경우
	    		if (!rs.next()) {
		    		id_field.setText("");
					
	    			conn.close();
	    			stmt.close();
	    			rs.close();	 
	    			    			
				return result;
	    		}

		    	// id 에 해당되는 answer 가져오기
		    	correct_answer = rs.getString("Answer");
		    			    	
		    	// id 에 해당되는 password 가져오기
		   	password = rs.getString("PW");
	    			
	    		result = true;
	    			
			return result;
	    }
	    
	    catch (ClassNotFoundException e) {
	    		System.out.print("Class not found");
	    		System.exit(0);
	    }

	    catch (Exception e) {
	    		System.out.print("Exception");
	    		System.exit(0);
	    }
	    
	    return result;
	}
}