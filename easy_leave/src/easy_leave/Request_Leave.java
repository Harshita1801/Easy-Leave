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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;

public class Request_Leave extends JFrame {

	private JPanel contentPane;

	int leaveLeft;
	String fDate,tDate;
	public static String us;
	JDateChooser fromDate;
	JDateChooser toDate;
	 JComboBox LeaveType;
	 JTextArea LeaveReason;
	String lty;
		public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Request_Leave frame = new Request_Leave("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Request_Leave(String User) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Request For Leave ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_1.setBounds(335, 25, 324, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("From Date");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(174, 174, 140, 47);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("To Date");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(174, 250, 140, 47);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Leave Type");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_2.setBounds(174, 331, 140, 47);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Leave Reason");
		lblNewLabel_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_2_1.setBounds(174, 415, 154, 47);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase2(User,lty);
				dispose();
				Employee ep=new Employee(User);
				ep.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 28));
		btnNewButton.setBounds(283, 590, 140, 37);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Employee ep=new Employee(User);
				ep.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		btnCancel.setBounds(519, 590, 140, 37);
		contentPane.add(btnCancel);
		
		 LeaveType = new JComboBox();
		 LeaveType.addItem("Select");;
			LeaveType.addItem("Causal Leave");
			LeaveType.addItem("Sick Leave");
		 LeaveType.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		lty=LeaveType.getSelectedItem().toString();
		 	}
		 });
		LeaveType.setBounds(383, 340, 276, 38);
		contentPane.add(LeaveType);
		
		 fromDate = new JDateChooser();
		 fromDate.setDateFormatString("y-MM-dd");
		fromDate.setBounds(383, 186, 276, 35);
		contentPane.add(fromDate);
		
		
		 toDate = new JDateChooser();
		 toDate.setDateFormatString("y-MM-dd");
		toDate.setBounds(383, 262, 276, 35);
		contentPane.add(toDate);
		tDate=toDate.toString();
		
		 LeaveReason = new JTextArea();
		LeaveReason.setBounds(383, 430, 276, 83);
		contentPane.add(LeaveReason);
	}
	static String lstatus="Pending";
	public  void SaveToDatabase2(String User,String lty)
	{	
		LocalDateTime from = LocalDateTime.ofInstant(fromDate.getDate().toInstant(), ZoneId.systemDefault());
		LocalDateTime to = LocalDateTime.ofInstant(toDate.getDate().toInstant(), ZoneId.systemDefault());
		Duration d = Duration.between(from, to);
		final int startW = from.getDayOfWeek().getValue();
		    final int endW = to.getDayOfWeek().getValue();
		    final long days = ChronoUnit.DAYS.between(from, to);
		    long result = (days+1) - 2*(days/7); 
		    if (days % 7 != 0) { 
		        if (startW == 7) {
		            result -= 1;
		        } else if (endW == 7) { 
		            result -= 1;
		        } else if (endW < startW) { 
		            result -= 2;
		        }
		    }
		try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elogindb","root","");
            Statement st = con.createStatement();
			String query = "Select * from userinfo where `Username`='"+User+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				leaveLeft=rs.getInt("Leaves left");	
        }
          }
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, w);
        }
		if(result<=leaveLeft)
		{
			try
			{Connection con=null;
				PreparedStatement pst=null;
				 con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leaveinfo","root","");
				String query ="insert into `leaves`(`Username`, `From Date`, `To Date`, `Leave Type`, `Leave Reason`, `Leave Status`,`Days`,`Leaves left`) values (?,?,?,?,?,?,?,?)";
				 pst=con.prepareStatement(query);
				pst.setString(1,User);
				pst.setString(2,((JTextField)fromDate.getDateEditor().getUiComponent()).getText());
				pst.setString(3,((JTextField)toDate.getDateEditor().getUiComponent()).getText());
				pst.setString(4,(String) lty);
				pst.setString(5,LeaveReason.getText());
				pst.setString(6,lstatus);
				pst.setLong(7,result);
				pst.setInt(8, leaveLeft);
				pst.executeUpdate();	
				con.close();
				JOptionPane.showMessageDialog(null,"Record saved !!! ");
				dispose();
				Employee ep=new Employee(User);
				ep.setVisible(true);
			}
			catch(Exception e)
			{    JOptionPane.showMessageDialog(null,e);
			}
		}
		else
			JOptionPane.showMessageDialog(null,"u cannot opt for "+result+" leaves as u have "+leaveLeft);
	}
}
