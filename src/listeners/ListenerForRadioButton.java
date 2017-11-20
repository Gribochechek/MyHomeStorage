package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.Instrument;
import tableModels.TableModelInstruments;

public class ListenerForRadioButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (Main.mainWindow.rdbtnShowAllItems.isSelected()) {
			Main.mainWindow.itemsTable.setModel(Main.mainWindow.itemModel);

		}

		if (Main.mainWindow.rdbtnShowItemsFromGroup.isSelected() | e.getSource() == Main.mainWindow.cb_Group_name) {
			Main.mainWindow.rdbtnShowItemsFromGroup.setSelected(true);
			if (Main.mainWindow.groupstxt.exists() && Main.mainWindow.groupstxt.length() > 5) {
				int index = Main.mainWindow.cb_Group_name.getSelectedIndex();
				int groupID = Main.mainWindow.groupsList.get(index).getGroupID();

				ArrayList<Instrument> tempGoods = new ArrayList<Instrument>();
				for (int i = 0; i < Main.mainWindow.items.size(); i++) {

					if (Main.mainWindow.items.get(i).getGroupID() == groupID)
						tempGoods.add(Main.mainWindow.items.get(i));
				}
				TableModelInstruments tempGoodsModel = new TableModelInstruments(tempGoods);
				Main.mainWindow.itemsTable.setModel(tempGoodsModel);
			}
		}

	}

}
