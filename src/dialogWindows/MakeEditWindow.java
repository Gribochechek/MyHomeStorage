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

import input_output.ListWriter;
import main.Main;
import objects_For_Items.AutomotiveParts_Make;

public class MakeEditWindow extends JFrame {

	private JTextField MakeNameTextField;
	private JComboBox comboBox_MakeChooser;
	private JLabel lblMakeName;
	private JButton btnOk;
	private JButton btnCancel;

	String oldMakeName;
	int indexOfTempMakeInArrayList = 0;
	AutomotiveParts_Make tempMake;

	ListWriter mlw = new ListWriter();
	Listener makeEditWindowListener = new Listener();

	public MakeEditWindow() {

		setSize(420, 188);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setTitle("EditingMake");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		getContentPane().add(panel);

		JLabel lblMakeChooser = new JLabel("Choose Make");
		lblMakeChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMakeChooser.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblMakeChooser);

		comboBox_MakeChooser = new JComboBox();
		comboBox_MakeChooser.setMaximumRowCount(10);
		String[] string = Main.mainWindow.getMakeNames();
		comboBox_MakeChooser.setModel(new DefaultComboBoxModel(string));
		comboBox_MakeChooser.addActionListener(makeEditWindowListener);
		panel.add(comboBox_MakeChooser);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		lblMakeName = new JLabel("Make Name");
		lblMakeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblMakeName);

		MakeNameTextField = new JTextField();
		panel_1.add(MakeNameTextField);
		MakeNameTextField.setText(Main.mainWindow.makesList.get(0).getMakeName());
		MakeNameTextField.setColumns(10);
		oldMakeName = MakeNameTextField.getText().toLowerCase();

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);

		btnOk = new JButton("OK");
		btnOk.addActionListener(makeEditWindowListener);
		panel_2.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(makeEditWindowListener);
		panel_2.add(btnCancel);
	}

	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comboBox_MakeChooser) {
				MakeNameTextField
						.setText(Main.mainWindow.makesList.get(comboBox_MakeChooser.getSelectedIndex()).getMakeName());
				oldMakeName = MakeNameTextField.getText().toLowerCase();
				indexOfTempMakeInArrayList = comboBox_MakeChooser.getSelectedIndex();

			} else if (e.getSource() == btnOk) {
				setResult();
				Main.mainWindow.refreshComboBoxes();
				dispose();

			} else if (e.getSource() == btnCancel) {
				dispose();
			}

		}

		private void setResult() {
			String temp_str = MakeNameTextField.getText().trim();
			if (!oldMakeName.equals(temp_str.toLowerCase())) {
				for (AutomotiveParts_Make make : Main.mainWindow.makesList) {
					if (make.getMakeName().toLowerCase().equals(temp_str.toLowerCase())) {
						JOptionPane.showMessageDialog(null, "Such entry already exists!");
						return;
					}
				}

			}

			tempMake = new AutomotiveParts_Make((Main.mainWindow.makesList.get(indexOfTempMakeInArrayList).getMakeID()),
					MakeNameTextField.getText());
			Main.mainWindow.makesList.remove(indexOfTempMakeInArrayList);
			Main.mainWindow.makesList.add(indexOfTempMakeInArrayList, tempMake);
			mlw.saveListInFile(Main.mainWindow.makesList, Main.makesListdat);
			Main.mainWindow.sql.updateMake(tempMake.getMakeID(), tempMake.getMakeName());

		}

	}

}
