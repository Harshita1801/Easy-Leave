package easy_leave;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jdatepicker.JDateComponentFactory;

public class Manage_Users extends JFrame {
	private JPanel contentPane;
	private JTable table_2;
	static JPopupMenu pm;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Users frame = new Manage_Users();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Manage_Users() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Users");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(364, 32, 241, 42);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 129, 920, 403);
		contentPane.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		table_2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
			Statement st = con.createStatement();
			String query = "Select * from userinfo";
			ResultSet rs = st.executeQuery(query);
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception ex)
		{           JOptionPane.showMessageDialog(null, ex);
		}
			JButton btnRemoveUser = new JButton("Remove User");
			btnRemoveUser.setFont(new Font("Times New Roman", Font.BOLD, 29));
			btnRemoveUser.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent ae) {
					 
					 int row = table_2.getSelectedRow();
					 DefaultTableModel model= (DefaultTableModel) table_2.getModel();
					 String selected = model.getValueAt(row, 0).toString();
					 int getSelectedRowForDeletion = table_2.getSelectedRow();
				   if (getSelectedRowForDeletion >= 0) {
				            model.removeRow(getSelectedRowForDeletion);
				   try {
			         Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
			             PreparedStatement ps = conn.prepareStatement("delete from  `userinfo` where `firstName` ='"+selected+"'");
			                    ps.executeUpdate();
			                    conn.close();
			                }
			                catch (Exception w) {
			                    JOptionPane.showMessageDialog(null, "Connection Error!");
			                } 
				            JOptionPane.showMessageDialog(null, "User removed");
				        } else {
				            JOptionPane.showMessageDialog(null, "Unable To Delete");
				        }
				 }
				 });

			btnRemoveUser.setBounds(126, 563, 217, 37);
			contentPane.add(btnRemoveUser);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Admin ad=new Admin();
					ad.setVisible(true);
				}
			});
			btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 29));
			btnCancel.setBounds(544, 563, 217, 37);
			contentPane.add(btnCancel);
	}
}
