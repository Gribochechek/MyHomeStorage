package tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import objects_For_Items.AutomotiveParts_Part;
import objects_For_Items.Instrument;

public class TableModelAutomotiveParts extends AbstractTableModel {

	private ArrayList<AutomotiveParts_Part> automotiveSpareParts;

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public TableModelAutomotiveParts(ArrayList<AutomotiveParts_Part> automotiveSpareParts) {
		this.automotiveSpareParts = automotiveSpareParts;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public int getRowCount() {

		return automotiveSpareParts.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		AutomotiveParts_Part partIndex = automotiveSpareParts.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return partIndex.getPartID();
		case 1:
			return partIndex.getPartName();
		case 2:
			return partIndex.getCatlogID();
		case 3:
			return partIndex.getDescription();
		case 4:
			return partIndex.getSide();
		case 5:
			return partIndex.getMaker();
		case 6:
			return partIndex.getQuantity();
		case 7:
			return partIndex.getStoragePlace();

		}
		return "";
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Part name";
		case 2:
			return "Catalog ID";
		case 3:
			return "Description";
		case 4:
			return "Side";
		case 5:
			return "Maker";
		case 6:
			return "Quantity";
		case 7:
			return "Storage place";
		}
		return "";
	}

}
