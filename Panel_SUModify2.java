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

public class Panel_SUModify2 extends JPanel implements ActionListener {
	
	JLabel id_field;
	JTextField pw_field;
	JTextField name_field;
	JTextField age_field;
	JTextField phone_field;
	JTextField address_field;
	
	String old_pw;
	String old_name;   
	int old_age;
	String old_ph;
	String old_add;
	
	boolean superUser = false;
	String id = "";
	
	public Panel_SUModify2 (boolean in_superUser, String in_id)
	{		
		superUser = in_superUser;
		id = in_id;
		
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 200));
				
		// label and text field for id
		My_Label id_label = new My_Label("ID:", 100, 80);
		add(id_label);
		
		id_field = new JLabel(id);
		id_field.setSize(148, 30);
		id_field.setLocation(162, 80);
		id_field.setOpaque(true);
		id_field.setBackground(Color.WHITE);
		add(id_field);
		
		
		// label and text field for pw
		My_Label pw_label = new My_Label("PW:", 100, 140);
		add(pw_label);
		
		pw_field = new JTextField();
		pw_field.setSize(160, 35);
		pw_field.setLocation(155, 138);
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
		
		
		// create "sign_in" button
		JButton sign_in = new JButton("Modify");
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
		
		setTextfields(id);
		
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
			if (superUser == true)
				Main.frame.modifySU_page();
			else
				Main.frame.user_page(id);
		}
		
		else if (e.getActionCommand() == "Modify") {
    			String new_id = id_field.getText();
    			String new_pw = pw_field.getText();
    			String new_name = name_field.getText();
    			int new_age = Integer.parseInt(age_field.getText());
    			String new_ph = phone_field.getText();
    			String new_add = address_field.getText();
    			
    			modify (new_id, new_pw, new_name, new_age, new_ph, new_add);
		}
	}
	
	private void setTextfields(String id)
	{
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    	    	    	    
	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    			    		
	    		stmt = (Statement) conn.createStatement();
	    		
	    		rs = stmt.executeQuery("select * from user_info where ID='" + id + "';");
	    		
	    		if (!rs.next())
	    			return;
	    		
	    		// id 에 해당되는 값들 가져오기
	    		old_pw = rs.getString("PW");
	    		old_name = rs.getString("Name");	   
	    		old_age = rs.getInt("Age");
	    		old_ph = rs.getString("PhoneNum");
	    		old_add = rs.getString("Address");
	    		
	    		pw_field.setText(old_pw);
	    		name_field.setText(old_name);
	    		age_field.setText(String.valueOf(old_age));
	    		phone_field.setText(old_ph);
	    		address_field.setText(old_add);
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
	
	public void modify(String id, String new_pw, String new_name, int new_age, String new_ph, String new_add)
	{
		Connection conn = null;
	    Statement stmt = null;
	    
	    int r = 0;
	    	    	    	    
	    try 
	    {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		
	    		conn = java.sql.DriverManager.getConnection(
	    				"jdbc:mysql://localhost:3306/db?serverTimezone=UTC&&useSSL=false", "root", "dlwlgus123");
	    			    		
	    		stmt = (Statement) conn.createStatement();
	    		
	    		r = stmt.executeUpdate("update user_info set PW='" + new_pw + "',Name='" + new_name + "',Age='" 
	    		+ new_age + "',PhoneNum='" + new_ph + "',Address='" + new_add +"' where ID='" + id + "';");
	    		
	    		JOptionPane.showMessageDialog(null, "User '" + id + "' successfuly modified!");
	    		
	    		conn.close();
	    		stmt.close();
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