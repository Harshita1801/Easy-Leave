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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class My_Leaves extends JFrame {
	private JPanel contentPane;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_Leaves frame = new My_Leaves("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public My_Leaves(String User) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel My_Leaves =  new JLabel("My Leaves");
		My_Leaves.setFont(new Font("Times New Roman", Font.BOLD, 35));
		My_Leaves.setBounds(326, 25, 199, 42);
		contentPane.add(My_Leaves);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Employee ep = new Employee(User);
				ep.setVisible(true);
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		cancel.setBounds(392, 479, 160, 40);
		contentPane.add(cancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 100, 851, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leaveinfo","root","");
			Statement st = con.createStatement();
			String query = "Select * from `leaves` where `Username`='"+User+"' ";
			ResultSet rs = st.executeQuery(query);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null," Record Not Accessable ");
		}
		
	}
}
