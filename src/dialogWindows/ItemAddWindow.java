package dialogWindows;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import input_output.InstrumentListWriter;
import main.Main;
import objects_For_Items.Instrument;

public class ItemAddWindow extends JDialog {

	JPanel jp_titlePanel, jp_fieldPanel;
	protected JTextField tf_Name;
	protected JTextField tf_Desc;
	protected JTextField tf_Maker;
	protected JTextField tf_Unit;
	protected JTextField tf_Quantity;
	protected JTextField tf_StoragePlace;
	JButton btnOk;
	JButton btnCancel;
	JButton btnAddImage;
	JButton btnShowPic;

	private JLabel lblGroup;
	public JComboBox comboBoxGroup;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblMaker;
	protected JLabel lblHeader;

	eHandler available_handler = new eHandler();

	private Instrument item;
	File itemImage;
	File itemImageSaving;

	InstrumentListWriter ilr = new InstrumentListWriter();

	public ItemAddWindow(Frame parent) {

		super(parent, true);
		setModal(false);
		setTitle("Adding item");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setLocation(parent.getWidth() - getWidth() / 2, 200);
		setSize(370, 400);
		setResizable(false);

		jp_titlePanel = new JPanel();
		jp_titlePanel.setBounds(0, 0, 364, 36);
		getContentPane().setLayout(null);
		getContentPane().add(jp_titlePanel);

		lblHeader = new JLabel("Adding item");
		lblHeader.setFont(new Font("Arial Black", Font.BOLD, 14));
		jp_titlePanel.add(lblHeader);
		jp_fieldPanel = new JPanel();
		jp_fieldPanel.setBounds(0, 277, 344, -241);
		getContentPane().add(jp_fieldPanel);

		btnOk = new JButton("OK");
		btnOk.setBounds(48, 327, 97, 25);
		getContentPane().add(btnOk);
		btnOk.addActionListener(available_handler);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(220, 327, 97, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(available_handler);

		lblGroup = new JLabel("Group");
		lblGroup.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGroup.setBounds(20, 47, 65, 25);
		getContentPane().add(lblGroup);

		comboBoxGroup = new JComboBox();
		String[] string = Main.mainWindow.getGroupsNames();
		comboBoxGroup.setModel(new DefaultComboBoxModel(string));
		comboBoxGroup.setBounds(146, 50, 184, 20);
		comboBoxGroup.addActionListener(available_handler);
		getContentPane().add(comboBoxGroup);

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(20, 87, 87, 16);
		getContentPane().add(lblName);

		tf_Name = new JTextField();
		tf_Name.setBounds(146, 85, 184, 22);
		getContentPane().add(tf_Name);
		tf_Name.setColumns(10);

		lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(20, 118, 87, 16);
		getContentPane().add(lblDescription);

		tf_Desc = new JTextField();
		tf_Desc.setColumns(10);
		tf_Desc.setBounds(146, 116, 184, 22);
		getContentPane().add(tf_Desc);

		lblMaker = new JLabel("Maker");
		lblMaker.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaker.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaker.setBounds(20, 149, 87, 16);
		getContentPane().add(lblMaker);

		tf_Maker = new JTextField();
		tf_Maker.setColumns(10);
		tf_Maker.setBounds(146, 147, 184, 22);
		getContentPane().add(tf_Maker);

		JLabel lbl_Unit = new JLabel("Unit");
		lbl_Unit.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Unit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Unit.setBounds(20, 180, 87, 16);
		getContentPane().add(lbl_Unit);

		tf_Unit = new JTextField();
		tf_Unit.setColumns(10);
		tf_Unit.setBounds(146, 178, 184, 22);
		getContentPane().add(tf_Unit);

		JLabel lbl_Quantity = new JLabel("Quantity");
		lbl_Quantity.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Quantity.setBounds(20, 211, 87, 16);
		getContentPane().add(lbl_Quantity);

		tf_Quantity = new JTextField();
		tf_Quantity.setColumns(10);
		tf_Quantity.setBounds(146, 209, 184, 22);
		getContentPane().add(tf_Quantity);

		JLabel lbl_StoragePlace = new JLabel("Storage place");
		lbl_StoragePlace.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_StoragePlace.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_StoragePlace.setBounds(20, 240, 111, 28);
		getContentPane().add(lbl_StoragePlace);

		tf_StoragePlace = new JTextField();
		tf_StoragePlace.setColumns(10);
		tf_StoragePlace.setBounds(146, 244, 184, 24);
		getContentPane().add(tf_StoragePlace);

		btnAddImage = new JButton("Add Image");
		btnAddImage.setBounds(48, 281, 97, 25);
		btnAddImage.addActionListener(available_handler);
		getContentPane().add(btnAddImage);

		btnShowPic = new JButton("Show Pic");
		btnShowPic.setBounds(220, 277, 97, 25);
		btnShowPic.addActionListener(available_handler);
		getContentPane().add(btnShowPic);

	}

	public class eHandler implements ActionListener {

		

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnOk) {
				if (comboBoxGroup.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Group is empty");
					return;
				}

				if (tf_Name.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Name format");
					return;
				}
				if (tf_Quantity.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Quantity format");
					return;
				}
				if (tf_Unit.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Unit format");
					return;
				}

				else
					setResult();
				Main.mainWindow.itemsTable.updateUI();
				dispose();
			} else if (e.getSource() == btnCancel) {
				dispose();
			}

			if (e.getSource() == btnAddImage) {
				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					itemImage = fileopen.getSelectedFile();
					try {
						BufferedImage input = ImageIO.read(itemImage);
						itemImageSaving = new File(Main.imageSaveFolder +"/"+tf_Name.getText().trim() + ".jpg");
						ImageIO.write(input, "JPG", itemImageSaving);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				/*
				 * else if (e.getSource() == comboBoxGroup) { String[] string =
				 * Main.mainWindow.getGroupsNames(); comboBoxGroup.setModel(new
				 * DefaultComboBoxModel(string)); }
				 */
			}
			
			
			if(e.getSource()==btnShowPic) {
				Desktop desktop = null;
				if (Desktop.isDesktopSupported()) {
				    desktop = Desktop.getDesktop();
				}
				try {
				    desktop.open(itemImageSaving);
				} catch (IOException ioe) {
				    ioe.printStackTrace();
				}
			}

		}

		private void setResult() {
			String temp_str = tf_Name.getText().trim();
			for (int i = 0; i < Main.mainWindow.items.size(); i++) {
				Instrument goods = Main.mainWindow.items.get(i);
				String str2 = goods.getName();
				if (temp_str.toLowerCase().equals(str2.toLowerCase())) {
					JOptionPane.showMessageDialog(null, "Such entry already exists!");
					Main.mainWindow.itemsTable.setRowSelectionInterval(i, i); // highlight the row with the same name
					return;
				}
			}

			int idOfNewProduct = 0;
			if (Main.mainWindow.items.size() == 0)
				idOfNewProduct = 1;
			else {
				for (Instrument g : Main.mainWindow.items) {
					if (g.getInstrumentID() > idOfNewProduct)
						idOfNewProduct = g.getInstrumentID();
				}
				idOfNewProduct++;
			}

			String quantity = tf_Quantity.getText().trim();

			int groupID = Main.mainWindow.groupsList.get(comboBoxGroup.getSelectedIndex()).getGroupID();

			item = new Instrument(idOfNewProduct, groupID, tf_Name.getText().trim(), tf_Desc.getText().trim(),
					tf_Maker.getText().trim(), tf_Unit.getText().trim(), Double.parseDouble(quantity),
					tf_StoragePlace.getText().trim(), itemImageSaving);

			Main.mainWindow.items.add(item);
			ilr.saveGoodsInFile(Main.mainWindow.items);
			Main.mainWindow.sql.addGoods(item);

		}
	}
}
