package easy_leave;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class Employee extends JFrame {
	public int leave=0;
	private JPanel contentPane;
	int leaveLeft;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame1 = new Employee("");
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Employee(String User1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOME PAGE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(321, 31, 294, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnRequestLeave = new JButton("Request Leave ");
		btnRequestLeave.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnRequestLeave.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				checkLeave(User1);
			 }
			 });
		btnRequestLeave.setBounds(76, 138, 210, 39);
		contentPane.add(btnRequestLeave);
		
		JButton btnMyLeaves = new JButton("My Leaves");
		btnMyLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				My_Leaves ml = new My_Leaves(User1);
				ml.setVisible(true);
			}
		});
		btnMyLeaves.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnMyLeaves.setBounds(76, 238, 210, 39);
		contentPane.add(btnMyLeaves);
		
		JButton btnEditProfile = new JButton("Edit profile");
		btnEditProfile.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnEditProfile.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				 dispose();
				 Edit_Profile ep = new Edit_Profile(User1);
				 ep.setVisible(true);
			 }
			 });
		btnEditProfile.setBounds(76, 334, 210, 39);
		contentPane.add(btnEditProfile);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnLogout.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				dispose();
				Login lo=new Login();
				lo.frame.setVisible(true);
			 }
			 });
		btnLogout.setBounds(76, 430, 210, 39);
		contentPane.add(btnLogout);
	}
	public void checkLeave(String user1)
	{
		try {
            Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
            Statement st = con.createStatement();
			String query = "Select * from userinfo where `username`='"+user1+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				leaveLeft=rs.getInt("Leaves left");
            }
          }
        catch (Exception w)
		{
            JOptionPane.showMessageDialog(null, w);
        }
		if(leaveLeft<=10&&leaveLeft>0)
		{
			JOptionPane.showMessageDialog(null,"You can take Leave and leaves left are :- "+leaveLeft);
			dispose();
			Request_Leave reqL =new Request_Leave(user1);
			reqL.setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"You dont have enough leaves ");
		}
		
	}
	
}
