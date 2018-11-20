import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame_Main extends JFrame {
	
	public Frame_Main ()
	{
		// set title, size, location of frame
		setTitle("Main");
		setSize(400, 600);
		setLocation(100, 0);
		
		// add default panel
		Panel_Default panel_def = new Panel_Default();
		setContentPane(panel_def);
		
		// set layout
		setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setVisible(true);
	}
	
	public void default_page ()
	{
		setTitle("Main");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_Default());
		
		revalidate();
		repaint();
	}
	
	public void signIn_page ()
	{
		setTitle("Sign In");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_SignIn());
		
		revalidate();
		repaint();
	}
	
	public void superUser_page ()
	{
		setTitle("User: Super User");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_SuperUser());
		
		revalidate();
		repaint();
	}
	
	public void user_page (String id)
	{
		setTitle("User: " + id);
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_User(id));
		
		revalidate();
		repaint();
	}
	
	public void searchSU_page (boolean superUser, String id)
	{
		setTitle("Search User");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_SUSearch());
		
		revalidate();
		repaint();
	}
	
	public void search_page (String id)
	{
		setTitle("Search User");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_Search(id));
		
		revalidate();
		repaint();
	}
	
	public void modifySU_page ()
	{
		setTitle("Modify User Info.");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_SUModify());
		
		revalidate();
		repaint();
	}
	
	public void modifySU_page2 (boolean superUser, String id)
	{
		setTitle("Modify User '" + id + "'");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_SUModify2(superUser, id));
		
		revalidate();
		repaint();
	}
	
	public void deleteAccount_page ()
	{
		setTitle("Delete User Account");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_DeleteAccount());
		
		revalidate();
		repaint();
	}
	
	public void forgot_page ()
	{
		setTitle("Forgot Your Password?");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_Forgot());
		
		revalidate();
		repaint();
	}
	
	public void forgot_page2 (String id, String answer, String password)
	{
		setTitle("Forgot Your Password?");
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.removeAll();
		
		setContentPane(new Panel_Forgot2(id, answer, password));
		
		revalidate();
		repaint();
	}
}