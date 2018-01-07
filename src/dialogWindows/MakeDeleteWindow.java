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

import dialogWindows.GroupDeleteWindow.eHandler;
import input_output.GroupListWriter;
import input_output.InstrumentListWriter;
import input_output.ListWriter;
import main.Main;

public class MakeDeleteWindow extends JDialog {

	eHandler buttonHAndler = new eHandler();

	public JButton btnYes;

	public JButton btnNo;

	private JComboBox cb_MakeName;

	public MakeDeleteWindow() {

		setSize(420, 180);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);

		setTitle("Deleting Make");
		getContentPane().setLayout(new GridLayout(3, 0, 0, 0));

		JPanel label_panel = new JPanel();
		getContentPane().add(label_panel);
		label_panel.setLayout(new GridLayout(2, 4, 0, 0));

		JLabel lblDoYouRealy = new JLabel("Do You realy want to remove Make?");
		lblDoYouRealy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouRealy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_panel.add(lblDoYouRealy);

		cb_MakeName = new JComboBox();
		label_panel.add(cb_MakeName);
		String[] string = Main.mainWindow.getMakeNames();
		cb_MakeName.setModel(new DefaultComboBoxModel(string));

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JLabel lblDoYouRealy_1 = new JLabel("WARNING! ALL PARTS OF THIS MAKE WILL BE REMOVED!");
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
		int tempMakeID = Main.mainWindow.makesList.get(cb_MakeName.getSelectedIndex()).getMakeID();

		for (int i = 0; i < Main.mainWindow.partList.size();) {
			if (Main.mainWindow.partList.get(i).getMakeID() == tempMakeID) {
				if (Main.mainWindow.partList.get(i).getPartImage().exists()) {
					Main.mainWindow.partList.get(i).getPartImage().delete();
				}
				Main.mainWindow.partList.remove(i);
			} else
				i++;

		}
		Main.mainWindow.makesList.remove(cb_MakeName.getSelectedIndex());
		ListWriter lw = new ListWriter();
		lw.saveListInFile(Main.mainWindow.makesList, Main.makesListdat);
		lw.saveListInFile(Main.mainWindow.modelList, Main.modelListdat);
		lw.saveListInFile(Main.mainWindow.partList, Main.partListdat);

		Main.mainWindow.refreshComboBoxes();
		Main.mainWindow.sql.removeItem(3, tempMakeID);

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
