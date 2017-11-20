package input_output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.Instrument;

public class InstrumentListReader {

	public ArrayList<Instrument> getProductsList() throws IOException {
		ArrayList<Instrument> goods = new ArrayList<Instrument>();
		BufferedReader br = new BufferedReader(new FileReader("data/instrumentList.txt"));
		Instrument tempGood;
		String text;

		while (true) {
			text = br.readLine();
			if (text != null) {
				int instrumentID = Integer.parseInt(text.substring(0, text.indexOf(";")));
				text = text.substring(text.indexOf(";") + 1);

				int groupID = Integer.parseInt(text.substring(0, text.indexOf(";")));
				text = text.substring(text.indexOf(";") + 1);

				String name = text.substring(0, text.indexOf(";"));
				text = text.substring(text.indexOf(";") + 1);

				String description = text.substring(0, text.indexOf(";"));
				text = text.substring(text.indexOf(";") + 1);

				String maker = text.substring(0, text.indexOf(";"));
				text = text.substring(text.indexOf(";") + 1);

				String unit = text.substring(0, text.indexOf(";"));
				text = text.substring(text.indexOf(";") + 1);

				double quantity = Double.parseDouble(text.substring(0, text.indexOf(";")));
				text = text.substring(text.indexOf(";") + 1);

				String storagePlace = text.substring(0, text.indexOf(";"));

				tempGood = new Instrument(instrumentID, groupID, name, description, maker, unit, quantity,
						storagePlace);
				goods.add(tempGood);

			} else
				break;

		}

		br.close();
		return goods;

	}

}
