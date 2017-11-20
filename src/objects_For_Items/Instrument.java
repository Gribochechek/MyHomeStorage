package objects_For_Items;

public class Instrument {

	private int instrumentID;
	private int groupID;
	private String name;
	private String description;
	private String maker;
	private String unit; // Одиниці вимірювання товарів (кг., шт., од., і тд.)
	private double quantity;
	private String storagePlace;

	private static int uniqueID = 1;

	public Instrument(int instrumentID, int groupID, String name, String description, String maker, String unit,
			double quantity, String storagePlace) {
		this.instrumentID = instrumentID;
		this.groupID = groupID;
		this.name = name;
		this.description = description;
		this.maker = maker;
		this.unit = unit;
		this.quantity = quantity;
		this.storagePlace = storagePlace;
	}

	public Instrument(int instrumentID, int groupID, String name, String maker, String unit, double quantity,
			String storagePlace) {
		this.instrumentID = instrumentID;
		this.groupID = groupID;
		this.name = name;
		this.maker = maker;
		this.unit = unit;
		this.quantity = quantity;
		this.storagePlace = storagePlace;
	}

	public int getInstrumentID() {
		return instrumentID;
	}

	public void setInstrumentID(int instrumentID) {
		this.instrumentID = instrumentID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}

	@Override
	public String toString() {
		return instrumentID + ";"+ groupID + ";" + name + ";"
				+ description + ";" + maker + ";" + unit + ";" + quantity + ";"
				+ storagePlace+";\n";
	}

	
}
