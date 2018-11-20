import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel_User extends JPanel implements ActionListener {

	String id = "";
	
	public Panel_User(String in_id)
	{	
		id = in_id;
		
		// set layout and background color
		setLayout(null);
		setBackground(new Color(233, 233, 233));
		
		
		// label for welcoming message
		JLabel info = new JLabel("Welcome " + id + " !");
		info.setFont(new Font("Monaco", Font.BOLD, 18));
		info.setOpaque(true);
		info.setBackground(Color.WHITE);
		info.setLocation(85, 80);
		info.setSize(240, 45);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		add(info);
		
		
		// create "Search User" button
		JButton search = new JButton("Search User");
		search.setSize(220, 45);
		search.setLocation(95, 160);
		search.setFont(new Font("Monaco", Font.BOLD, 13));
		search.addActionListener(this);
		add(search);
		
		
		// create "Modify User Info" button
		JButton modify = new JButton("Modify User Info.");
		modify.setSize(220, 45);
		modify.setLocation(95, 235);
		modify.setFont(new Font("Monaco", Font.BOLD, 13));
		modify.addActionListener(this);
		add(modify);
		
		
		// create "Delete User Account" button
		JButton delete = new JButton("Delete User Account");
		delete.setSize(220, 45);
		delete.setLocation(95, 310);
		delete.setFont(new Font("Monaco", Font.BOLD, 13));
		delete.addActionListener(this);
		add(delete);
		
		
		// create "Log Out" button
		JButton log_out = new JButton("Log Out");
		log_out.setSize(220, 45);
		log_out.setLocation(95, 385);
		log_out.setFont(new Font("Monaco", Font.BOLD, 13));
		log_out.setForeground(Color.RED);
		log_out.addActionListener(this);
		add(log_out);
		
		
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
		
		else if (e.getActionCommand() == "Search User") {
			Main.frame.search_page(id);
		}
		
		else if (e.getActionCommand() == "Modify User Info.") {
			Main.frame.modifySU_page2(false, id);
		}
		
		else if (e.getActionCommand() == "Delete User Account") {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?\nUser: '" + id + "'", "WARNING", JOptionPane.YES_NO_OPTION);
			
			if (reply == JOptionPane.YES_OPTION) {
				Panel_DeleteAccount.delete(id);
				JOptionPane.showMessageDialog(null, "'" + id + "' successfully deleted.");
				Main.frame.default_page();
			}
		}
		
		else if (e.getActionCommand() == "Log Out") {
			JOptionPane.showMessageDialog(null, "Logged Out!");
			Main.frame.default_page();
		}
	}
}