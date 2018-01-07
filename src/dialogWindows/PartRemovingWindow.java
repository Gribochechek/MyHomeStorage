package dialogWindows;

import java.awt.Frame;

import javax.swing.JDialog;

import main.Main;
import objects_For_Items.AutomotiveParts_Part;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import input_output.ListWriter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class PartRemovingWindow extends JDialog {

	private AutomotiveParts_Part partToRemove;
	private int indexOfTempPartInList;

	public PartRemovingWindow(Frame parent, int id, String nameOfParttoRemove) {
		setTitle("Removing Part");

		for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
			if (id == Main.mainWindow.partList.get(i).getPartID()) {
				partToRemove = Main.mainWindow.partList.get(i);
				indexOfTempPartInList = i;
			}
		}

		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setSize(450, 185);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(3, 0, 0, 0));

		JPanel headerPanel = new JPanel();
		getContentPane().add(headerPanel);

		JLabel lblRemovingPart = new JLabel("Removing Part");
		lblRemovingPart.setFont(new Font("Tahoma", Font.BOLD, 20));
		headerPanel.add(lblRemovingPart);

		JPanel questionPanel = new JPanel();
		getContentPane().add(questionPanel);

		JLabel lblDoYouReally = new JLabel("Do you really want to remove " + nameOfParttoRemove + "?");
		lblDoYouReally.setFont(new Font("Tahoma", Font.PLAIN, 16));
		questionPanel.add(lblDoYouReally);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel);

		JButton btnYes = new JButton("YES");
		buttonPanel.add(btnYes);
		btnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int tempID = 0;
				for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
					tempID = Main.mainWindow.partList.get(i).getPartID();
					if (tempID == id) {

						if (Main.mainWindow.partList.get(i).getPartImage().exists()) {
							Main.mainWindow.partList.get(i).getPartImage().delete();
						}
						Main.mainWindow.partList.remove(Main.mainWindow.partList.get(i));

					}
				}

				ListWriter lw = new ListWriter();
				lw.saveListInFile(Main.mainWindow.partList, Main.partListdat);

				Main.mainWindow.sql.removeItem(5, tempID);
				dispose();

				Main.mainWindow.automotivePartsTable.setModel(Main.mainWindow.automotivePartsTableModel);
				Main.mainWindow.automotivePartsTable.updateUI();

			}
		});

		JButton btnNo = new JButton("NO");
		buttonPanel.add(btnNo);

	}

}
