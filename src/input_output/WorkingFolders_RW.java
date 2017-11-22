package input_output;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkingFolders_RW {

	public void saveWorkingFolder(String s) throws IOException {

		FileWriter writer = new FileWriter("data/folder_paths.txt");
		BufferedWriter bufferWriter = new BufferedWriter(writer);
		bufferWriter.append(s);
		bufferWriter.close();
		writer.close();

	}

	public String[] readWorkingFolder() {
		String text[] = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/folder_paths.txt"));
			while (br.readLine() != null) {
				for(int i=0; i<3; i++)
				text[i] = br.readLine();

			}

			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
}
