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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel_Default extends JPanel implements ActionListener {
	
	JTextField id_field;
	JPasswordField pw_field;

	public Panel_Default()
	{
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 200));
		
		// label for log-in message
		JLabel info = new JLabel("Log-In!");
		info.setFont(new Font("Monaco", Font.BOLD, 15));
		info.setOpaque(true);
		info.setBackground(Color.WHITE);
		info.setLocation(150, 95);
		info.setSize(130, 45);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		add(info);
		
		
		// label and text field for id
		My_Label id_label = new My_Label("ID:", 100, 190);
		add(id_label);
				
		id_field = new JTextField();
		id_field.setSize(160, 35);
		id_field.setLocation(155, 188);
		add(id_field);
				
				
		// label and text field for pw
		My_Label pw_label = new My_Label("PW:", 100, 260);
		add(pw_label);
				
		pw_field = new JPasswordField();
		pw_field.setSize(160, 35);
		pw_field.setLocation(155, 258);
		add(pw_field);
		
		
		// create "sign_in" button
		JButton sign_in = new JButton("Sign In");
		sign_in.setSize(67, 35);
		sign_in.setLocation(95, 350);
		sign_in.setFont(new Font("Monaco", Font.BOLD, 11));
		sign_in.addActionListener(this);
		add(sign_in);
		
		
		// create "log_in" button
		JButton log_in = new JButton("Log In");
		log_in.setSize(67, 35);
		log_in.setLocation(245, 350);
		log_in.setFont(new Font("Monaco", Font.BOLD, 11));
		log_in.setForeground(Color.RED);
		log_in.addActionListener(this);
		add(log_in);
		
		// create "Forgot Password?" button
		JButton forgot = new JButton("Forgot Password?");
		forgot.setSize(107, 30);
		forgot.setLocation(205, 390);
		forgot.setFont(new Font("Monaco", Font.BOLD, 9));
		forgot.setForeground(Color.RED);
		forgot.addActionListener(this);
		add(forgot);
	}
	
	class My_Label extends JLabel {
		
		public My_Label (String name, int x, int y)
		{
			super(name);
			setFont(new Font("Monaco", Font.BOLD, 13));
			setOpaque(true);
			setBackground(Color.WHITE);
			setLocation(x, y);
			setSize(40, 30);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	
	public void actionPerformed (ActionEvent e) 
	{
		if (e.getActionCommand() == "Log In") {
			String id = "";
			String pw = "";
			
			id = id_field.getText();
			pw = pw_field.getText();
		
			log_in(id, pw);
		}
		
		else if (e.getActionCommand() == "Sign In")
			Main.frame.signIn_page ();
		
		else if (e.getActionCommand() == "Forgot Password?")
			Main.frame.forgot_page();
	}
	
	public void log_in (String id, String pw)
	{
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    String correct_pw = "";
	    
	    int r = 0;
	    
	    if (id.compareTo("") == 0 || pw.compareTo("") == 0) {
	    		JOptionPane.showMessageDialog(null, "Type ID and Password!");
	    		return;
	    }
	    	    
	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    			    		
	    		stmt = (Statement) conn.createStatement();
	    		
	    		rs = stmt.executeQuery("select * from user_info where ID='" + id + "';");
	    	
	    		// id 를 찾지 못했을 경우
	    		if (!rs.next()) {
	    			JOptionPane.showMessageDialog(null, "Wrong ID!");
	    			return;
	    		}
	    		
	    		// id 에 해당되는 pw 가져오기
	    		correct_pw = rs.getString("PW");
	    			    		
	    		// id 와 pw 가 일치하지 않을 경우
	    		if (correct_pw.compareTo(pw) != 0) {
	    			JOptionPane.showMessageDialog(null, "Wrong Password!");
	    			return;
	    		}
	    		
	    		// id 와 pw 가 일치하는 경우
	    		else {
	    			JOptionPane.showMessageDialog(null, "Log-in Successful!", "Logged In", JOptionPane.NO_OPTION);
	    			
	    			// 슈퍼유저인 경우
	    			if (id.compareTo("SuperUser") == 0)
	    				Main.frame.superUser_page();
	    			
	    			// 일반유저인 경우
	    			else 
	    				Main.frame.user_page(id);
	    		}	    	
	    		
	    		conn.close();
	    		stmt.close();
	    		rs.close();
	    }
	    
	    catch (ClassNotFoundException e) {
	    		System.out.print("Class not found");
	    		System.exit(0);
	    }

	    catch (Exception e) {
	    		System.out.print("Exception");
	    		System.exit(0);
	    }
	}
}
