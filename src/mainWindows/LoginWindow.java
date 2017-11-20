package mainWindows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Main;


public class LoginWindow extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting screen size

	private int width = (int) screenSize.getWidth(); // announcing and initializing variables
	private int height = (int) screenSize.getHeight(); // for screen and window size
	private int windowWidth = width / 5;
	private int windowHeight = height / 5;

	JLabel title, user, password; // ���������� ������
	JComboBox<String> userChoser; // ���������� ����������� ������ �����
	JTextField userName;
	JPasswordField passField;
	JButton ok, cancel;

	final int START_HEIGHT_POINT = 60;
	final int START_WIDTH_POINT = 10;
	final int ELEMENT_HEIGHT = 20;
	final int TEXTFIELD_HEIGHT = 25;
	final int SPACE = 20;
	
	eHandler handler = new eHandler();

	public LoginWindow(String s) {
		super(s);

		setBounds(width / 2 - windowWidth / 2, height / 2 - windowHeight / 2, windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		title = new JLabel("Authorization:");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setSize(windowWidth / 2, ELEMENT_HEIGHT);
		title.setLocation(getWidth() / 2 - title.getWidth() / 2, getHeight() / 10);
		getContentPane().add(title);

		user = new JLabel("User:");
		user.setFont(new Font("Times New Roman", Font.BOLD, 20));
		user.setBounds(SPACE, START_HEIGHT_POINT, 100, ELEMENT_HEIGHT);
		getContentPane().add(user);

		userName = new JTextField();
		userName.setBounds(user.getX() + user.getWidth() + SPACE, user.getY(), user.getWidth() * 2, ELEMENT_HEIGHT);
		getContentPane().add(userName);
		userName.setColumns(10);

		password = new JLabel("Password:");
		password.setFont(new Font("Times New Roman", Font.BOLD, 20));
		password.setBounds(SPACE, user.getY() + ELEMENT_HEIGHT * 2, 100, ELEMENT_HEIGHT);
		getContentPane().add(password);

		passField = new JPasswordField();
		passField.setBounds(password.getX() + password.getWidth() + SPACE, password.getY(), userName.getWidth(),
				ELEMENT_HEIGHT);
		getContentPane().add(passField);

		ok = new JButton("OK");
		ok.setBounds(SPACE, password.getY() + ELEMENT_HEIGHT * 2, windowWidth / 3, ELEMENT_HEIGHT);
		getContentPane().add(ok);
		ok.addActionListener(handler);

		cancel = new JButton("Cancel");
		cancel.setBounds(windowWidth - ok.getWidth() - SPACE, ok.getY(), windowWidth / 3, ELEMENT_HEIGHT);
		getContentPane().add(cancel);
		cancel.addActionListener(handler);

	}

	public class eHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == ok) {

				if (userName.getText().equals("User")) {
					if (checkPassword()) {
						try {
							Main.mainWindow = new MainWindow("Home Storage");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect password");
						return;
					}
				}

				Main.mainWindow.setVisible(true);
				Main.mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
				dispose();
			}

			if (e.getSource() == cancel) {
				System.exit(0);
			}
		}

		public boolean checkPassword() {
			char[] correctPass = { 'A', 'n', 'd', 'r', 'o', 'm', 'e', 'd', 'a' };
			char[] pass = passField.getPassword();

			if (pass.length != correctPass.length)
				return false;

			else
				for (int i = 0; i < correctPass.length; i++) {
					if (pass[i] != correctPass[i])
						return false;
				}
			return true;

		}
	}
}
