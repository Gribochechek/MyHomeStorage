package dialogWindows;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import input_output.GroupListWriter;
import input_output.InstrumentListWriter;
import main.Main;

public class GroupDeleteWindow extends JDialog {

	eHandler buttonHAndler = new eHandler();

	public JButton btnYes;

	public JButton btnNo;

	private JComboBox cb_groupName;

	public GroupDeleteWindow() {

		setSize(420, 180);
		setLocation(Main.mainWindow.getWidth() - getWidth() / 2, 200);

		setTitle("DeletingGroup");
		getContentPane().setLayout(new GridLayout(3, 0, 0, 0));

		JPanel label_panel = new JPanel();
		getContentPane().add(label_panel);
		label_panel.setLayout(new GridLayout(2, 4, 0, 0));

		JLabel lblDoYouRealy = new JLabel("Do You realy want to remove group of items?");
		lblDoYouRealy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouRealy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_panel.add(lblDoYouRealy);

		cb_groupName = new JComboBox();
		label_panel.add(cb_groupName);
		String[] string = Main.mainWindow.getGroupsNames();
		cb_groupName.setModel(new DefaultComboBoxModel(string));

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JLabel lblDoYouRealy_1 = new JLabel("WARNING! ALL ITEMS FROM THIS GROUP WILL BE REMOVED!");
		lblDoYouRealy_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblDoYouRealy_1);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		btnYes = new JButton("YES");
		panel.add(btnYes);
		btnYes.addActionListener(buttonHAndler);

		btnNo = new JButton("NO");
		btnNo.addActionListener(buttonHAndler);
		panel.add(btnNo);

	}

	public void setResult() {
		int tempGroupID = Main.mainWindow.groupsList.get(cb_groupName.getSelectedIndex()).getGroupID();

		for (int i = 0; i < Main.mainWindow.items.size();) {
			if (Main.mainWindow.items.get(i).getGroupID() == tempGroupID) {
				Main.mainWindow.deletedItems.add(Main.mainWindow.items.get(i));
				Main.mainWindow.items.remove(i);
			} else
				i++;

		}
		Main.mainWindow.groupsList.remove(cb_groupName.getSelectedIndex());
		GroupListWriter gw = new GroupListWriter();
		gw.saveGroupsInFile(Main.mainWindow.groupsList);
		Main.mainWindow.refreshComboBoxes();
		
		InstrumentListWriter ilw = new InstrumentListWriter();
		ilw.saveGoodsInFile(Main.mainWindow.items);
		
	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnYes) {
				setResult();
				Main.mainWindow.itemsTable.updateUI();
				dispose();
			} else if (e.getSource() == btnNo)
				dispose();

		}

	}

}
