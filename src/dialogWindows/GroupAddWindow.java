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

import input_output.GroupListWriter;
import main.Main;
import objects_For_Items.ItemGroup;

public class GroupAddWindow extends JDialog {
	private JTextField tf_GroupName;

	ItemGroup group;
	public JButton btnGroupAdding_OK;
	private JButton btn_cancel;

	private eHandler buttonHandler = new eHandler();
	GroupListWriter gw = new GroupListWriter();

	public GroupAddWindow() {
		setResizable(false);

		setSize(340, 180);
		setLocation(Main.mainWindow.getWidth() - getWidth() / 2, 200);

		setTitle("Adding new group of items");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 332, 48);
		getContentPane().add(panel);

		JLabel lblAddingNewGroup = new JLabel("Adding new group of items");
		lblAddingNewGroup.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblAddingNewGroup);

		JPanel groupAddingTextFieldPanel = new JPanel();
		groupAddingTextFieldPanel.setBounds(0, 49, 332, 48);
		getContentPane().add(groupAddingTextFieldPanel);
		groupAddingTextFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel lblGroupAdding = new JLabel("Group name:");
		lblGroupAdding.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroupAdding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		groupAddingTextFieldPanel.add(lblGroupAdding);

		tf_GroupName = new JTextField();
		tf_GroupName.setHorizontalAlignment(SwingConstants.RIGHT);
		groupAddingTextFieldPanel.add(tf_GroupName);
		tf_GroupName.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 96, 332, 48);
		getContentPane().add(panel_1);

		btnGroupAdding_OK = new JButton("ADD");
		btnGroupAdding_OK.addActionListener(buttonHandler);
		panel_1.add(btnGroupAdding_OK);

		btn_cancel = new JButton("CANCEL");
		btn_cancel.addActionListener(buttonHandler);
		panel_1.add(btn_cancel);

	}

	private int nextGroupID() {
		int newGroupId = Main.mainWindow.groupsList.get(Main.mainWindow.groupsList.size() - 1).getGroupID() + 1;
		return newGroupId;

	}

	public void setResult() {
		String temp_str = tf_GroupName.getText().toLowerCase().trim();
		for (int i = 0; i < Main.mainWindow.groupsList.size(); i++) {
			ItemGroup group = Main.mainWindow.groupsList.get(i);
			String str2 = group.getGroupName().toLowerCase();
			if (temp_str.equals(str2)) {
				JOptionPane.showMessageDialog(Main.mainWindow, "Such entry already exists!");

				return;
			}
		}

		if (Main.mainWindow.groupsList.size() > 0) {
			group = new ItemGroup(nextGroupID(), tf_GroupName.getText().trim());
		} else {
			group = new ItemGroup(1, tf_GroupName.getText().trim());
		}

		Main.mainWindow.groupsList.add(group);
		gw.saveGroupsInFile(Main.mainWindow.groupsList);
		Main.mainWindow.refreshComboBoxes();

	}

	class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGroupAdding_OK) {
				if (tf_GroupName.getText().equals("")) {
					JOptionPane.showMessageDialog(Main.mainWindow, "Invalid Name format");
					return;
				}

				else
					setResult();

				dispose();
			}

			if (e.getSource() == btn_cancel) {

				dispose();

			}

		}

	}
}
