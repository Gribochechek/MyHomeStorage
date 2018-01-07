package input_output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.AutomotiveParts_Make;

public class ListWriter {
	
	
	public void saveListInFile(ArrayList list, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeObject(list);

			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
