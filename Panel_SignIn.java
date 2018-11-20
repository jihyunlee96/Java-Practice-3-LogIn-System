import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Panel_SignIn extends JPanel implements ActionListener, DocumentListener {
	
	JTextField id_field;
	JPasswordField pw_field;
	JTextField name_field;
	JTextField age_field;
	JTextField phone_field;
	JTextField address_field;
	JTextField answer_field;
	
	JLabel pw_label = new JLabel();
	JLabel id_label = new JLabel();
	
	boolean is_pw_safe = false;
	boolean is_id_available = false;
		
	public Panel_SignIn()
	{		
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 200));
		
		
		// label and text field for id
		My_Label id_label = new My_Label("ID:", 100, 80);
		add(id_label);
		
		id_field = new JTextField();
		id_field.setSize(160, 35);
		id_field.setLocation(155, 78);
		id_field.getDocument().addDocumentListener((DocumentListener) this);
		add(id_field);
		
		
		// label and text field for pw
		My_Label pw_label = new My_Label("PW:", 100, 140);
		add(pw_label);
		
		pw_field = new JPasswordField();
		pw_field.setSize(160, 35);
		pw_field.setLocation(155, 138);
		pw_field.getDocument().addDocumentListener((DocumentListener) this);
		add(pw_field);
		
		
		// label and text field for name
		My_Label name_label = new My_Label("Name:", 100, 200);
		add(name_label);
				
		name_field = new JTextField();
		name_field.setSize(160, 35);
		name_field.setLocation(155, 198);
		add(name_field);
		
		
		// label and text field for name
		My_Label age_label = new My_Label("Age:", 100, 260);
		add(age_label);
				
		age_field = new JTextField();
		age_field.setSize(160, 35);
		age_field.setLocation(155, 258);
		add(age_field);
		
		
		// label and text field for phoneNo
		My_Label phone_label = new My_Label("Phone:", 100, 320);
		phone_label.setFont(new Font("Monaco", Font.BOLD, 11));
		add(phone_label);
				
		phone_field = new JTextField();
		phone_field.setSize(160, 35);
		phone_field.setLocation(155, 318);
		add(phone_field);	
		
		
		// label and text field for address
		My_Label address_label = new My_Label("Address:", 100, 380);
		address_label.setFont(new Font("Monaco", Font.BOLD, 10));
		add(address_label);
				
		address_field = new JTextField();
		address_field.setSize(160, 35);
		address_field.setLocation(155, 378);
		add(address_field);	
		
		
		// label and text field for answer
		JLabel answer_label = new My_Label("Favorite Color:", 50, 440);
		answer_label.setFont(new Font("Monaco", Font.BOLD, 10));
		answer_label.setSize(100, 30);
		add(answer_label);
						
		answer_field = new JTextField();
		answer_field.setSize(160, 35);
		answer_field.setLocation(155, 438);
		add(answer_field);	
		
		
		// create "sign_in" button
		JButton sign_in = new JButton("Sign In");
		sign_in.setSize(80, 35);
		sign_in.setLocation(250, 500);
		sign_in.setFont(new Font("Monaco", Font.BOLD, 11));
		sign_in.setForeground(Color.RED);
		sign_in.addActionListener(this);
		add(sign_in);
		
		
		// create "go back" button
		JButton go_back = new JButton("<-");
		go_back.setSize(50, 35);
		go_back.setLocation(15, 15);
		go_back.setFont(new Font("Monaco", Font.BOLD, 11));
		go_back.addActionListener((ActionListener) this);
		add(go_back);
		
		
		setVisible(true);
	}
	
	class My_Label extends JLabel {
		
		public My_Label (String name, int x, int y)
		{
			super(name);
			setFont(new Font("Monaco", Font.BOLD, 13));
			setOpaque(true);
			setBackground(Color.WHITE);
			setLocation(x, y);
			setSize(50, 30);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand() == "<-") {
			Main.frame.default_page();
		}
		
		else if (e.getActionCommand() == "Sign In") {
			String id = "";
			String pw = "";
			String name = "";
			int age = 0;
			String phoneNo = "";
			String address = "";
			String answer = "";
			
			id = id_field.getText();
			pw = pw_field.getText();
			name = name_field.getText();
			age = Integer.parseInt(age_field.getText());
			phoneNo = phone_field.getText();
			address = address_field.getText();
			answer = answer_field.getText();
		
			sign_in(id, pw, name, age, phoneNo, address, answer);
		}
	}
	
	public void insertUpdate (DocumentEvent ev)
	{
		if (ev.getDocument() == pw_field.getDocument()) {
			String pw = pw_field.getText();
			print_safety(check_password(pw));
		}
		
		else if (ev.getDocument() == id_field.getDocument()) {
			String id = id_field.getText();
			print_id(check_id(id));
		}
	}
	
	public void removeUpdate (DocumentEvent ev)
	{
		if (ev.getDocument() == pw_field.getDocument()) {
			String pw = pw_field.getText();
			print_safety(check_password(pw));
		}
		
		else if (ev.getDocument() == id_field.getDocument()) {
			String id = id_field.getText();
			print_id(check_id(id));
		}
	}
	
	public void changedUpdate (DocumentEvent ev)
	{
		if (ev.getDocument() == pw_field.getDocument()) {
			String pw = pw_field.getText();
			print_safety(check_password(pw));
		}
		
		else if (ev.getDocument() == id_field.getDocument()) {
			String id = id_field.getText();
			print_id(check_id(id));
		}
	}
	
	public void sign_in (String id, String pw, String name, int age, String phoneNo, String address, String answer)
	{
		Connection conn = null;
	    Statement stmt = null;
	    
	    int r = 0;
	    
	    if (is_id_available == false) {
	    		JOptionPane.showMessageDialog(null, "ID not available.");
	    		return;
	    }
	    
	    if (is_pw_safe == false) {
	    		JOptionPane.showMessageDialog(null, "Password not safe.");
    			return;
	    }
	    
	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    		
	    		stmt = (Statement) conn.createStatement();
    			
    			r = stmt.executeUpdate("INSERT INTO user_info(ID,PW,Name,Age,PhoneNum,Address,Answer) VALUES('"
    					+ id + "', '" + pw + "', '" + name + "', '" + age + "', '" + phoneNo + "', '" + address 
    					+ "', '" + answer + "')");
    			System.out.println(r);
    			
    			if (r == 1) {
    				JOptionPane.showMessageDialog(null, "Sign-in Successful!");
    				Main.frame.default_page();
    				
        			return;
    			}
    			
    			stmt.close();
	    		conn.close();
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
	
	public String check_password (String pw)
	{
		String result = "Safe password!";
		
		if (pw.length() == 0) {
			result = "";	
			is_pw_safe = false;
			
			return result;
		}
		
		// check if same letter is repeated more than 3 times
		int count = 0;
		char temp = pw.charAt(0);
		int i = 0;
		
		for (i = 1; i < pw.length(); i ++)
		{
			if (pw.charAt(i) == temp) {
				count ++;
				
				if (count == 2) break;
			}
			else {
				temp = pw.charAt(i);
				count = 0;
			}	
		}
		
		if (i != pw.length()) {
			result = "Weak! same letter repeated 3 times.";
			is_pw_safe = false;
			
			return result;
		}
		
		// check if there is more than 3 consecutive letters (such as 123)
		count = 0;
		temp = pw.charAt(0);
		i = 0;
		
		for (i = 1; i < pw.length(); i ++)
		{
			if (pw.charAt(i) == temp + 1) {				
				count ++;
				temp = pw.charAt(i);
				
				if (count == 2) break;
			}
			else {
				temp = pw.charAt(i);
				count = 0;
			}	
		}
		
		if (i != pw.length()) {
			result = "Weak! 3 consecutive letters.";
			is_pw_safe = false;
			
			return result;
		}
		
		
		if (pw.length() < 5) {
			result = "Weak! less than 5 letters.";
			is_pw_safe = false;
			
			return result;
		}
		
		is_pw_safe = true;
		
		return result;
	}
	
	public String check_id (String id)
	{
		if (id.length() == 0) {
			is_id_available = false;
			return "";
		}
		
		String result = id + " is available.";
		
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    	    
	    int r = 0;

	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    			    		
	    		stmt = (Statement) conn.createStatement();
	    		
	    		rs = stmt.executeQuery("select * from user_info where ID='" + id + "';");
	    	
	    		// id 를 찾았을 경우
	    		if (rs.next()) {
	    			result = id + " is already taken.";
	    			is_id_available = false;
	    			
	    			return result;
	    		}
	    		
	    		conn.close();
	    		stmt.close();
	    		rs.close();
	    		
	    		is_id_available = true;
	    		
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
	
	public void print_safety (String str)
	{
		pw_label.setText(str);
		
		pw_label.setFont(new Font("Monaco", Font.BOLD, 10));
		pw_label.setOpaque(true);
		pw_label.setBackground(new Color(233, 233, 200));
		pw_label.setLocation(158, 175);
		pw_label.setSize(220, 20);
		
		if (str.compareTo("Safe password!") == 0)
			pw_label.setForeground(Color.BLUE);
		else
			pw_label.setForeground(Color.RED);
		
		pw_label.setVisible(true);
		
		add(pw_label);
		
		revalidate();
		repaint();
	}
	
	public void print_id (String str)
	{
		id_label.setText(str);
		id_label.setFont(new Font("Monaco", Font.BOLD, 10));
		id_label.setOpaque(true);
		id_label.setBackground(new Color(233, 233, 200));
		id_label.setLocation(158, 115);
		id_label.setSize(220, 20);
		
		if (str.contains("available"))
			id_label.setForeground(Color.BLUE);
		else
			id_label.setForeground(Color.RED);
		
		id_label.setVisible(true);
		
		add(id_label);
		
		revalidate();
		repaint();
	}
}
