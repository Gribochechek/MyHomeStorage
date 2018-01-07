package objects_For_Items;

import java.io.File;
import java.io.Serializable;

public class AutomotiveParts_Part implements Serializable {

	private int partID;
	private int makeID;
	private int modelID;
	private String partName;
	private String catlogID;
	private String description;
	private String side;
	private String maker;
	private String quantity;
	private String storagePlace;
	private File partImage;

	public AutomotiveParts_Part(int partID, int makeID, int modelID, String partName, String catlogID,
			String description, String side, String maker, String quantity2, String storagePlace, File itemImage) {
		this.partID = partID;
		this.makeID = makeID;
		this.modelID = modelID;
		this.partName = partName;
		this.catlogID = catlogID;
		this.description = description;
		this.side = side;
		this.maker = maker;

		this.quantity = quantity2;
		this.storagePlace = storagePlace;
		this.partImage = itemImage;
	}

	public int getPartID() {
		return partID;
	}

	public void setPartID(int partID) {
		this.partID = partID;
	}

	public int getMakeID() {
		return makeID;
	}

	public void setMakeID(int makeID) {
		this.makeID = makeID;
	}

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String name) {
		this.partName = name;
	}

	public String getCatlogID() {
		return catlogID;
	}

	public void setCatlogID(String catlogID) {
		this.catlogID = catlogID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}

	public File getPartImage() {
		return partImage;
	}

	public void setItemImage(File itemImage) {
		this.partImage = itemImage;
	}

}
