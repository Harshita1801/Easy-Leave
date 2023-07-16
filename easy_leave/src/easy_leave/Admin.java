package easy_leave;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Admin extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN PAGE ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(365, 40, 263, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnManageUsers = new JButton("Manage Users ");
		btnManageUsers.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnManageUsers.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				 dispose();
				 Manage_Users mu = new Manage_Users();
				 mu.setVisible(true);
				
			 }
			 });
		btnManageUsers.setBounds(164, 249, 272, 42);
		contentPane.add(btnManageUsers);
		
		JButton btnManageLeaves = new JButton("Manage Leaves ");
		btnManageLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Manage_Leaves ml =new Manage_Leaves();
				ml.setVisible(true);
			}
		});
		btnManageLeaves.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnManageLeaves.setBounds(546, 249, 272, 42);
		contentPane.add(btnManageLeaves);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lo = new Login();
				lo.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnNewButton.setBounds(430, 447, 153, 42);
		contentPane.add(btnNewButton);
	}
}
