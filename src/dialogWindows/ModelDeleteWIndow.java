package dialogWindows;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dialogWindows.MakeDeleteWindow.eHandler;
import input_output.ListWriter;
import main.Main;
import mainWindows.MainWindow;
import objects_For_Items.AutomotiveParts_Model;

import java.awt.FlowLayout;

public class ModelDeleteWIndow extends JDialog {

	eHandler buttonHAndler = new eHandler();

	public JButton btnYes;

	public JButton btnNo;

	private JComboBox cb_ModelName;

	private JComboBox makeName_comboBox;

	public ModelDeleteWIndow() {

		setSize(420, 269);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);

		setTitle("Deleting Model");
		getContentPane().setLayout(new GridLayout(4, 0, 0, 0));

		JPanel make_chooser = new JPanel();
		getContentPane().add(make_chooser);
		make_chooser.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label = new JLabel("Make:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		make_chooser.add(label);

		makeName_comboBox = new JComboBox();
		String[] string_makes = Main.mainWindow.getMakeNames();
		makeName_comboBox.setModel(new DefaultComboBoxModel(string_makes));
		make_chooser.add(makeName_comboBox);

		makeName_comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> modelsList = new ArrayList<>();
				int tempMakeID = Main.mainWindow.makesList.get(makeName_comboBox.getSelectedIndex()).getMakeID();

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

		JPanel label_panel = new JPanel();
		getContentPane().add(label_panel);
		label_panel.setLayout(new GridLayout(2, 4, 0, 0));

		JLabel lblDoYouRealy = new JLabel("Do You realy want to remove Model?");
		lblDoYouRealy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouRealy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_panel.add(lblDoYouRealy);

		cb_ModelName = new JComboBox();
		label_panel.add(cb_ModelName);
		ArrayList<String> modelsList = new ArrayList<>();
		int tempMakeID = Main.mainWindow.makesList.get(makeName_comboBox.getSelectedIndex()).getMakeID();

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

		JPanel warning_panel = new JPanel();
		getContentPane().add(warning_panel);

		JLabel lblDoYouRealy_1 = new JLabel("WARNING! ALL PARTS OF THIS MODEL WILL BE REMOVED!");
		lblDoYouRealy_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		warning_panel.add(lblDoYouRealy_1);

		JPanel btn_panel = new JPanel();
		getContentPane().add(btn_panel);

		btnYes = new JButton("YES");
		btn_panel.add(btnYes);
		btnYes.addActionListener(buttonHAndler);

		btnNo = new JButton("NO");
		btnNo.addActionListener(buttonHAndler);
		btn_panel.add(btnNo);

	}

	public void setResult() {
		int tempModelID = Main.mainWindow.modelList.get(cb_ModelName.getSelectedIndex()).getModelID();

		for (int i = 0; i < Main.mainWindow.partList.size();) {
			if (Main.mainWindow.partList.get(i).getModelID() == tempModelID) {
				if (Main.mainWindow.partList.get(i).getPartImage().exists()) {
					Main.mainWindow.partList.get(i).getPartImage().delete();
				}
				Main.mainWindow.partList.remove(i);
			} else
				i++;

		}
		Main.mainWindow.modelList.remove(cb_ModelName.getSelectedIndex());
		ListWriter lw = new ListWriter();

		lw.saveListInFile(Main.mainWindow.modelList, Main.modelListdat);
		lw.saveListInFile(Main.mainWindow.partList, Main.partListdat);

		Main.mainWindow.refreshComboBoxes();
		Main.mainWindow.sql.removeItem(4, tempModelID);

	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnYes) {
				setResult();
				Main.mainWindow.automotivePartsTable.updateUI();
				dispose();
			} else if (e.getSource() == btnNo)
				dispose();

		}

	}

}
