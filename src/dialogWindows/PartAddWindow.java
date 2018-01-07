package dialogWindows;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import input_output.ListWriter;
import main.Main;
import objects_For_Items.AutomotiveParts_Model;
import objects_For_Items.AutomotiveParts_Part;

public class PartAddWindow extends JFrame {

	eHandler available_handler = new eHandler();

	private AutomotiveParts_Part part;
	File partImage;
	File partImageSaving;
	File noImage = new File(Main.imageSaveFolder + "/NoImageAvailable.jpg");
	private JTextField textField_partName;
	private JTextField textField_catID;
	private JTextField textField_Description;
	private JTextField textField_Maker;
	private JTextField textField_quantity;
	private JTextField textField_StoragePlace;
	private JComboBox comboBox_Make;
	private JComboBox comboBox_model;
	private JComboBox comboBox_Side;
	private JButton btnAddImage;
	private JButton btnShowImage;
	private JButton btnOk;
	private JButton btnCancel;

	public PartAddWindow() {

		setSize(370, 633);
		setResizable(false);
		setTitle("Adding part");
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel header_panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, header_panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, header_panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, header_panel, 30, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, header_panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(header_panel);

		JLabel lblAddingPart = new JLabel("Adding part");
		lblAddingPart.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddingPart.setFont(new Font("Tahoma", Font.BOLD, 20));
		header_panel.add(lblAddingPart);

		JPanel field_panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, field_panel, 0, SpringLayout.SOUTH, header_panel);
		springLayout.putConstraint(SpringLayout.WEST, field_panel, 20, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, field_panel, -100, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, field_panel, -20, SpringLayout.EAST, header_panel);
		getContentPane().add(field_panel);
		field_panel.setLayout(new GridLayout(0, 2, 10, 25));

