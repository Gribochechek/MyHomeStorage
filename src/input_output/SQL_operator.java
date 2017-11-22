package input_output;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects_For_Items.Instrument;
import objects_For_Items.ItemGroup;

public class SQL_operator {

	private Connection con;

	public void db_initialization(String name) {

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + name);
			PreparedStatement st = con.prepareStatement(
					"create table if not exists 'items' ('ID' INT PRIMARY KEY, 'groupID' INT, 'name' text, 'description' text, 'maker' text, 'unit' text, 'quantity' real, 'storagePlace' text, 'itemImage' text);");
			int result = st.executeUpdate();

			st = con.prepareStatement(
					"create table if not exists 'groups' ('groupID' INT PRIMARY KEY, 'groupName' text);");
			result = st.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void firstUpload(ArrayList<ItemGroup> groups, ArrayList<Instrument> items) {
		try {
			// якщо в базі вже є дані, тоді добавляти не будемо
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM items;");
			if (res.next()) {
				stm.close();
				res.close();
				System.out.println("У базі вже наявні дані. Нові дані не будуть повторно додані");
				return;
			}

			stm.close();
			res.close();

			PreparedStatement statement = con.prepareStatement("INSERT INTO groups(groupID, groupName) VALUES (?, ?)");

			for (ItemGroup gr : groups) {
				statement.setInt(1, gr.getGroupID());
				statement.setString(2, gr.getGroupName());
				int result = statement.executeUpdate();
			}

			statement = con.prepareStatement(
					"INSERT INTO items(ID, groupID, name, description, maker, unit, quantity, storagePlace, itemImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			for (Instrument g : items) {
				statement.setInt(1, g.getInstrumentID());
				statement.setInt(2, g.getGroupID());
				statement.setString(3, g.getName());
				statement.setString(4, g.getDescription());
				statement.setString(5, g.getMaker());
				statement.setString(6, g.getUnit());
				statement.setDouble(7, g.getQuantity());
				statement.setString(8, g.getStoragePlace());
				statement.setObject(9, g.getItemImage());

				int result = statement.executeUpdate();
			}

			statement.close();

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку");
			e.printStackTrace();
		}
	}

	public void initializeArrays(ArrayList<ItemGroup> groups, ArrayList<Instrument> items) {
		try {
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM groups");

			while (res.next()) {
				groups.add(new ItemGroup(res.getInt("groupID"), res.getString("groupName")));
			}

			res = st.executeQuery("SELECT * FROM items");

			while (res.next()) {
				File itemimage = new File(res.getString("itemImage"));
				items.add(new Instrument(res.getShort("ID"), res.getShort("groupID"), res.getString("name"),
						res.getString("description"), res.getString("maker"), res.getString("unit"),
						res.getDouble("quantity"), res.getString("storagePlace"), itemimage));

			}

			st.close();
			res.close();

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вибірку даних");
			e.printStackTrace();
		}
	}

	public void addGroup(ItemGroup gr) {

		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO groups(groupID, groupName) VALUES (?, ?)");
			statement.setInt(1, gr.getGroupID());
			statement.setString(2, gr.getGroupName());
			int result = statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку групи товарів");
			e.printStackTrace();
		}
	}

	public void addGoods(Instrument g) {

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO items(ID, groupID, name, description, maker, unit, quantity, storagePlace, itemImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, g.getInstrumentID());
			statement.setInt(2, g.getGroupID());
			statement.setString(3, g.getName());
			statement.setString(4, g.getDescription());
			statement.setString(5, g.getMaker());
			statement.setString(6, g.getUnit());
			statement.setDouble(7, g.getQuantity());
			statement.setString(8, g.getStoragePlace());
			statement.setObject(9, g.getItemImage());
			int result = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку товару");
			e.printStackTrace();
		}
	}

	public void removeItem(int type, int id) {
		/*
		 * Параметр type: 1 - видалення групи товарів 2 - видалення товару
		 */
		try {
			PreparedStatement statement = null;
			int result;

			switch (type) {

			// видалення групи
			case 1:
				statement = con.prepareStatement("DELETE FROM groups WHERE groupID=?");
				statement.setInt(1, id);
				result = statement.executeUpdate();

				Statement st = con.createStatement();
				ResultSet res = st.executeQuery("SELECT * FROM items");

				while (res.next()) {
					statement = con.prepareStatement("DELETE FROM items WHERE ID=?");
					statement.setInt(1, id);
					result = statement.executeUpdate();
				}
				st.close();
				res.close();

				break;

			case 2:
				statement = con.prepareStatement("DELETE FROM items WHERE ID=?");
				statement.setInt(1, id);
				result = statement.executeUpdate();

				break;

			}

			statement.close();

		} catch (

		SQLException e) {
			System.out.println("Не вірний SQL запит на видалення");
			e.printStackTrace();
		}
	}

	public void updateGroup(int groupID, String groupName) {
		try {
			Statement st = con.createStatement();
			st.execute("update 'groups' set groupName='" + groupName + "' where groupID = " + groupID + ";");

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на оновлення інформації про групу товарів");
			e.printStackTrace();
		}
	}

	public void updateGoods(Instrument g) {
		try {
			Statement st = con.createStatement();
			st.execute("update 'goods' set groupID='" + g.getGroupID() + "', name='" + g.getName() + "', description='"
					+ g.getDescription() + "', maker='" + g.getMaker() + "', unit='" + g.getUnit() + "', quantity='"
					+ g.getQuantity() + "', storagePlace='" + g.getStoragePlace() + "', itemImage='" + g.getItemImage()
					+ "' where id = " + g.getInstrumentID() + ";");

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на оновлення інформації про товар");
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}