package dialogWindows;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import main.Main;
import mainWindows.MainWindow;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import input_output.WorkingFolders_RW;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class SetFoldersWindow extends JDialog {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting screen size

	public int width = (int) screenSize.getWidth(); // announcing and initializing variables
	public int height = (int) screenSize.getHeight(); // for screen and window size
	public int windowWidth = width / 2;
	public int windowHeight = height / 2;
	
	
	

public SetFoldersWindow() {
		setTitle("Seting Data Folders");
		setSize(420, 387);
		setLocation(width / 2 - 420 / 2, height / 2 - 340 / 2);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(7, 0, 0, 0));

		JPanel panelSetDataFolder = new JPanel();
		getContentPane().add(panelSetDataFolder);

		JLabel lblSetDataSaving = new JLabel("Set data saving folder");
		lblSetDataSaving.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelSetDataFolder.add(lblSetDataSaving);

		JPanel panel_DataFolderPath = new JPanel();
		getContentPane().add(panel_DataFolderPath);

		JLabel label_Chosen_Folder = new JLabel("");
		panel_DataFolderPath.add(label_Chosen_Folder);

		JPanel panel_forButton = new JPanel();
		getContentPane().add(panel_forButton);

		JButton btnSetDataFolder = new JButton("Set Data Folder");
		btnSetDataFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = chooser.showDialog(null, "Choose folder for saving images");
				if (ret == JFileChooser.APPROVE_OPTION) {
					
					
					label_Chosen_Folder.setText(Main.groupstxt.getParent());
				}
				
			}
		});
		panel_forButton.add(btnSetDataFolder);

		JPanel panel_SetImageFolder = new JPanel();
		getContentPane().add(panel_SetImageFolder);

		JLabel lblSetImagesSaving = new JLabel("Set images saving folder");
		lblSetImagesSaving.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_SetImageFolder.add(lblSetImagesSaving);

		JPanel panel_ImagesFolderPath = new JPanel();
		getContentPane().add(panel_ImagesFolderPath);

		JLabel labelChosenFolderForImages = new JLabel("");
		labelChosenFolderForImages.setSize(420, 308/6);;
		
		panel_ImagesFolderPath.add(labelChosenFolderForImages);

		JPanel panel_setImageFoldereButton = new JPanel();
		getContentPane().add(panel_setImageFoldereButton);

		JButton btnSetImageFolder = new JButton("Set Image Folder");
		btnSetImageFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = chooser.showDialog(null, "Choose folder for saving images");
				if (ret == JFileChooser.APPROVE_OPTION) {
					Main.imageSaveFolder = chooser.getSelectedFile();
					labelChosenFolderForImages.setText(Main.imageSaveFolder.getAbsolutePath());
					
				}
				
				

			}
		});
		panel_setImageFoldereButton.add(btnSetImageFolder);
		
		JPanel panel_OKButton = new JPanel();
		getContentPane().add(panel_OKButton);
		
		JButton btnOk = new JButton("OK");
		
		btnOk.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					WorkingFolders_RW frw = new WorkingFolders_RW();
					frw.saveWorkingFolder(Main.groupstxt.getAbsolutePath());
					frw.saveWorkingFolder(Main.instrumentsdat.getAbsolutePath());
					frw.saveWorkingFolder(Main.imageSaveFolder.getAbsolutePath());
					
					Main.mainWindow = new MainWindow("Home Storage", Main.imageSaveFolder, Main.groupstxt, Main.instrumentsdat);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main.mainWindow.setVisible(true);
				dispose();
				
			}
		});
		panel_OKButton.add(btnOk);
	}

}
