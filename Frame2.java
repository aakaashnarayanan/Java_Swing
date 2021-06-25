package test2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

public class Frame2 {

	private JFrame frame;
	private JTable table;

	public final String JDBC_URL = "jdbc:derby:JdbcDemo;create=true";
	String databaseURL;
	Connection conn;
	Statement stmt;
	ResultSet rs;

	String selectcmd;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Frame2 window = new Frame2();
	 * window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	 * } }); }
	 */

	public Frame2(String username) throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 267);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		connect();
		initialize();
		DisplayData(username);
	}

	void connect() throws Exception {
		conn = DriverManager.getConnection(JDBC_URL);
		System.out.println("Connection created...");
	}

	void DisplayData(String username) throws Exception {
		//System.out.println(username);
		selectcmd = "select * from student where sname='"+username+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectcmd);
		System.out.println(selectcmd);

		table.setModel(DbUtils.resultSetToTableModel(rs));
	}

	private void initialize() {
		/*
		 * frame = new JFrame(); frame.setBounds(100, 100, 634, 267);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.getContentPane().setLayout(null);
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 576, 103);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
