package input_output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import objects_For_Items.ItemGroup;

public class GroupListWriter {
	public void saveGroupsInFile(ArrayList<ItemGroup> groups) {
		try {
			FileWriter writer = new FileWriter(Main.mainWindow.groupstxt);
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < groups.size(); i++) {
				ItemGroup tempGroup = groups.get(i);
				s = tempGroup.getGroupID() + ";" + tempGroup.getGroupName() + ";" + "\n";
				bufferWriter.write(s);

			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}

}
