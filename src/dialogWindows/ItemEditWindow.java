package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import input_output.InstrumentListWriter;
import main.Main;
import objects_For_Items.Instrument;

public class ItemEditWindow extends JDialog {

	JPanel jp_titlePanel, jp_fieldPanel;
	protected JTextField tf_Name;
	protected JTextField tf_Desc;
	protected JTextField tf_Maker;
	protected JTextField tf_Unit;
	protected JTextField tf_Quantity;
	protected JTextField tf_StoragePlace;
	JButton btnOk;
	JButton btnCancel;

	private JLabel lblGroup;
	public JComboBox comboBoxGroup;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblMaker;
	protected JLabel lblHeader;

	private Instrument editingItem;

	InstrumentListWriter ilw = new InstrumentListWriter();
	private int indexOfTempGoodInList;
	private String old_item_name;

	public ItemEditWindow(Frame parent, int id) {
		super(parent, true);
		setModal(false);
		setTitle("Editing item");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		for (int i = 0; i < Main.mainWindow.items.size(); i++) {
			if (id == Main.mainWindow.items.get(i).getInstrumentID()) {
				editingItem = Main.mainWindow.items.get(i);
				indexOfTempGoodInList = i;
			}
		}

		setLocation(parent.getWidth() - getWidth() / 2, 200);
		setSize(370, 365);
		setResizable(false);

		jp_titlePanel = new JPanel();
		jp_titlePanel.setBounds(0, 0, 364, 36);
		getContentPane().setLayout(null);
		getContentPane().add(jp_titlePanel);

		lblHeader = new JLabel("Editing item");
		lblHeader.setFont(new Font("Arial Black", Font.BOLD, 14));
		jp_titlePanel.add(lblHeader);
		jp_fieldPanel = new JPanel();
		jp_fieldPanel.setBounds(0, 277, 344, -241);
		getContentPane().add(jp_fieldPanel);

		btnOk = new JButton("OK");
		btnOk.setBounds(47, 295, 97, 25);
		getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
				} else
					setResult();
				dispose();

			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(219, 295, 97, 25);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		lblGroup = new JLabel("Group");
		lblGroup.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGroup.setBounds(20, 47, 65, 25);
		getContentPane().add(lblGroup);

		comboBoxGroup = new JComboBox();
		String[] string = Main.mainWindow.getGroupsNames();
		comboBoxGroup.setModel(new DefaultComboBoxModel(string));
		comboBoxGroup.setBounds(146, 50, 184, 20);

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
		
		comboBoxGroup.setSelectedIndex(editingItem.getGroupID() - 1);

		tf_Name.setText(editingItem.getName());
		tf_Desc.setText(editingItem.getDescription());
		tf_Maker.setText(editingItem.getMaker());
		tf_Unit.setText(editingItem.getUnit());
		tf_Quantity.setText(Double.toString(editingItem.getQuantity()));
		tf_StoragePlace.setText(editingItem.getStoragePlace());

		old_item_name = tf_Name.getText().trim();

	}

	private void setResult() {
		String temp_itemName = tf_Name.getText().trim();

		/*if (!old_item_name.equals(temp_itemName.toLowerCase())) {
			for (Instrument it : Main.mainWindow.items) {
				if (it.getName().toLowerCase().equals(temp_itemName.toLowerCase())) {
					JOptionPane.showMessageDialog(null, "Such entry already exists!");
					return;
				}
			}
		}*/

		editingItem = new Instrument(editingItem.getInstrumentID(), editingItem.getGroupID(), tf_Name.getText().trim(),
				tf_Desc.getText().trim(), tf_Maker.getText().trim(), tf_Unit.getText().trim(),
				Double.parseDouble(tf_Quantity.getText().trim()), tf_StoragePlace.getText().trim());
		Main.mainWindow.items.remove(indexOfTempGoodInList);
		Main.mainWindow.items.add(indexOfTempGoodInList, editingItem);

		ilw.saveGoodsInFile(Main.mainWindow.items);
		Main.mainWindow.itemsTable.updateUI();

	}

}
