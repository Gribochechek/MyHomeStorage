package input_output;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.Instrument;

public class InstrumentListWriter {

	/**
	 * @param goods
	 */
	public void saveGoodsInFile(ArrayList<Instrument> items) {

		try {
			FileOutputStream fout = new FileOutputStream(Main.instrumentsdat);
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeObject(items);

			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// FileWriter writer = new FileWriter("data/instrumentList.txt");
		// BufferedWriter bufferWriter = new BufferedWriter(writer);
		// String s;
		// for (int i = 0; i < goods.size(); i++) {
		// Instrument tempGood = goods.get(i);
		// s = tempGood.toString();
		//
		// // /*s = tempGood.getInstrumentID() + "|" + tempGood.getGroupID() + "|" +
		// // tempGood.getName() + "|"
		// // + tempGood.getMaker() + "|" + tempGood.getDescription() + "|"
		// // + tempGood.getQuantity()+"|" + tempGood.getUnit() + "|" +
		// // tempGood.getStoragePlace()+ "|\n";*/
		// bufferWriter.write(s);
		// }
		// bufferWriter.close();
		// writer.close();
		// } catch (IOException e1) {
		// System.out.println(e1);
		//
		// }

	}
}
