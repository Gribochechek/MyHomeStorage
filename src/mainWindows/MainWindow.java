package mainWindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import input_output.GroupListReader;
import input_output.InstrumentListReader;
import input_output.SQL_operator;
import listeners.ListenerForButton;
import listeners.ListenerForRadioButton;
import main.Main;
import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.AutomotiveParts_Model;
import objects_For_Items.AutomotiveParts_Part;
import objects_For_Items.Instrument;
import objects_For_Items.ItemGroup;
import tableModels.TableModelAutomotiveParts;
import tableModels.TableModelInstruments;

public class MainWindow extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting screen size

	public int width = (int) screenSize.getWidth(); // announcing and initializing variables
	public int height = (int) screenSize.getHeight(); // for screen and window size
	public int windowWidth = width / 2;
	public int windowHeight = height / 2;

	public AbstractTableModel itemModel;
	public JTable itemsTable;
	public AbstractTableModel automotivePartsTableModel;
	public JTable automotivePartsTable;

	private JTabbedPane allItems = new JTabbedPane();
	private JPanel pFirstTab = new JPanel();
	private JPanel pSecondTab = new JPanel();
	private JPanel pFirstTabButtonPanel;
	private JPanel title_panel;
	private JLabel title;

	public ArrayList<Instrument> items;
	public ArrayList<ItemGroup> groupsList;
	public ArrayList<AutomotiveParts_Make> makesList;
	public ArrayList<AutomotiveParts_Part> partList;
	public ArrayList<AutomotiveParts_Model> modelList;

	ListenerForButton aListener = new ListenerForButton();
	ListenerForRadioButton rdbt_listener = new ListenerForRadioButton();

	private JScrollPane jsp_itemTable;
	private JLabel lblGroup;
	public JComboBox cb_Group_name;
	public JButton btnAddGroup;
	public JButton btnEditGroup;
	public JButton btnDeleteGroup;
	public JButton bAddItem;
	public JButton btnEditItem;
	public JButton btnDeleteItem;
	public JButton btnSearch;

	public JRadioButton rdbtnShowAllItems;
	public JRadioButton rdbtnShowItemsFromGroup;

	public SQL_operator sql = new SQL_operator();

	private JPanel panel_buttonPanel_spareParts;

	private JScrollPane jsp_automotivePartsTable;
	public JButton btnSearch_1;
	public JButton btnAddMake;
	public JButton btnEditMake;
	public JButton btnDeleteMake;
	public JButton btnAddModel;
	public JButton btnEditModel;
	public JButton btnDeleteModel;
	public JButton btnAddPart;
	public JButton btnEditPart;
	public JButton btnDeletePart;

	public JRadioButton rdbtnAllParts;

	public JRadioButton rdbtnPartsForMake;

	public JRadioButton rdbtnPartsForModel;

	public JComboBox comboBox_makeNames;

	public JComboBox comboBox_model_names;

	public MainWindow(String s, File imagesSavingFolder, File groupstxt, File instrumentsdat) throws IOException {
		super(s);

		setBounds(width / 2 - windowWidth / 2, height / 2 - windowHeight / 2, windowWidth, windowHeight);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, allItems, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, allItems, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, allItems, 0, SpringLayout.EAST, getContentPane());
		getContentPane().setLayout(springLayout);

		title = new JLabel("My Storage");
		title.setFont(new Font("TimesNewRoman", Font.BOLD, 20));

		title_panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, allItems, 0, SpringLayout.SOUTH, title_panel);
		springLayout.putConstraint(SpringLayout.NORTH, title_panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, title_panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, title_panel, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, title_panel, 0, SpringLayout.EAST, getContentPane());

		title_panel.setLayout(new FlowLayout());
		title_panel.add(title);
		getContentPane().add(title_panel);

		/*
		 * if (groupstxt.exists() && groupstxt.length() > 2) { groupsList =
		 * grr.getGroupsList(); } if (instrumentsdat.exists() &&
		 * instrumentsdat.length()> 2) {
		 * 
		 * items = ilr.getProductsList(); }
		 */

		items = new ArrayList<Instrument>();
		groupsList = new ArrayList<ItemGroup>();
		makesList = new ArrayList<AutomotiveParts_Make>();
		modelList = new ArrayList<AutomotiveParts_Model>();
		partList = new ArrayList<AutomotiveParts_Part>();

		sql.db_initialization("Homestorage.db");
		sql.initializeArrays(groupsList, items, makesList, modelList, partList);

		itemModel = new TableModelInstruments(items);
		ListSelectionModel lm = new DefaultListSelectionModel();
		lm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsTable = new JTable(itemModel);
		itemsTable.setAutoCreateRowSorter(true);
		itemsTable.getTableHeader().setReorderingAllowed(true);
		itemsTable.setUpdateSelectionOnSort(true);

		pFirstTab.setLayout(new BorderLayout());
		jsp_itemTable = new JScrollPane(itemsTable);
		pFirstTab.add(jsp_itemTable, BorderLayout.CENTER);

		allItems.add("Instrumets", pFirstTab);
		allItems.setEnabledAt(0, true);

		pFirstTabButtonPanel = new JPanel();
		pFirstTabButtonPanel.setLocation(0, 392);
		pFirstTabButtonPanel.setSize(937, 35);
		pFirstTab.add(pFirstTabButtonPanel, BorderLayout.SOUTH);

		pFirstTabButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		btnAddGroup = new JButton("Add Group");
		pFirstTabButtonPanel.add(btnAddGroup);
		btnAddGroup.addActionListener(aListener);

		btnEditGroup = new JButton("Edit Group");
		pFirstTabButtonPanel.add(btnEditGroup);
		btnEditGroup.addActionListener(aListener);

		btnDeleteGroup = new JButton("Delete Group");
		pFirstTabButtonPanel.add(btnDeleteGroup);
		btnDeleteGroup.addActionListener(aListener);

		bAddItem = new JButton("Add Item");
		pFirstTabButtonPanel.add(bAddItem);
		bAddItem.addActionListener(aListener);

		btnEditItem = new JButton("Edit Item");
		pFirstTabButtonPanel.add(btnEditItem);
		btnEditItem.addActionListener(aListener);

		btnDeleteItem = new JButton("Delete Item");
		pFirstTabButtonPanel.add(btnDeleteItem);
		btnDeleteItem.addActionListener(aListener);

		btnSearch = new JButton("Search");
		pFirstTabButtonPanel.add(btnSearch);
		btnSearch.addActionListener(aListener);

		JPanel panel = new JPanel();
		pFirstTab.add(panel, BorderLayout.NORTH);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.NORTH, allItems);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		ButtonGroup bg1 = new ButtonGroup();
		String[] string = getGroupsNames();

		rdbtnShowAllItems = new JRadioButton("show all items");
		rdbtnShowAllItems.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnShowAllItems.addActionListener(rdbt_listener);
		panel.add(rdbtnShowAllItems);

		rdbtnShowItemsFromGroup = new JRadioButton("show items from group");
		rdbtnShowItemsFromGroup.addActionListener(rdbt_listener);
		panel.add(rdbtnShowItemsFromGroup);
		bg1.add(rdbtnShowAllItems);
		bg1.add(rdbtnShowItemsFromGroup);

		lblGroup = new JLabel("Group:");
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblGroup);

		cb_Group_name = new JComboBox();
		cb_Group_name.setModel(new DefaultComboBoxModel(string));
		cb_Group_name.addActionListener(rdbt_listener);
		panel.add(cb_Group_name);

		allItems.add("Автозапчастини", pSecondTab);
		allItems.setEnabledAt(1, true);
		SpringLayout sl_pSecondTab = new SpringLayout();
		pSecondTab.setLayout(sl_pSecondTab);

		automotivePartsTableModel = new TableModelAutomotiveParts(partList);
		ListSelectionModel lm1 = new DefaultListSelectionModel();
		lm1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automotivePartsTable = new JTable(automotivePartsTableModel);
		automotivePartsTable.setAutoCreateRowSorter(true);
		automotivePartsTable.getTableHeader().setReorderingAllowed(true);
		automotivePartsTable.setUpdateSelectionOnSort(true);
		jsp_automotivePartsTable = new JScrollPane(automotivePartsTable);
		sl_pSecondTab.putConstraint(SpringLayout.WEST, jsp_automotivePartsTable, 0, SpringLayout.WEST, pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.EAST, jsp_automotivePartsTable, 0, SpringLayout.EAST, pSecondTab);
		pSecondTab.add(jsp_automotivePartsTable);

		JPanel panel_rdbtn_spareParts = new JPanel();
		sl_pSecondTab.putConstraint(SpringLayout.NORTH, jsp_automotivePartsTable, 0, SpringLayout.SOUTH,
				panel_rdbtn_spareParts);
		FlowLayout flowLayout_1 = (FlowLayout) panel_rdbtn_spareParts.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		sl_pSecondTab.putConstraint(SpringLayout.NORTH, panel_rdbtn_spareParts, 0, SpringLayout.NORTH, pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.WEST, panel_rdbtn_spareParts, 0, SpringLayout.WEST, pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.SOUTH, panel_rdbtn_spareParts, 30, SpringLayout.NORTH, pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.EAST, panel_rdbtn_spareParts, 0, SpringLayout.EAST, pSecondTab);
		pSecondTab.add(panel_rdbtn_spareParts);

		rdbtnAllParts = new JRadioButton("All parts");
		rdbtnAllParts.setHorizontalAlignment(SwingConstants.LEFT);
		panel_rdbtn_spareParts.add(rdbtnAllParts);

		rdbtnPartsForMake = new JRadioButton("parts for make");
		panel_rdbtn_spareParts.add(rdbtnPartsForMake);

		comboBox_makeNames = new JComboBox();
		comboBox_makeNames.setMaximumRowCount(20);
		comboBox_makeNames.setModel(new DefaultComboBoxModel<>(getMakeNames()));
		panel_rdbtn_spareParts.add(comboBox_makeNames);
		comboBox_makeNames.addActionListener(new ActionListener() {// listener which fills combobox with model names of chosen make

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> modelsList = new ArrayList<>();
				int tempMakeID = Main.mainWindow.makesList.get(comboBox_makeNames.getSelectedIndex()).getMakeID();

				for (int i = 0; i < Main.mainWindow.modelList.size(); i++) {
					AutomotiveParts_Model model = Main.mainWindow.modelList.get(i);
					if (model.getMakeID() == tempMakeID) {
						modelsList.add(model.getModelName());
					}
				}
				String[] string_models = new String[modelsList.size()];
				for (int i = 0; i < modelsList.size(); i++) {
					string_models[i] = modelsList.get(i);
				}
				comboBox_model_names.setModel(new DefaultComboBoxModel<>(string_models));
			}
		});

		rdbtnPartsForModel = new JRadioButton("parts for model");
		panel_rdbtn_spareParts.add(rdbtnPartsForModel);
		
		ButtonGroup autmotiveParts_bg = new ButtonGroup();
		autmotiveParts_bg.add(rdbtnAllParts);
		autmotiveParts_bg.add(rdbtnPartsForMake);
		autmotiveParts_bg.add(rdbtnPartsForModel);

		comboBox_model_names = new JComboBox();
		comboBox_model_names.setMaximumRowCount(20);
		comboBox_model_names.setModel(new DefaultComboBoxModel<>(getModelNames()));
		panel_rdbtn_spareParts.add(comboBox_model_names);

		btnSearch_1 = new JButton("Search");
		btnSearch_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_rdbtn_spareParts.add(btnSearch_1);

		panel_buttonPanel_spareParts = new JPanel();
		sl_pSecondTab.putConstraint(SpringLayout.SOUTH, jsp_automotivePartsTable, 0, SpringLayout.NORTH,
				panel_buttonPanel_spareParts);
		FlowLayout flowLayout_2 = (FlowLayout) panel_buttonPanel_spareParts.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		sl_pSecondTab.putConstraint(SpringLayout.NORTH, panel_buttonPanel_spareParts, -40, SpringLayout.SOUTH,
				pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.WEST, panel_buttonPanel_spareParts, 0, SpringLayout.WEST, pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.SOUTH, panel_buttonPanel_spareParts, 0, SpringLayout.SOUTH,
				pSecondTab);
		sl_pSecondTab.putConstraint(SpringLayout.EAST, panel_buttonPanel_spareParts, 0, SpringLayout.EAST, pSecondTab);
		pSecondTab.add(panel_buttonPanel_spareParts);

		btnAddMake = new JButton("Add Make");
		panel_buttonPanel_spareParts.add(btnAddMake);
		btnAddMake.addActionListener(aListener);

		btnEditMake = new JButton("Edit Make");
		panel_buttonPanel_spareParts.add(btnEditMake);
		btnEditMake.addActionListener(aListener);

		btnDeleteMake = new JButton("Delete Make");
		panel_buttonPanel_spareParts.add(btnDeleteMake);
		btnDeleteMake.addActionListener(aListener);

		btnAddModel = new JButton("Add model");
		panel_buttonPanel_spareParts.add(btnAddModel);
		btnAddModel.addActionListener(aListener);

		btnEditModel = new JButton("Edit model");
		panel_buttonPanel_spareParts.add(btnEditModel);
		btnEditModel.addActionListener(aListener);

		btnDeleteModel = new JButton("Delete model");
		panel_buttonPanel_spareParts.add(btnDeleteModel);
		btnDeleteModel.addActionListener(aListener);

		btnAddPart = new JButton("Add part");
		panel_buttonPanel_spareParts.add(btnAddPart);
		btnAddPart.addActionListener(aListener);

		btnEditPart = new JButton("Edit part");
		panel_buttonPanel_spareParts.add(btnEditPart);
		btnEditPart.addActionListener(aListener);

		btnDeletePart = new JButton("Delete part");
		panel_buttonPanel_spareParts.add(btnDeletePart);
		btnDeletePart.addActionListener(aListener);
		getContentPane().add(allItems);

	}

	private void setColumnWidth(JTable itemsTable2, int[] is) {
		for (int i = 0; i < is.length; i++)
			itemsTable.getColumnModel().getColumn(i).setPreferredWidth(is[i]);

	}

	public String[] getGroupsNames() {
		String[] string = new String[groupsList.size()];
		for (int i = 0; i < groupsList.size(); i++) {
			ItemGroup group = groupsList.get(i);
			string[i] = group.getGroupName();
		}
		return string;

	}

	public String[] getMakeNames() {
		String[] string = new String[makesList.size()];
		for (int i = 0; i < makesList.size(); i++) {
			AutomotiveParts_Make make = makesList.get(i);
			string[i] = make.getMakeName();
		}
		return string;
	}

	public String[] getModelNames() {
		String[] string = new String[modelList.size()];
		for (int i = 0; i < modelList.size(); i++) {
			AutomotiveParts_Model model = modelList.get(i);
			string[i] = model.getModelName();
		}
		return string;

	}

	public void refreshComboBoxes() {
		String[] string_Groups = getGroupsNames();
		cb_Group_name.setModel(new DefaultComboBoxModel(string_Groups));

		String[] string_Makes = getMakeNames();
		comboBox_makeNames.setModel(new DefaultComboBoxModel(string_Makes));

		String[] string_Models = getModelNames();
		comboBox_model_names.setModel(new DefaultComboBoxModel(string_Models));

	}
}
