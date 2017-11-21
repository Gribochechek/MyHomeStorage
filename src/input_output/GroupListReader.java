package input_output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.ItemGroup;

public class GroupListReader {

	public ArrayList<ItemGroup> getGroupsList() throws IOException {
		ArrayList<ItemGroup> groups = new ArrayList<ItemGroup>();
		FileReader fr = new FileReader(Main.groupstxt);
		BufferedReader br = new BufferedReader(fr);
		ItemGroup tempGroup;
		String text;

		while (true) {
			text = br.readLine();
			if (text != null) {
				int id = Integer.parseInt(text.substring(0, text.indexOf(";")));
				text = text.substring(text.indexOf(";") + 1);

				String pName = text.substring(0, text.indexOf(";"));
				text = text.substring(text.indexOf(";") + 1);

				tempGroup = new ItemGroup(id, pName);
				groups.add(tempGroup);

			} else
				break;

		}

		br.close();
		return groups;

	}
}
