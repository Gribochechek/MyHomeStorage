package main;

import java.io.File;
import java.io.IOException;

import dialogWindows.SetFoldersWindow;
import input_output.WorkingFolders_RW;
import mainWindows.LoginWindow;
import mainWindows.MainWindow;

public class Main {

	public static WorkingFolders_RW wfr = new WorkingFolders_RW();
	public static SetFoldersWindow setFoldfWin;
	public static File groupstxt = new File("data/groupList.txt");
	public static File instrumentsdat = new File("data/instruments.dat");
	public static File imageSaveFolder = new File("images/");
	public static MainWindow mainWindow;

	public static void main(String[] args) throws IOException {

		mainWindow = new MainWindow("Home Storage", imageSaveFolder, groupstxt, instrumentsdat);
		mainWindow.setVisible(true);

	}

}
