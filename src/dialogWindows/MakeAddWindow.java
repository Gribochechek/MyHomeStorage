package dialogWindows;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dialogWindows.GroupAddWindow.eHandler;
import input_output.ListWriter;
import main.Main;
import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.ItemGroup;

public class MakeAddWindow extends JDialog {

	AutomotiveParts_Make make;

	private JTextField tf_MakeName;
	private JButton makeAdding_OK;
	private JButton makeAdding_cancel;

	private eHandler buttonHandler = new eHandler();

	ListWriter lwr = new ListWriter();

	public MakeAddWindow() {

		setResizable(false);

		setSize(340, 180);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);

		setTitle("Adding new Make");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel title_panel = new JPanel();
		title_panel.setBounds(0, 0, 332, 48);
		getContentPane().add(title_panel);

		JLabel lblAddingNewMake = new JLabel("Adding new Make");
		lblAddingNewMake.setFont(new Font("Tahoma", Font.BOLD, 20));
		title_panel.add(lblAddingNewMake);

		JPanel makeAddingTextFieldPanel = new JPanel();
		makeAddingTextFieldPanel.setBounds(0, 49, 332, 48);
		getContentPane().add(makeAddingTextFieldPanel);
		makeAddingTextFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel lblMakeAdding = new JLabel("Make:");
		lblMakeAdding.setHorizontalAlignment(SwingConstants.LEFT);
		lblMakeAdding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		makeAddingTextFieldPanel.add(lblMakeAdding);

		tf_MakeName = new JTextField();
		tf_MakeName.setHorizontalAlignment(SwingConstants.RIGHT);
		makeAddingTextFieldPanel.add(tf_MakeName);
		tf_MakeName.setColumns(10);

		JPanel button_panel = new JPanel();
		button_panel.setBounds(0, 96, 332, 48);
		getContentPane().add(button_panel);

		makeAdding_OK = new JButton("ADD");
		makeAdding_OK.addActionListener(buttonHandler);
		button_panel.add(makeAdding_OK);

		makeAdding_cancel = new JButton("CANCEL");
		makeAdding_cancel.addActionListener(buttonHandler);
		button_panel.add(makeAdding_cancel);

	}

	private int nextMakeID() {
		int newGroupId = Main.mainWindow.makesList.get(Main.mainWindow.makesList.size() - 1).getMakeID() + 1;
		return newGroupId;

	}

	public void setResult() {
		String temp_str = tf_MakeName.getText().toLowerCase().trim();
		for (int i = 0; i < Main.mainWindow.makesList.size(); i++) {
			AutomotiveParts_Make make = Main.mainWindow.makesList.get(i);
			String str2 = make.getMakeName().toLowerCase();
			if (temp_str.equals(str2)) {
				JOptionPane.showMessageDialog(Main.mainWindow, "Such entry already exists!");

				return;
			}
		}

		if (Main.mainWindow.makesList.size() > 0) {
			make = new AutomotiveParts_Make(nextMakeID(), tf_MakeName.getText().trim());
		} else {
			make = new AutomotiveParts_Make(1, tf_MakeName.getText().trim());
		}

		Main.mainWindow.makesList.add(make);
		Main.mainWindow.sql.addMake(make);
		lwr.saveListInFile(Main.mainWindow.makesList, Main.makesListdat);
		Main.mainWindow.refreshComboBoxes();

	}

	class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == makeAdding_OK) {
				if (tf_MakeName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(Main.mainWindow, "Invalid Name format");
					return;
				}

				else
					setResult();

				dispose();
			}

			if (e.getSource() == makeAdding_cancel) {

				dispose();

			}

		}

	}

}
