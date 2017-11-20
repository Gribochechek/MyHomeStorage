package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import input_output.InstrumentListWriter;
import main.Main;
import objects_For_Items.Instrument;

public class ItemRemovingWindow extends JDialog {
	private Instrument itemToRemove;
	private int indexOfTempGoodInList;

	public ItemRemovingWindow(Frame parent, int id, String nameOfItemtoRemove) {
		setTitle("Removing Item");
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		for (int i = 0; i < Main.mainWindow.items.size(); i++) {
			if (id == Main.mainWindow.items.get(i).getInstrumentID()) {
				itemToRemove = Main.mainWindow.items.get(i);
				indexOfTempGoodInList = i;
			}
		}
		setLocation(parent.getWidth() - getWidth() / 2, 200);
		setSize(450, 185);
		setResizable(false);

		JPanel headerPanel = new JPanel();
		getContentPane().add(headerPanel);

		JLabel lblRemovingItem = new JLabel("Removing Item");
		lblRemovingItem.setFont(new Font("Arial", Font.BOLD, 20));
		headerPanel.add(lblRemovingItem);

		JPanel removingItemPanel = new JPanel();
		getContentPane().add(removingItemPanel);

		JLabel label = new JLabel("Do You really want to remove " + nameOfItemtoRemove + "?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removingItemPanel.add(label);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel);

		JButton btnOk = new JButton("OK");
		buttonPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int tempID;
				for (int i = 0; i < Main.mainWindow.items.size(); i++) {
					tempID = Main.mainWindow.items.get(i).getInstrumentID();
					if (tempID == id) {
						Main.mainWindow.items.remove(Main.mainWindow.items.get(i));
					}
				}

				InstrumentListWriter ilw = new InstrumentListWriter();
				ilw.saveGoodsInFile(Main.mainWindow.items);

				dispose();

				Main.mainWindow.itemsTable.setModel(Main.mainWindow.itemModel);
				Main.mainWindow.itemsTable.updateUI();

			}
		});

		JButton btnNo = new JButton("NO");
		buttonPanel.add(btnNo);
		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
	}

}
