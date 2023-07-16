package easy_leave;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	String user;
	Connection con=null;
	PreparedStatement pst=null;
	private JPanel contentPane;
	private JTextField firstname;
	private JPasswordField password;
	private JPasswordField cpassword;
	private JTextField lastname;
	private JTextField username;
	private JTextField mail;
	private JTextField designation;
	private JTextField phoneno;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(285, 27, 309, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(153, 137, 165, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(153, 198, 165, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(153, 261, 165, 24);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_3.setBounds(153, 322, 165, 24);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("E-mail ID");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_4.setBounds(153, 442, 165, 24);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Designation");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_5.setBounds(153, 500, 165, 24);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Phone Number");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_6.setBounds(153, 560, 165, 24);
		contentPane.add(lblNewLabel_1_6);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 28));
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				 verifyUsername();
				verifyPassword();
				verifyMail();
				 phoneNumber();
				 CheckEmpty();
			  //   SaveToDatabase();
			 }
			 });
		btnNewButton.setBounds(323, 632, 240, 55);
		contentPane.add(btnNewButton);
		
		firstname = new JTextField();
		firstname.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		firstname.setBounds(395, 123, 309, 38);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Confirm Password");
		lblNewLabel_1_4_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_4_1.setBounds(153, 383, 212, 24);
		contentPane.add(lblNewLabel_1_4_1);
		
		password = new JPasswordField();
		password.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		password.setBounds(393, 307, 316, 38);
		contentPane.add(password);
		
		cpassword = new JPasswordField();
		cpassword.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cpassword.setBounds(393, 372, 316, 38);
		contentPane.add(cpassword);
		
		lastname = new JTextField();
		lastname.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lastname.setColumns(10);
		lastname.setBounds(395, 184, 309, 38);
		contentPane.add(lastname);
		
		username = new JTextField();
		username.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		username.setColumns(10);
		username.setBounds(395, 247, 309, 38);
		contentPane.add(username);
		
		mail = new JTextField();
		mail.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		mail.setColumns(10);
		mail.setBounds(395, 428, 309, 38);
		contentPane.add(mail);
		
		designation = new JTextField();
		designation.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		designation.setColumns(10);
		designation.setBounds(395, 486, 309, 38);
		contentPane.add(designation);
		
		phoneno = new JTextField();
		phoneno.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		phoneno.setColumns(10);
		phoneno.setBounds(395, 546, 309, 38);
		contentPane.add(phoneno);
	}
	
	@SuppressWarnings("deprecation")
	public  void SaveToDatabase()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
			String query= "insert into `userinfo` (`firstName`,`lastname`,`username`,`password`,`Email`,`designation`,`phone`,`Leaves left`) values(?,?,?,?,?,?,?,?)";
			
			pst=con.prepareStatement(query);
			pst.setString(1,firstname.getText());
			pst.setString(2,lastname.getText());
			pst.setString(3,username.getText());
			pst.setString(4,password.getText());
			pst.setString(5,mail.getText());
			pst.setString(6,designation.getText());
			pst.setString(7,phoneno.getText());
			pst.setInt(8,10);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record saved !!! ");
			String text=username.getText().toString();
			con.close();
			dispose();
			Employee emp = new Employee(text);
			// dispose the register frame
			
			emp.setVisible(true);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Record not saved !!! ");
		}
	}
	public void verifyPassword()
	{
		String pass=String.valueOf(password.getPassword());
		String cpass=String.valueOf(cpassword.getPassword());
		if(pass.equals(cpass))
		{
			return;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Enter same password for confirm password ");
			//cpassword.setPassword("");
		}
	}
	public void phoneNumber()
	{
		String phone=phoneno.getText();
		int l=phone.length();
		if(l==10||l==12)
		{
			return;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Enter proper phone number ");
			phoneno.setText(" ");
		}
	}
	public void verifyUsername()
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
			Statement st = con.createStatement();
			String query = "Select username from `userinfo` ";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				user=rs.getString("username");
				String user1=username.getText();
				if(user1.equals(user))
				{
					JOptionPane.showMessageDialog(null,"Please use a different Username ");
					username.setText("");
				}
				
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null," Record Not Accessable ");
		}
	}
	public void verifyMail()
	{
		String checkMail = mail.getText();
		String cm;
		int i=checkMail.indexOf("@");
		cm=checkMail.substring(i);
		//System.out.println(cm);
		if(cm.equals("@gmail.com")||cm.equals("@outlook.com")||cm.equals("@yahoo.com"))
		{
			return ;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Please enter a valid E-mail ");
			mail.setText("");
		}
	}
	public void CheckEmpty()
	{
		if(username.getText().equals("")||firstname.getText().equals("")||lastname.getText().equals("")||mail.getText().equals("")||designation.getText().equals("")||phoneno.getText().equals(""))
				{
			JOptionPane.showMessageDialog(null,"Please enter all the fields ");
				}
		else
		{
			SaveToDatabase();
		}
	}
}
