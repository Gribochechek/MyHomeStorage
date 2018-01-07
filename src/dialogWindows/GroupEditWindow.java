package dialogWindows;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import input_output.GroupListWriter;
import input_output.ListWriter;
import main.Main;
import objects_For_Items.ItemGroup;

public class GroupEditWindow extends JFrame {
	private JTextField GroupNameTextField;
	private JComboBox comboBox_GroupChooser;
	private JLabel lblGroupName;
	private JButton btnOk;
	private JButton btnCancel;

	String oldGroupName;
	int indexOfTempGroopInArrayList = 0;
	ItemGroup tempGroup;

	Listener groupEditWindowListener = new Listener();

	public GroupEditWindow() {

		setSize(420, 188);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setTitle("EditingGroup");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		getContentPane().add(panel);

		JLabel lblGroupChooser = new JLabel("Choose Group");
		lblGroupChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGroupChooser.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblGroupChooser);

		comboBox_GroupChooser = new JComboBox();
		comboBox_GroupChooser.setMaximumRowCount(10);
		String[] string = Main.mainWindow.getGroupsNames();
		comboBox_GroupChooser.setModel(new DefaultComboBoxModel(string));
		comboBox_GroupChooser.addActionListener(groupEditWindowListener);
		panel.add(comboBox_GroupChooser);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		lblGroupName = new JLabel("Group Name");
		lblGroupName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblGroupName);

		GroupNameTextField = new JTextField();
		panel_1.add(GroupNameTextField);
		GroupNameTextField.setText(Main.mainWindow.groupsList.get(0).getGroupName());
		GroupNameTextField.setColumns(10);
		oldGroupName = GroupNameTextField.getText().toLowerCase();

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);

		btnOk = new JButton("OK");
		btnOk.addActionListener(groupEditWindowListener);
		panel_2.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(groupEditWindowListener);
		panel_2.add(btnCancel);
	}

	public class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == comboBox_GroupChooser) {
				GroupNameTextField.setText(
						Main.mainWindow.groupsList.get(comboBox_GroupChooser.getSelectedIndex()).getGroupName());
				oldGroupName = GroupNameTextField.getText().toLowerCase();
				indexOfTempGroopInArrayList = comboBox_GroupChooser.getSelectedIndex();

			} else if (e.getSource() == btnOk) {
				setResult();
				Main.mainWindow.refreshComboBoxes();
				dispose();

			} else if (e.getSource() == btnCancel) {
				dispose();
			}

		}

		public void setResult() {

			String temp_str = GroupNameTextField.getText().trim();
			if (!oldGroupName.equals(temp_str.toLowerCase())) {
				for (ItemGroup gr : Main.mainWindow.groupsList) {
					if (gr.getGroupName().toLowerCase().equals(temp_str.toLowerCase())) {
						JOptionPane.showMessageDialog(null, "Such entry already exists!");
						return;
					}
				}

			}

			tempGroup = new ItemGroup((Main.mainWindow.groupsList.get(indexOfTempGroopInArrayList).getGroupID()),
					GroupNameTextField.getText());
			Main.mainWindow.groupsList.remove(indexOfTempGroopInArrayList);
			Main.mainWindow.groupsList.add(indexOfTempGroopInArrayList, tempGroup);
			ListWriter lw = new ListWriter();
			lw.saveListInFile(Main.mainWindow.groupsList, Main.groupListdat);

			Main.mainWindow.sql.updateGroup(tempGroup.getGroupID(), tempGroup.getGroupName());
		}

	}

}
