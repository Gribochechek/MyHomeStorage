package tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import objects_For_Items.Instrument;

public class TableModelInstruments extends AbstractTableModel {

	private ArrayList<Instrument> items;

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	public TableModelInstruments(ArrayList<Instrument> items) {
		this.items = items;
	}

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Instrument goodIndex = items.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return goodIndex.getInstrumentID();
		case 1:
			return goodIndex.getName();
		case 2:
			return goodIndex.getMaker();
		case 3:
			return goodIndex.getDescription();
		case 4:
			return goodIndex.getQuantity();
		case 5:
			return goodIndex.getUnit();
		case 6:
			return goodIndex.getStoragePlace();
		
		}
		return "";
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Instrument name";
		case 2:
			return "Manufacturer";
		case 3:
			return "Description";
		case 4:
			return "Quantity";
		case 5:
			return "Unit";
		case 6:
			return "Storage place";
		}
		return "";
	}

}
