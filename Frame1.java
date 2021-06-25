package test2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Frame1 {

	private JFrame frame;
	private static JTable table;

	public final static String JDBC_URL = "jdbc:derby:JdbcDemo;create=true";
	static String databaseURL;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;

	static String selectcmd;
	static String insertcmd;
	static String deletecmd;
	static String showcmd;
	static String updatecmd;

	private static JTextField txtSno;
	private static JTextField txtSname;
	private static JTextField txtMark1;
	private static JTextField txtMark2;
	private static JTextField txtMark3;
	private static JTextField txtMark4;
	private static JTextField txtMark5;

	private JButton Delete_btn;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton Update_btn;

	/*
	 * public static void main(String[] args) throws Exception { connect();
	 * EventQueue.invokeLater(new Runnable() { public void run() { try { Frame1
	 * window = new Frame1(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	static void connect() throws Exception {
		conn = DriverManager.getConnection(JDBC_URL);
		System.out.println("Connection created...");
	}

	static void DisplayData() throws Exception {
		selectcmd = "select * from student";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectcmd);

		table.setModel(DbUtils.resultSetToTableModel(rs));
	}

	static void InsertData() throws Exception {
		insertcmd = "insert into student values (" + txtSno.getText() + ",'" + txtSname.getText() + "',"
				+ txtMark1.getText() + "," + txtMark2.getText() + "," + txtMark3.getText() + "," + txtMark4.getText()
				+ "," + txtMark5.getText() + ")";
		stmt = conn.createStatement();
		stmt.executeUpdate(insertcmd);
		System.out.println("Value inserted:");
		System.out.println(insertcmd);
	}

	static void DeleteData() throws Exception {
		deletecmd = "delete from student where sno=" + txtSno.getText();
		stmt = conn.createStatement();
		stmt.executeUpdate(deletecmd);
		System.out.println("Value deleted ");
	}

	static void showData() throws Exception {
		showcmd = "select * from student where sno=" + txtSno.getText();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(showcmd);
		rs.next();
		txtSno.setText("" + rs.getString("sno"));
		txtSname.setText("" + rs.getString("sname"));
		txtMark1.setText("" + rs.getString("mark1"));
		txtMark2.setText("" + rs.getString("mark2"));
		txtMark3.setText("" + rs.getString("mark3"));
		txtMark4.setText("" + rs.getString("mark4"));
		txtMark5.setText("" + rs.getString("mark5"));
	}

	static void updateData() throws Exception {
		updatecmd = "update student set sname='" + txtSname.getText() + "', mark1="
				+ Float.parseFloat(txtMark1.getText()) + ", mark2=" + Float.parseFloat(txtMark2.getText()) + ",mark3="
				+ Float.parseFloat(txtMark3.getText()) + ",mark4=" + Float.parseFloat(txtMark4.getText()) + ",mark5="
				+ Float.parseFloat(txtMark5.getText()) + " where sno=" + Integer.parseInt(txtSno.getText());
		stmt = conn.createStatement();
		stmt.executeUpdate(updatecmd);
	}

	public Frame1() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 659, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		connect();
		initialize();
		DisplayData();
	}

	private void initialize() throws Exception {
		/*
		 * frame = new JFrame(); frame.setBounds(100, 100, 659, 527);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.getContentPane().setLayout(null);
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(20, 23, 603, 162);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton Display_btn = new JButton("Refresh");
		Display_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectcmd = "select * from student";
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(selectcmd);

					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		Display_btn.setBounds(415, 217, 195, 21);
		frame.getContentPane().add(Display_btn);

		JButton Insert_btn = new JButton("Insert");
		Insert_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InsertData();
					DisplayData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Insert_btn.setBounds(415, 262, 195, 21);
		frame.getContentPane().add(Insert_btn);

		Delete_btn = new JButton("delete");
		Delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteData();
					DisplayData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Delete_btn.setBounds(415, 303, 195, 21);
		frame.getContentPane().add(Delete_btn);

		JButton Show_Btn = new JButton("Show");
		Show_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Show_Btn.setBounds(415, 344, 195, 21);
		frame.getContentPane().add(Show_Btn);

		Update_btn = new JButton("Update");
		Update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Update_btn.setBounds(415, 386, 195, 21);
		frame.getContentPane().add(Update_btn);

		txtSno = new JTextField();
		txtSno.setBounds(76, 229, 96, 19);
		frame.getContentPane().add(txtSno);
		txtSno.setColumns(10);

		txtSname = new JTextField();
		txtSname.setBounds(76, 263, 96, 19);
		frame.getContentPane().add(txtSname);
		txtSname.setColumns(10);

		txtMark1 = new JTextField();
		txtMark1.setBounds(76, 304, 96, 19);
		frame.getContentPane().add(txtMark1);
		txtMark1.setColumns(10);

		txtMark2 = new JTextField();
		txtMark2.setBounds(76, 345, 96, 19);
		frame.getContentPane().add(txtMark2);
		txtMark2.setColumns(10);

		txtMark3 = new JTextField();
		txtMark3.setBounds(76, 387, 96, 19);
		frame.getContentPane().add(txtMark3);
		txtMark3.setColumns(10);

		txtMark4 = new JTextField();
		txtMark4.setBounds(76, 426, 96, 19);
		frame.getContentPane().add(txtMark4);
		txtMark4.setColumns(10);

		txtMark5 = new JTextField();
		txtMark5.setBounds(238, 229, 96, 19);
		frame.getContentPane().add(txtMark5);
		txtMark5.setColumns(10);

		lblNewLabel = new JLabel("SNO :");
		lblNewLabel.setBounds(10, 232, 34, 16);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("SNAME :");
		lblNewLabel_1.setBounds(10, 266, 56, 13);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("MARK1 :");
		lblNewLabel_2.setBounds(10, 307, 67, 13);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("MARK2 :");
		lblNewLabel_3.setBounds(10, 348, 67, 13);
		frame.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("MARK3 :");
		lblNewLabel_4.setBounds(10, 390, 67, 13);
		frame.getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("MARK4 :");
		lblNewLabel_5.setBounds(10, 429, 67, 13);
		frame.getContentPane().add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Mark5 :");
		lblNewLabel_6.setBounds(183, 232, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);

	}
}