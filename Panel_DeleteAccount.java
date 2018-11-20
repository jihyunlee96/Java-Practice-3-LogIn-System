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

public class Panel_DeleteAccount extends JPanel implements ActionListener {
	
	JTextField id_field;

	public Panel_DeleteAccount()
	{	
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 233));

		// label for instruction message
		JLabel info1 = new JLabel("    Type ID of an account");
		info1.setFont(new Font("Monaco", Font.BOLD, 14));
		info1.setOpaque(true);
		info1.setBackground(Color.WHITE);
		info1.setLocation(65, 80);
		info1.setSize(260, 45);	
		
		JLabel info2 = new JLabel("    		you want to delete.");
		info2.setFont(new Font("Monaco", Font.BOLD, 14));
		info2.setOpaque(true);
		info2.setBackground(Color.WHITE);
		info2.setLocation(95, 115);
		info2.setSize(260, 45);
		
		add(info2);
		add(info1);
		
		
		// label and text field for id
		My_Label id_label = new My_Label("ID:", 100, 190);
		add(id_label);
						
		id_field = new JTextField();
		id_field.setSize(160, 35);
		id_field.setLocation(155, 188);
		add(id_field);
		
		
		// create "delete" button
		JButton delete = new JButton("Delete");
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
		if (e.getActionCommand() == "Delete") {
			String id = id_field.getText();
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?\nUser: '" + id + "'", "WARNING", JOptionPane.YES_NO_OPTION);
			
			if (reply == JOptionPane.YES_OPTION) {
				id_field.setText("");
				delete(id);
			}
			
			else
				id_field.setText("");
			
		}
		
		else if (e.getActionCommand() == "<-")
			Main.frame.superUser_page();
	}
	
	public static void delete (String id)
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
	    		
	    		r = stmt.executeUpdate("delete from user_info where ID='" + id + "';");
	    		
	    		System.out.println("check");
	    		
	    		if (r == 0)
	    			JOptionPane.showMessageDialog(null, "User '" + id + "' not found.");
	    		else
				JOptionPane.showMessageDialog(null, "User '" + id + "' successfuly deleted.");
	    		
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