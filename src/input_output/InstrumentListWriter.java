package input_output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.Instrument;

public class InstrumentListWriter {

	public void saveGoodsInFile(ArrayList<Instrument> goods) {
		try {
			FileWriter writer = new FileWriter("data/instrumentList.txt");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < goods.size(); i++) {
				Instrument tempGood = goods.get(i);
				s = tempGood.toString();
				
//				/*s = tempGood.getInstrumentID() + "|" + tempGood.getGroupID() + "|" + tempGood.getName() + "|"
//						+ tempGood.getMaker() + "|" + tempGood.getDescription() + "|"
//						+ tempGood.getQuantity()+"|" + tempGood.getUnit() + "|" + tempGood.getStoragePlace()+ "|\n";*/
				bufferWriter.write(s);
			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
}