		JLabel lblMake = new JLabel("Make");
		lblMake.setHorizontalAlignment(SwingConstants.LEFT);
		lblMake.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblMake);

		comboBox_Make = new JComboBox();
		String[] string_makes = Main.mainWindow.getMakeNames();
		comboBox_Make.setModel(new DefaultComboBoxModel(string_makes));
		lblMake.setLabelFor(comboBox_Make);
		field_panel.add(comboBox_Make);
		comboBox_Make.addActionListener(available_handler);

		JLabel lblModel = new JLabel("Model");
		lblModel.setHorizontalAlignment(SwingConstants.LEFT);
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblModel);

		comboBox_model = new JComboBox();

		ArrayList<String> modelsList = new ArrayList<>();
		int tempMakeID = Main.mainWindow.makesList.get(comboBox_Make.getSelectedIndex()).getMakeID();

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

		comboBox_model.setModel(new DefaultComboBoxModel(string_models));
		field_panel.add(comboBox_model);

		JLabel lblPartname = new JLabel("PartName");
		lblPartname.setHorizontalAlignment(SwingConstants.LEFT);
		lblPartname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblPartname);

		textField_partName = new JTextField();
		field_panel.add(textField_partName);
		textField_partName.setColumns(10);

		JLabel lblCatalogid = new JLabel("CatalogID");
		lblCatalogid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblCatalogid);

		textField_catID = new JTextField();
		field_panel.add(textField_catID);
		textField_catID.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblDescription);

		textField_Description = new JTextField();
		field_panel.add(textField_Description);
		textField_Description.setColumns(10);

		JLabel lblSide = new JLabel("Side");
		lblSide.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblSide);

		comboBox_Side = new JComboBox(objects_For_Items.Sides.values());

		field_panel.add(comboBox_Side);

		JLabel lblMaker = new JLabel("Maker");
		lblMaker.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblMaker);

		textField_Maker = new JTextField();
		field_panel.add(textField_Maker);
		textField_Maker.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblQuantity);

		textField_quantity = new JTextField();
		field_panel.add(textField_quantity);
		textField_quantity.setColumns(10);

		JLabel lblStoragePlace = new JLabel("Storage Place");
		lblStoragePlace.setFont(new Font("Tahoma", Font.PLAIN, 17));
		field_panel.add(lblStoragePlace);

		textField_StoragePlace = new JTextField();
		field_panel.add(textField_StoragePlace);
		textField_StoragePlace.setColumns(10);

		JPanel panel_btnPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_btnPanel, 10, SpringLayout.SOUTH, field_panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_btnPanel, 20, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_btnPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_btnPanel, -20, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel_btnPanel);
		panel_btnPanel.setLayout(new GridLayout(2, 2, 10, 10));

		btnAddImage = new JButton("Add Image");
		btnAddImage.addActionListener(available_handler);
		panel_btnPanel.add(btnAddImage);

		btnShowImage = new JButton("Show Image");
		btnShowImage.addActionListener(available_handler);
		panel_btnPanel.add(btnShowImage);

		btnOk = new JButton("OK");
		btnOk.addActionListener(available_handler);
		panel_btnPanel.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(available_handler);
		panel_btnPanel.add(btnCancel);

	}

	public class eHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e1) {

			if (e1.getSource() == btnOk) {
				if (comboBox_Make.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Make is empty");
					return;
				}
				if (comboBox_model.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Model is empty");
					return;
				}

				if (textField_partName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Name");
					return;
				}
				if (textField_catID.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid CatalogID");
					return;
				}
				if (textField_Description.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Description");
					return;
				}
				if (comboBox_Side.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Side is empty");
					return;
				}
				if (textField_Maker.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid CatalogID");
					return;
				}

				if (textField_quantity.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid CatalogID");
					return;
				}
				if (textField_StoragePlace.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid CatalogID");
					return;
				}
				else {
					setResult();
					Main.mainWindow.automotivePartsTable.updateUI();
					dispose();
				}

			}

			

			if (e1.getSource() == btnCancel) {
				dispose();
			}

			if (e1.getSource() == btnAddImage) {
				JFileChooser fileopen = new JFileChooser();
				ImagePreviewForFileChooser preview = new ImagePreviewForFileChooser();
				fileopen.setAccessory(preview);
				fileopen.addPropertyChangeListener(preview);
				int ret = fileopen.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					partImage = fileopen.getSelectedFile();
					try {
						BufferedImage input = ImageIO.read(partImage);
						partImageSaving = new File(
								Main.imageSaveFolder + "/" + textField_partName.getText().trim() + ".jpg");
						ImageIO.write(input, "JPG", partImageSaving);

					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}

			}

			if (e1.getSource() == btnShowImage) {
				Desktop desktop = null;
				if (Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
				}
				try {
					desktop.open(partImageSaving);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "No image added");
				}
			}

			if (e1.getSource() == comboBox_Make) {
				ArrayList<String> modelsList = new ArrayList<>();
				int tempMakeID = Main.mainWindow.makesList.get(comboBox_Make.getSelectedIndex()).getMakeID();

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
				comboBox_model.setModel(new DefaultComboBoxModel(string_models));
			}

		}

		private void setResult() {
			String temp_str = textField_partName.getText().trim();
			for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
				AutomotiveParts_Part part = Main.mainWindow.partList.get(i);
				String str2 = part.getPartName();
				if (temp_str.toLowerCase().equals(str2.toLowerCase())) {
					JOptionPane.showMessageDialog(null, "Such entry already exists!");
					Main.mainWindow.itemsTable.setRowSelectionInterval(i, i); // highlight the row with the same name
					return;
				}
			}

			int idOfNewProduct = 0;
			if (Main.mainWindow.partList.size() == 0)
				idOfNewProduct = 1;
			else {
				for (AutomotiveParts_Part p : Main.mainWindow.partList) {
					if (p.getPartID() > idOfNewProduct)
						idOfNewProduct = p.getPartID();
				}
				idOfNewProduct++;
			}

			String quantity = textField_quantity.getText().trim();

			int makeID = Main.mainWindow.makesList.get(comboBox_Make.getSelectedIndex()).getMakeID();
			int modelID = Main.mainWindow.modelList.get(comboBox_model.getSelectedIndex()).getModelID();

			if (partImageSaving == null) {
				try {
					BufferedImage input = ImageIO.read(noImage);
					partImageSaving = new File(
							Main.imageSaveFolder + "/" + textField_partName.getText().trim() + ".jpg");
					ImageIO.write(input, "JPG", partImageSaving);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			part = new AutomotiveParts_Part(idOfNewProduct, makeID, modelID, textField_partName.getText().trim(),
					textField_catID.getText().trim(), textField_Description.getText().trim(),
					comboBox_Side.getSelectedItem().toString().trim(), textField_Maker.getText().trim(), quantity,
					textField_StoragePlace.getText().trim(), partImageSaving);

			Main.mainWindow.partList.add(part);
			ListWriter lw = new ListWriter();
			lw.saveListInFile(Main.mainWindow.items, Main.partListdat);

			Main.mainWindow.sql.addPart(part);

		}

	}

}
