package dialogWindows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListReader {

	public ArrayList getProductsList(File file) throws IOException {

		ArrayList items = new ArrayList();
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fin);
		try {
			items = (ArrayList) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
		return items;

	}
}
