package dialogWindows;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

import main.Main;

import java.awt.FlowLayout;

public class SearchForm extends JDialog {
	private JTextField tf_Search;

	public SearchForm(Frame parent) {
		super(parent, true);
		setTitle("Search");
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setSize(350, 150);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEnterAKeyword = new JLabel("Enter a keyword");
		lblEnterAKeyword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAKeyword.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEnterAKeyword.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblEnterAKeyword);

		JPanel panel_ = new JPanel();
		getContentPane().add(panel_);

		tf_Search = new JTextField();
		panel_.add(tf_Search);
		tf_Search.setColumns(10);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JButton btnSearch = new JButton("Search");
		panel_1.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String searchWord = tf_Search.getText();
				while (!searchWord.isEmpty() && searchWord.charAt(0) == ' ')
					searchWord = searchWord.substring(1);
				if (searchWord.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Search is empty");
				} else {
					if (searchWord.length() < 3) {
						JOptionPane.showMessageDialog(null, "Enter at least 3 symbols");
					} else {

						dispose();
						searchWord = searchWord.toLowerCase();
						SearchResult sr = new SearchResult(Main.mainWindow, searchWord);
						sr.setVisible(true);

					}
				}

			}
		});

		JButton btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}
}