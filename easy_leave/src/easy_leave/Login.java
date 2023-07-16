package easy_leave;

import java.awt.EventQueue;


import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class Login {

	public JFrame frame;
	private JTextField Username;
	private JPasswordField Password;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
		
	}
	 static Connection con; 
	PreparedStatement ps;

	private void initialize() {
		frame = new JFrame("EASY LEAVE");
		frame.setBounds(100, 100, 1020, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel.setBounds(397, 135, 236, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(323, 255, 144, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(323, 333, 144, 27);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		Username = new JTextField();
		Username.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		Username.setBounds(499, 255, 192, 27);
		frame.getContentPane().add(Username);
		Username.setColumns(10);
		
		Password = new JPasswordField();
		Password.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Password.setBounds(499, 333, 192, 27);
		frame.getContentPane().add(Password);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				Verify();
			 }
			 });
		btnNewButton.setBounds(437, 444, 151, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New User , CLICK HERE");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnNewButton_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
					Register reg = new Register();
					frame.dispose();
					reg.setVisible(true);

			 }
			 });
		btnNewButton_1.setBounds(323, 550, 409, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("EASY LEAVE");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2.setBounds(395, 36, 238, 43);
		frame.getContentPane().add(lblNewLabel_2);
		
	}
	//public String user=Username.getText();
	
	public void Verify()
	{
		boolean count=false;
		 String user=Username.getText();
		String pass=String.valueOf(Password.getPassword());
		String user1,pass1;
		if(user.equals("Harshu")&&pass.equals("harshu"))
		{
			JOptionPane.showMessageDialog(null,"Welcome Admin "); 
			frame.dispose();
			Admin ad = new Admin();
			ad.setVisible(true);;
			
		}
		else
		{
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
				Statement st = con.createStatement();
				String query = "Select username, password from userinfo";
				ResultSet rs = st.executeQuery(query);
				while(rs.next())
				{
					user1=rs.getString("username");
					pass1=rs.getString("password");
					if(user.equals(user1)&&pass.equals(pass1))
					{
						count=true;
						JOptionPane.showMessageDialog(null,"Welcome "+user1);
						Employee reg = new Employee(user1);
						frame.dispose();
						reg.setVisible(true);
						return;
					}
				}
			}
			catch(Exception ex)
			{
				
			}
			if(count==false)
			{
				JOptionPane.showMessageDialog(null,"Enter correct username and password ");
			}
		}
	}
}
