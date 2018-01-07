package objects_For_Items;

import java.io.Serializable;

public class AutomotiveParts_Make implements Serializable {

	private int makeID;
	private String makeName;

	public AutomotiveParts_Make(int makeID, String makeName) {
		this.makeID = makeID;
		this.makeName = makeName;
	}

	public int getMakeID() {
		return makeID;
	}

	public void setMakeID(int makeID) {
		this.makeID = makeID;
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

}
