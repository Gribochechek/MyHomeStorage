package dialogWindows;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dialogWindows.MakeEditWindow.Listener;
import input_output.ListWriter;
import main.Main;
import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.AutomotiveParts_Model;

public class ModelEditWindow extends JFrame {

	private JTextField ModelNameTextField;
	private JComboBox comboBox_MakeChooser;
	private JLabel lblModelName;
	private JButton btnOk;
	private JButton btnCancel;

	String oldModelName;
	int indexOfTempModelInArrayList = 0;
	AutomotiveParts_Model tempModel;

	ListWriter mlw = new ListWriter();
	Listener modelEditWindowListener = new Listener();
	private JPanel model_chooser_panel;
	private JLabel lblChooseModel;
	private JComboBox cb_ModelName;
	private int tempMakeID;

	public ModelEditWindow() {

		setSize(420, 256);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setTitle("EditingModel");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel make_Chooser_panel = new JPanel();
		getContentPane().add(make_Chooser_panel);
		make_Chooser_panel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblMakeChooser = new JLabel("Choose Make");
		lblMakeChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMakeChooser.setHorizontalAlignment(SwingConstants.CENTER);
		make_Chooser_panel.add(lblMakeChooser);

		comboBox_MakeChooser = new JComboBox();
		comboBox_MakeChooser.setMaximumRowCount(10);
		String[] string = Main.mainWindow.getMakeNames();
		comboBox_MakeChooser.setModel(new DefaultComboBoxModel(string));
		comboBox_MakeChooser.addActionListener(modelEditWindowListener);
		comboBox_MakeChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> modelsList = new ArrayList<>();
				int tempMakeID = Main.mainWindow.makesList.get(comboBox_MakeChooser.getSelectedIndex()).getMakeID();

				for (int i = 0; i < Main.mainWindow.modelList.size(); i++) {
					AutomotiveParts_Model model = Main.mainWindow.modelList.get(i);
					if (model.getMakeID() == tempMakeID) {
						modelsList.add(model.getModelName());
					}
				}
				String[] string_models = new String[modelsList.size()];
				for (int i = 0; i < modelsList.size(); i++) {
					string_models[i] = modelsList.get(i);
				}

				cb_ModelName.setModel(new DefaultComboBoxModel(string_models));

			}
		});
		make_Chooser_panel.add(comboBox_MakeChooser);

		model_chooser_panel = new JPanel();
		getContentPane().add(model_chooser_panel);
		model_chooser_panel.setLayout(new GridLayout(2, 4, 0, 0));

		lblChooseModel = new JLabel("Choose Model");
		lblChooseModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseModel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		model_chooser_panel.add(lblChooseModel);

		cb_ModelName = new JComboBox();
		
		ArrayList<String> modelsList = new ArrayList<>();
		int tempMakeID = Main.mainWindow.makesList.get(comboBox_MakeChooser.getSelectedIndex()).getMakeID();

		for (int i = 0; i < Main.mainWindow.modelList.size(); i++) {
			AutomotiveParts_Model model = Main.mainWindow.modelList.get(i);
			if (model.getMakeID() == tempMakeID) {
				modelsList.add(model.getModelName());
			}
		}
		String[] string_models = new String[modelsList.size()];
		for (int i = 0; i < modelsList.size(); i++) {
			string_models[i] = modelsList.get(i);
		}
		
		cb_ModelName.setModel(new DefaultComboBoxModel(string_models));
		cb_ModelName.addActionListener(modelEditWindowListener);
		model_chooser_panel.add(cb_ModelName);

		JPanel model_editing_panel = new JPanel();
		getContentPane().add(model_editing_panel);

		lblModelName = new JLabel("Model Name");
		lblModelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		model_editing_panel.add(lblModelName);

		ModelNameTextField = new JTextField();
		model_editing_panel.add(ModelNameTextField);
		ModelNameTextField.setText(Main.mainWindow.modelList.get(0).getModelName());
		ModelNameTextField.setColumns(10);
		oldModelName = ModelNameTextField.getText().toLowerCase();

		JPanel btn_panel = new JPanel();
		getContentPane().add(btn_panel);

		btnOk = new JButton("OK");
		btnOk.addActionListener(modelEditWindowListener);
		btn_panel.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(modelEditWindowListener);
		btn_panel.add(btnCancel);

	}

	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cb_ModelName) {
				ModelNameTextField
						.setText(Main.mainWindow.modelList.get(cb_ModelName.getSelectedIndex()).getModelName());
				oldModelName = ModelNameTextField.getText().toLowerCase();
				indexOfTempModelInArrayList = cb_ModelName.getSelectedIndex();

			} else if (e.getSource() == btnOk) {
				setResult();
				Main.mainWindow.refreshComboBoxes();
				dispose();

			} else if (e.getSource() == btnCancel) {
				dispose();
			}

		}

		private void setResult() {
			String temp_str = ModelNameTextField.getText().trim();
			if (!oldModelName.equals(temp_str.toLowerCase())) {
				for (AutomotiveParts_Model model : Main.mainWindow.modelList) {
					if (model.getModelName().toLowerCase().equals(temp_str.toLowerCase())) {
						JOptionPane.showMessageDialog(null, "Such entry already exists!");
						return;
					}
				}

			}

			tempMakeID = Main.mainWindow.makesList.get(comboBox_MakeChooser.getSelectedIndex()).getMakeID();
			tempModel = new AutomotiveParts_Model(
					(Main.mainWindow.modelList.get(indexOfTempModelInArrayList).getModelID()), tempMakeID,
					ModelNameTextField.getText());
			Main.mainWindow.modelList.remove(indexOfTempModelInArrayList);
			Main.mainWindow.modelList.add(indexOfTempModelInArrayList, tempModel);
			mlw.saveListInFile(Main.mainWindow.modelList, Main.modelListdat);
			Main.mainWindow.sql.updateModel(tempModel);

		}

	}

}
