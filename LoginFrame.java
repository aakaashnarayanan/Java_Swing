package test2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.resources.windows;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	public final String JDBC_URL = "jdbc:derby:JdbcDemo;create=true";
	String databaseURL;
	Connection conn;
	Statement stmt;
	ResultSet rs;

	String user;
	String pass;

	String validatecmd;

	private JPanel contentPane;
	private JTextField Txt_UserName;
	private JTextField Txt_Password;

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void connect() throws Exception {
		conn = DriverManager.getConnection(JDBC_URL);
		System.out.println("Connection created...");
	}

	void Validate() throws Exception {
		String admin = "admin";

		validatecmd = "select password from auth where username = '" + Txt_UserName.getText() + "' ";
		System.out.println(validatecmd);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(validatecmd);
			rs.next();
			pass = rs.getString("password");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(Txt_UserName, "username not exist");
		}

		if (Txt_Password.getText().equals(pass)) {
			System.out.println("accept");

			if (Txt_UserName.getText().equals(admin)) {
				new Frame1();
				dispose();
			} else {
				Frame2 f = new Frame2(Txt_UserName.getText());
				// f.username = Txt_UserName.getText();
				// new Frame2();
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(Txt_Password, "Password is wrong");
		}
	}

	public LoginFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		connect();
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Validate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		LoginBtn.setBounds(208, 185, 85, 21);
		contentPane.add(LoginBtn);

		Txt_UserName = new JTextField();
		Txt_UserName.setBounds(253, 76, 96, 19);
		contentPane.add(Txt_UserName);
		Txt_UserName.setColumns(10);

		Txt_Password = new JTextField();
		Txt_Password.setBounds(253, 126, 96, 19);
		contentPane.add(Txt_Password);
		Txt_Password.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(163, 79, 67, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(163, 129, 67, 13);
		contentPane.add(lblNewLabel_1);
	}
}
