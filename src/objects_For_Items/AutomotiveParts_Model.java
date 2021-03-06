package objects_For_Items;

import java.io.Serializable;

public class AutomotiveParts_Model implements Serializable {

	private int modelID;
	private int makeID;
	private String modelName;

	public AutomotiveParts_Model(int modelID, int makeID, String modelName) {
		this.modelID = modelID;
		this.makeID = makeID;
		this.modelName = modelName;
	}

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}

	public int getMakeID() {
		return makeID;
	}

	public void setMakeID(int makeID) {
		this.makeID = makeID;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelNAme) {
		this.modelName = modelNAme;
	}

}
