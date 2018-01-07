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
import dialogWindows.MakeAddWindow;
import dialogWindows.MakeDeleteWindow;
import dialogWindows.MakeEditWindow;
import dialogWindows.ModelAddWindow;
import dialogWindows.ModelDeleteWIndow;
import dialogWindows.ModelEditWindow;
import dialogWindows.PartAddWindow;
import dialogWindows.PartRemovingWindow;
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

			} catch (IndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "No Item Selected");
				return;
			}

		}
		if (e.getSource() == Main.mainWindow.btnSearch) {
			SearchForm dialog = new SearchForm(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == Main.mainWindow.btnAddMake) {
			MakeAddWindow dialog = new MakeAddWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}

		if (e.getSource() == Main.mainWindow.btnEditMake) {
			MakeEditWindow dialog = new MakeEditWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == Main.mainWindow.btnDeleteMake) {
			MakeDeleteWindow dialog = new MakeDeleteWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == Main.mainWindow.btnAddModel) {
			ModelAddWindow dialog = new ModelAddWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == Main.mainWindow.btnDeleteModel) {
			ModelDeleteWIndow dialog = new ModelDeleteWIndow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == Main.mainWindow.btnEditModel) {
			ModelEditWindow dialog = new ModelEditWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	 
		if(e.getSource()== Main.mainWindow.btnAddPart) {
			PartAddWindow dialog = new PartAddWindow();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		
		if (e.getSource() == Main.mainWindow.btnDeletePart) {
			try {
				int idOfRemovingProduct = (int) Main.mainWindow.automotivePartsTable
						.getValueAt(Main.mainWindow.automotivePartsTable.getSelectedRow(), 0);
				String editingItemName = (String) Main.mainWindow.automotivePartsTable
						.getValueAt(Main.mainWindow.automotivePartsTable.getSelectedRow(), 1);
				PartRemovingWindow dialog = new PartRemovingWindow(Main.mainWindow, idOfRemovingProduct,
						editingItemName);
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			} catch (IndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "No Item Selected");
				return;
			}

		}
		
	
	
	}
}
