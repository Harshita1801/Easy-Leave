package easy_leave;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Manage_Leaves extends JFrame implements MLinterface {
	String User1;
	int days, left , newLeft;
	boolean acc=false,rej=false;
	private JPanel contentPane;
	private JTable table;
	private JTextField username;
	private JTextField designation;

	String fDate,tDate;
	int day,leave ;
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Leaves frame = new Manage_Leaves();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Manage_Leaves() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Leaves");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(417, 10, 252, 51);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 1010, 315);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leaveinfo","root","");
			Statement st = con.createStatement();
			String s="Pending";
			String query = "Select * from `leaves` where `Leave Status`='"+s+"' ";
			ResultSet rs = st.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null," Record Not Accessable ");
		}
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Admin ad=new Admin();
				ad.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 32));
		btnNewButton.setBounds(874, 648, 151, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel_1.setBounds(57, 501, 174, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Designation");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel_1_1.setBounds(57, 565, 174, 37);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Action");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel_1_1_1.setBounds(57, 627, 174, 37);
		contentPane.add(lblNewLabel_1_1_1);
		
		JCheckBox accept = new JCheckBox("Accepted");
		accept.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					acc=true;
				}
				else
				{
					acc=false;
				}
			}
		});
		accept.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		accept.setBounds(208, 627, 159, 35);
		contentPane.add(accept);
		
		JCheckBox reject = new JCheckBox("Rejected");
		reject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					rej=true;
				}
				else
				{
					rej=false;
				}
			}
		});
		reject.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		reject.setBounds(404, 627, 159, 35);
		contentPane.add(reject);
		
		username = new JTextField();
		username.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		username.setBounds(241, 501, 198, 37);
		contentPane.add(username);
		username.setColumns(10);
		
		designation = new JTextField();
		designation.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		designation.setColumns(10);
		designation.setBounds(241, 565, 198, 37);
		contentPane.add(designation);
		
		JButton btnNewButton_1 = new JButton("Display");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnNewButton_1.setBounds(187, 432, 136, 28);
		contentPane.add(btnNewButton_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((acc==true)&&(rej==false))
				{
					SaveToDatabase("Accepted");
				}
				else if((acc==false )&&(rej==true))
				{
					SaveToDatabase("Rejected");
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Select only one");
				}
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 32));
		btnSubmit.setBounds(666, 648, 151, 37);
		contentPane.add(btnSubmit);
	}
	public void display()
	{
		int row = table.getSelectedRow();
		 DefaultTableModel model= (DefaultTableModel) table.getModel();
		 String selected = model.getValueAt(row, 0).toString();
		 int getSelectedRowForDeletion = table.getSelectedRow();
	      
		 User1=selected;
		
	        if (getSelectedRowForDeletion >= 0) {
	            try {
                  Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
                  Statement st = con.createStatement();
      			String query = "Select * from userinfo where `Username`='"+selected+"'";
      			ResultSet rs = st.executeQuery(query);
      			while(rs.next())
      			{
      				String u=rs.getString("username");
      				String d=rs.getString("designation");
      				username.setText(u);
				    designation.setText(d);
              }
  }
              catch (Exception w) {
                  JOptionPane.showMessageDialog(null, w);
              } 
	            try {
	                  Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leaveinfo","root","");
	                  Statement st = con.createStatement();
	      			String query = "Select * from leaves where `Username`='"+selected+"'";
	      			ResultSet rs = st.executeQuery(query);
	      			while(rs.next())
	      			{
	      				fDate=rs.getString("From Date");
	      				 tDate=rs.getString("To Date");
	      				days=rs.getInt("Days");
	      				left=rs.getInt("Leaves Left");
	              }
		            }
	              catch (Exception w) {
	                  JOptionPane.showMessageDialog(null, w);
	              } 
	        }
	         else {
	            JOptionPane.showMessageDialog(null, "Unable To Access");
	        }
	}
	public void SaveToDatabase(String status)
	{
		if(status.equals("Accepted"))
		{ newLeft=left-days; 
		}
		else 
			newLeft=left;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leaveinfo","root","");
			Statement st = con.createStatement();
			String query = "update `leaves` set `Leave Status`=?, `Leaves left`=? where `Username`='"+User1+"' ";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,status);
			pst.setInt(2,newLeft);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record saved !!! ");
			con.close();
		}
		catch(Exception ex)
		{	JOptionPane.showMessageDialog(null,ex);
		}
		
		try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
            Statement st = con.createStatement();
			String query = "update `userinfo` set `Leaves left`=? where `username`='"+User1+"'";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1,newLeft);
			pst.executeUpdate();
			con.close();
          }
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, w);
        }
		dispose();
		Manage_Leaves ml =new Manage_Leaves();
		ml.setVisible(true);
	}
}
