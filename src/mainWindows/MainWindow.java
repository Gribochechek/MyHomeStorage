package mainWindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import input_output.GroupListReader;
import input_output.InstrumentListReader;
import listeners.ListenerForButton;
import objects_For_Items.Instrument;
import objects_For_Items.ItemGroup;
import tableModels.TableModelInstruments;
import javax.swing.SpringLayout;

public class MainWindow extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting screen size

	public int width = (int) screenSize.getWidth(); // announcing and initializing variables
	public int height = (int) screenSize.getHeight(); // for screen and window size
	public int windowWidth = width / 2;
	public int windowHeight = height / 2;

	public AbstractTableModel itemModel;
	public JTable itemsTable;

	private JTabbedPane allItems = new JTabbedPane();
	private JPanel pFirstTab = new JPanel();
	private JPanel pSecondTab = new JPanel();
	private JPanel pFirstTabButtonPanel;
	private JPanel title_panel;
	private JLabel title;

	public ArrayList<Instrument> items = new ArrayList<Instrument>();
	public ArrayList<ItemGroup> groupsList = new ArrayList<ItemGroup>();
	public ArrayList<Instrument> deletedItems = new ArrayList<Instrument>();

	ListenerForButton aListener = new ListenerForButton();

	private JScrollPane jsp_itemTable;
	private JLabel lblGroup;
	private JComboBox cb_Group_name;
	public JButton btnAddGroup;
	public JButton btnEditGroup;
	public JButton btnDeleteGroup;
	public JButton bAddItem;
	public JButton btnEditItem;
	public JButton btnDeleteItem;
	public JButton btnSearch;

	private GroupListReader grr;

	private InstrumentListReader ilr = new InstrumentListReader();

	public File groupstxt = new File("data/groupList.txt");
	public File instrumentstxt = new File("data/instrumentList.txt");

	public JRadioButton rdbtnShowAllItems;

	public JRadioButton rdbtnShowItemsFrom;
	

	/*
	 * private JPanel mainPanel = new JPanel(); private JPanel title = new JPanel();
	 * private JTabbedPane goodsAll = new JTabbedPane(); private JPanel pFirstTab =
	 * new JPanel(); private JPanel pSecondTab = new JPanel();
	 */
	public MainWindow(String s) throws IOException {
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
		springLayout.putConstraint(SpringLayout.NORTH, title_panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, title_panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, title_panel, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, title_panel, 0, SpringLayout.EAST, getContentPane());

		title_panel.setLayout(new FlowLayout());
		title_panel.add(title);
		getContentPane().add(title_panel);

		if (groupstxt.exists() && groupstxt.length() > 5) {
			grr = new GroupListReader();
			groupsList = grr.getGroupsList();
		}
		if (instrumentstxt.exists() && instrumentstxt.length() > 5) {

			items = ilr.getProductsList();
		}

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
		springLayout.putConstraint(SpringLayout.NORTH, allItems, 40, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.NORTH, allItems);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());

		getContentPane().add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		rdbtnShowAllItems = new JRadioButton("show all items");
		rdbtnShowAllItems.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(rdbtnShowAllItems);

		rdbtnShowItemsFrom = new JRadioButton("show items from group");
		panel.add(rdbtnShowItemsFrom);

		lblGroup = new JLabel("Group:");
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblGroup);

		cb_Group_name = new JComboBox();
		String[] string = getGroupsNames();
		cb_Group_name.setModel(new DefaultComboBoxModel(string));
		panel.add(cb_Group_name);

		allItems.add("Parts", pSecondTab);
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

	public void refreshComboBoxes() {
		String[] string = getGroupsNames();
		cb_Group_name.setModel(new DefaultComboBoxModel(string));
	}
}
