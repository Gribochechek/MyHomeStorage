package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import dialogWindows.GroupAddWindow;
import dialogWindows.GroupDeleteWindow;
import dialogWindows.GroupEditWindow;
import dialogWindows.ItemAddWindow;
import dialogWindows.ItemEditWindow;
import dialogWindows.ItemRemovingWindow;
import dialogWindows.SearchForm;
import main.Main;

public class ListenerForButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Main.mainWindow.bAddItem) {
			ItemAddWindow dialog = new ItemAddWindow(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}

		if (e.getSource() == Main.mainWindow.btnAddGroup) {
			GroupAddWindow dialog = new GroupAddWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}
		if (e.getSource() == Main.mainWindow.btnDeleteGroup) {
			GroupDeleteWindow dialog = new GroupDeleteWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		if (e.getSource() == Main.mainWindow.btnEditGroup) {
			GroupEditWindow dialog = new GroupEditWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}
		if (e.getSource() == Main.mainWindow.btnEditItem) {

			try {
				int idOfeditingItem = (int) Main.mainWindow.itemsTable
						.getValueAt(Main.mainWindow.itemsTable.getSelectedRow(), 0);
				ItemEditWindow dialog = new ItemEditWindow(Main.mainWindow, idOfeditingItem);
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No Item Selected");
				return;
			}
		}

		if (e.getSource() == Main.mainWindow.btnDeleteItem) {
			try {
				int idOfRemovingProduct = (int) Main.mainWindow.itemsTable
						.getValueAt(Main.mainWindow.itemsTable.getSelectedRow(), 0);
				String editingItemName = (String) Main.mainWindow.itemsTable
						.getValueAt(Main.mainWindow.itemsTable.getSelectedRow(), 1);
				ItemRemovingWindow dialog = new ItemRemovingWindow(Main.mainWindow, idOfRemovingProduct,
						editingItemName);
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "No Item Selected");
				return;
			}

		}
		if (e.getSource()== Main.mainWindow.btnSearch) {
			SearchForm dialog = new SearchForm(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
}
