package main;

import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dialogWindows.SetFoldersWindow;
import input_output.WorkingFolders_RW;
import mainWindows.MainWindow;

public class Main {

	public static WorkingFolders_RW wfr = new WorkingFolders_RW();
	public static SetFoldersWindow setFoldfWin;
	public static File groupstxt = new File("data/groupList.txt");
	public static File groupListdat = new File("data/groupList.dat");
	public static File instrumentsdat = new File("data/instruments.dat");
	public static File makesListdat = new File("data/makesList.dat");
	public static File modelListdat = new File("data/modelList.dat");
	public static File partListdat = new File("data/partlist.dat");
	public static File imageSaveFolder = new File("images/");
	public static MainWindow mainWindow;

	public static void main(String[] args) throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mainWindow = new MainWindow("Home Storage", imageSaveFolder, groupstxt, instrumentsdat);
		mainWindow.setVisible(true);

	}

}
