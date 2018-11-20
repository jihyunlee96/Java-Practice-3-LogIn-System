import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Panel_SUSearch extends JPanel implements ActionListener, DocumentListener {

	JList<String> id_list;
	Vector<String> id_vector;
	JScrollPane scroll;
	
	JTextField search;
	
	JButton button;
	
	JTextArea print;

	public Panel_SUSearch()
	{	
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 233));
		
		
		// create id_list and id_vector, scroll
		id_vector = new Vector<String>();
		init_id_vector();
		
		id_list = new JList<String>(id_vector);
		id_list.setBounds(50, 100, 300, 130);
		
		scroll = new JScrollPane(id_list);
		scroll.setVisible(true);;
		add(scroll);

		id_list.setVisible(true);
		add(id_list);
		
		id_list.setSelectedIndex(0);
		
		
		// create text field for id_list
		search = new JTextField();
		search.setBounds(45, 70, 310, 30);
		search.getDocument().addDocumentListener((DocumentListener) this);
		add(search);
		
		
		// create "search" button
		button = new JButton ("Search!");
		button.setSize(80, 35);
		button.setLocation(165, 240);
		button.setFont(new Font("Monaco", Font.BOLD, 11));
		button.setForeground(Color.RED);
		button.addActionListener((ActionListener) this);
		add(button);
		
		
		// create label to print user info
		print = new JTextArea (); 
		print.setFont(new Font("Monaco", Font.BOLD, 11));
		print.setOpaque(true);
		print.setBackground(Color.WHITE);
		print.setBounds(50, 290, 300, 220);
		add(print);
		
				
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
			Main.frame.superUser_page();
		}
		
		else if (e.getActionCommand() == "Search!") {
			search(id_vector.get(id_list.getSelectedIndex()));
		}
	}
	
	public void insertUpdate (DocumentEvent ev)
	{
		if (ev.getDocument() == search.getDocument()) {
			String text = search.getText();
			search_select(text);
		}
	}
	
	public void removeUpdate (DocumentEvent ev)
	{

	}
	
	public void changedUpdate (DocumentEvent ev)
	{
		if (ev.getDocument() == search.getDocument()) {
			String text = search.getText();
			search_select(text);
		}
	}
	
	public void init_id_vector()
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
	    		
	    		rs = stmt.executeQuery("select ID from user_info;");

	    		while (rs.next())
	    		{
	    			String id = rs.getString("ID");
	    			id_vector.add(id);
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
	
	public void search_select (String text)
	{		
		if (text.length() == 0)
			return;
		
		for (int i = 0; i < id_vector.size(); i ++)
		{
			if (id_vector.get(i).toUpperCase().startsWith(text.toUpperCase())) {
				id_list.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public void search (String id)
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
	    		
	    		rs = stmt.executeQuery("select * from user_info where ID='" + id +"';");
	    	
	    		// id 를 찾지 못했을 경우
	    		if (!rs.next()) {
				return;
	    		}
	    		
	    		String pw = "", name = "", ph = "", add = "";
	    		int age = 0;    		

		 	// id 에 해당되는 값들 가져오기
	    		pw = rs.getString("PW");
			name = rs.getString("Name");	   
			age = rs.getInt("Age");
			ph = rs.getString("PhoneNum");
			add = rs.getString("Address");
		    		
		    	String result = "\n\n   [ID]  " + id + "\n\n" + "   [Password]  " + pw + "\n\n" + "   [Name]  " + name + "\n\n"
		    			+ "   [Age]  " + String.valueOf(age) + "\n\n" + "   [Phone Number]  " + ph + "\n\n"
		    			+ "   [Address]  " + add + "\n";
		    		
		    	print.setText(result);
	    		
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