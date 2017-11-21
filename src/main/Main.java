package main;

import java.io.File;
import java.io.IOException;

import dialogWindows.SetFoldersWindow;
import mainWindows.LoginWindow;
import mainWindows.MainWindow;

public class Main {

	
	public static SetFoldersWindow setFoldfWin;
	public static File groupstxt;
	public  static File instrumentsdat;
	public static File imageSaveFolder;
	public static MainWindow mainWindow;
	

	public static void main(String[] args) throws IOException {
		// LoginWindow stWin = new LoginWindow("Home Storage");
		// stWin.setVisible(true);
		
			setFoldfWin = new SetFoldersWindow();
			setFoldfWin.setVisible(true);
		
			//mainWindow = new MainWindow("Home Storage", imageSaveFolder, groupstxt, instrumentsdat);
			//mainWindow.setVisible(true);
		
		
		
		
	}

}
