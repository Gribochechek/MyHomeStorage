package dialogWindows;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dialogWindows.MakeAddWindow.eHandler;
import input_output.ListWriter;
import main.Main;
import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.AutomotiveParts_Model;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ModelAddWindow extends JDialog {

	AutomotiveParts_Model model;
	ListWriter lwr = new ListWriter();
	private eHandler buttonHandler = new eHandler();

	private JButton modelAdding_OK;
	private JButton modelAdding_cancel;
	private JTextField tf_ModelName;
	private JComboBox makeName_comboBox;

	public ModelAddWindow() {

		setResizable(false);

		setSize(340, 243);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);

		setTitle("Adding new Model");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel title_panel = new JPanel();
		getContentPane().add(title_panel);

		JLabel lblAddingNewMake = new JLabel("Adding new Make");
		lblAddingNewMake.setFont(new Font("Tahoma", Font.BOLD, 20));
		title_panel.add(lblAddingNewMake);

		JPanel MakePanel = new JPanel();
		getContentPane().add(MakePanel);
		MakePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel lblMakeAdding = new JLabel("Make:");
		lblMakeAdding.setHorizontalAlignment(SwingConstants.LEFT);
		lblMakeAdding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MakePanel.add(lblMakeAdding);

		makeName_comboBox = new JComboBox();
		String[] string = Main.mainWindow.getMakeNames();
		makeName_comboBox.setModel(new DefaultComboBoxModel(string));
		MakePanel.add(makeName_comboBox);

		JPanel ModelPanel = new JPanel();
		getContentPane().add(ModelPanel);
		ModelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel model_LAbel = new JLabel("Model:");
		model_LAbel.setHorizontalAlignment(SwingConstants.LEFT);
		model_LAbel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ModelPanel.add(model_LAbel);

		tf_ModelName = new JTextField();
		ModelPanel.add(tf_ModelName);
		tf_ModelName.setColumns(10);

		JPanel button_panel = new JPanel();
		getContentPane().add(button_panel);

		modelAdding_OK = new JButton("ADD");
		button_panel.add(modelAdding_OK);
		modelAdding_OK.addActionListener(buttonHandler);

		modelAdding_cancel = new JButton("CANCEL");
		button_panel.add(modelAdding_cancel);
		modelAdding_cancel.addActionListener(buttonHandler);
	}

	private int nextModelID() {
		int newModelId = Main.mainWindow.modelList.get(Main.mainWindow.modelList.size() - 1).getModelID() + 1;
		return newModelId;

	}

	public void setResult() {
		String temp_str = tf_ModelName.getText().toLowerCase().trim();
		int tempMakeID = Main.mainWindow.makesList.get(makeName_comboBox.getSelectedIndex()).getMakeID();
		for (int i = 0; i < Main.mainWindow.modelList.size(); i++) {
			AutomotiveParts_Model model = Main.mainWindow.modelList.get(i);
			String str2 = model.getModelName().toLowerCase();
			if (temp_str.equals(str2)) {
				JOptionPane.showMessageDialog(Main.mainWindow, "Such entry already exists!");

				return;
			}
		}

		if (Main.mainWindow.modelList.size() > 0) {
			model = new AutomotiveParts_Model(nextModelID(), tempMakeID, tf_ModelName.getText().trim());
		} else {
			model = new AutomotiveParts_Model(1, tempMakeID, tf_ModelName.getText().trim());
		}

		Main.mainWindow.modelList.add(model);
		Main.mainWindow.sql.addModel(model);
		lwr.saveListInFile(Main.mainWindow.modelList, Main.modelListdat);
		Main.mainWindow.refreshComboBoxes();

	}

	class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == modelAdding_OK) {
				if (tf_ModelName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(Main.mainWindow, "Invalid Name format");
					return;
				}

				else
					setResult();

				dispose();
			}

			if (e.getSource() == modelAdding_cancel) {

				dispose();

			}

		}

	}

}
