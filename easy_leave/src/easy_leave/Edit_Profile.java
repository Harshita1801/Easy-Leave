package easy_leave;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Edit_Profile extends JFrame {
	private JPanel contentPane;
	private static JTextField fName;
	private static JTextField lName;
	private static JTextField username;
	private static JTextField Password;
	private static JTextField Email;
	private static JTextField designation;
	private static JTextField phoneno;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Profile frame = new Edit_Profile(" ");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Edit_Profile(String User) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 738);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Profile");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(369, 25, 248, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(251, 111, 134, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(251, 185, 134, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Username");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(251, 253, 134, 33);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Password");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_2.setBounds(251, 317, 134, 33);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("E-mail");
		lblNewLabel_1_1_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_3.setBounds(251, 390, 134, 33);
		contentPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Designation");
		lblNewLabel_1_1_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_4.setBounds(251, 463, 134, 33);
		contentPane.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_4_1 = new JLabel("Phone No.");
		lblNewLabel_1_1_4_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_4_1.setBounds(251, 530, 134, 33);
		contentPane.add(lblNewLabel_1_1_4_1);
		
		fName = new JTextField();
		fName.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		fName.setBounds(447, 111, 215, 33);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		lName.setColumns(10);
		lName.setBounds(447, 185, 215, 33);
		contentPane.add(lName);
		
		username = new JTextField();
		username.setEditable(false);
		username.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		username.setColumns(10);
		username.setBounds(447, 253, 215, 33);
		contentPane.add(username);
		
		Password = new JTextField();
		Password.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		Password.setColumns(10);
		Password.setBounds(447, 317, 215, 33);
		contentPane.add(Password);
		
		Email = new JTextField();
		Email.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		Email.setColumns(10);
		Email.setBounds(447, 390, 439, 33);
		contentPane.add(Email);
		
		designation = new JTextField();
		designation.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		designation.setColumns(10);
		designation.setBounds(447, 463, 215, 33);
		contentPane.add(designation);
		
		phoneno = new JTextField();
		phoneno.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		phoneno.setColumns(10);
		phoneno.setBounds(447, 530, 215, 33);
		contentPane.add(phoneno);
		
		showProfile(User);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
					Statement st = con.createStatement();
					String query = "update `userinfo` set `firstName`=?,`lastname`=?,`username`=?,`password`=?,`Email`=?,`designation`=?,`phone`=? where `username`='"+User+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1,fName.getText());
					pst.setString(2,lName.getText());
					pst.setString(3,username.getText());
					pst.setString(4,Password.getText());
					pst.setString(5,Email.getText());
					pst.setString(6,designation.getText());
					pst.setString(7,phoneno.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record saved !!! ");
					con.close();
				}
				catch(Exception ex)
				{   JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 35));
		btnupdate.setBounds(287, 615, 151, 39);
		contentPane.add(btnupdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Employee emp=new Employee(User);
				emp.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		btnCancel.setBounds(546, 615, 151, 39);
		contentPane.add(btnCancel);
	}
	public static void showProfile(String User)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
			Statement st = con.createStatement();
			String query = "Select * from userinfo";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				String f=rs.getString("firstName");
				String l=rs.getString("lastName");
				String u=rs.getString("username");
				String p=rs.getString("password");
				String e=rs.getString("Email");
				String d=rs.getString("designation");
				String ph=rs.getString("phone");
				if(User.equals(u))
				{
					fName.setText(f);
					lName.setText(l);
				    username.setText(u);
					Password.setText(p);
					Email.setText(e);
				    designation.setText(d);
				    phoneno.setText(ph);
				}
			}
			con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
