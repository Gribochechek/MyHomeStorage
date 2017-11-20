package main;

import java.io.IOException;

import mainWindows.LoginWindow;
import mainWindows.MainWindow;

public class Main {

	public static MainWindow mainWindow;

	public static void main(String[] args) throws IOException {
		// LoginWindow stWin = new LoginWindow("Home Storage");
		// stWin.setVisible(true);
		mainWindow = new MainWindow("Home Storage");
		mainWindow.setVisible(true);
	}

}
