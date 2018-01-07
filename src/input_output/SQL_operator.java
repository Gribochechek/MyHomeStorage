package input_output;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.AutomotiveParts_Model;
import objects_For_Items.AutomotiveParts_Part;
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

			st = con.prepareStatement(
					"create table if not exists 'makes' ('makeID' INT PRIMARY KEY, 'makeName' text);");
			result = st.executeUpdate();

			st = con.prepareStatement(
					"create table if not exists 'models' ('modelID' INT PRIMARY KEY, 'makeID' INT, 'modelName' text);");
			result = st.executeUpdate();

			st = con.prepareStatement(
					"create table if not exists 'parts' ('partID' INT PRIMARY KEY, 'makeID' INT, 'modelID' INT, 'partName' text, 'catlogID' text, 'description' text, 'side' text, 'maker' text, 'quantity' real, 'storagePlace' text, 'itemImage' text);");
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

	public void initializeArrays(ArrayList<ItemGroup> groups, ArrayList<Instrument> items,
			ArrayList<AutomotiveParts_Make> makes, ArrayList<AutomotiveParts_Model> models,
			ArrayList<AutomotiveParts_Part> parts) {
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

			res = st.executeQuery("SELECT * FROM makes");

			while (res.next()) {
				makes.add(new AutomotiveParts_Make(res.getInt("makeID"), res.getString("makeName")));

			}

			res = st.executeQuery("SELECT * FROM models");

			while (res.next()) {
				models.add(new AutomotiveParts_Model(res.getInt("modelID"), res.getInt("makeID"),
						res.getString("modelNAme")));
			}

			res = st.executeQuery("SELECT * FROM parts");

			while (res.next()) {
				File partimage = new File(res.getString("itemImage"));
				parts.add(new AutomotiveParts_Part(res.getInt("partID"), res.getInt("makeID"), res.getInt("modelID"),
						res.getString("partName"), res.getString("catlogID"), res.getString("description"),
						res.getString("side"), res.getString("maker"), res.getString("quantity"),
						res.getString("storagePlace"), partimage));

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

	public void addInstrument(Instrument g)  {

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

	public void addMake(AutomotiveParts_Make make) {

		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO makes(makeID, makeName) VALUES (?,?)");
			statement.setInt(1, make.getMakeID());
			statement.setString(2, make.getMakeName());
			int result = statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку товару");
			e.printStackTrace();

		}

	}

	public void addModel(AutomotiveParts_Model model) {

		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO models(modelID, makeID, modelName) VALUES(?,?,?)");
			statement.setInt(1, model.getModelID());
			statement.setInt(2, model.getMakeID());
			statement.setString(3, model.getModelName());
			int result = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку товару");
			e.printStackTrace();
		}

	}

	public void addPart(AutomotiveParts_Part part) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO parts(partID, makeID, modelID, partName, catlogID, description, side, maker, quantity, storagePlace, itemImage) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, part.getPartID());
			statement.setInt(2, part.getMakeID());
			statement.setInt(3, part.getModelID());
			statement.setString(4, part.getPartName());
			statement.setString(5, part.getCatlogID());
			statement.setString(6, part.getDescription());
			statement.setString(7, part.getSide());
			statement.setString(8, part.getMaker());
			statement.setString(9, part.getQuantity());
			statement.setString(10, part.getStoragePlace());
			statement.setObject(11, part.getPartImage());
			int result = statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на вставку товару");
			e.printStackTrace();
		}
	}

	public void removeItem(int type, int id) {
		/*
		 * Параметр type: 1 - видалення групи товарів 2 - видалення товару 3 - видалення
		 * марки машини
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
					statement = con.prepareStatement("DELETE FROM items WHERE groupID=?");
					statement.setInt(1, id);
					result = statement.executeUpdate();
				}
				st.close();
				res.close();

				break;

			case 2:// видалення інструментів
				statement = con.prepareStatement("DELETE FROM items WHERE ID=?");
				statement.setInt(1, id);
				result = statement.executeUpdate();

				break;

			case 3: // видалення марки машини
				statement = con.prepareStatement("DELETE FROM makes WHERE makeID=?");
				statement.setInt(1, id);
				result = statement.executeUpdate();

				Statement stModels = con.createStatement();
				ResultSet resModels = stModels.executeQuery("SELECT * FROM models");

				while (resModels.next()) {
					statement = con.prepareStatement("DELETE FROM models WHERE makeID=?");
					statement.setInt(1, id);
					result = statement.executeUpdate();
				}
				stModels.close();
				resModels.close();

				Statement stPartsByMake = con.createStatement();
				ResultSet resPartsByMake = stPartsByMake.executeQuery("SELECT * FROM parts");

				while (resPartsByMake.next()) {
					statement = con.prepareStatement("DELETE FROM parts WHERE makeID=?");
					statement.setInt(1, id);
					result = statement.executeUpdate();
				}

				stPartsByMake.close();
				resPartsByMake.close();

				break;

			case 4: // видалення моделі машини
				statement = con.prepareStatement("DELETE FROM models WHERE modelID=?");
				statement.setInt(1, id);
				result = statement.executeUpdate();

				Statement stPartsByModel = con.createStatement();
				ResultSet resPartsByModel = stPartsByModel.executeQuery("SELECT * FROM parts");

				while (resPartsByModel.next()) {
					statement = con.prepareStatement("DELETE FROM parts WHERE modelID=?");
					statement.setInt(1, id);
					result = statement.executeUpdate();
				}

				stPartsByModel.close();
				resPartsByModel.close();

				break;

			case 5: // видалення запчастин
				statement = con.prepareStatement("DELETE FROM parts WHERE partID=?");
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

	public void updateInstrument(Instrument g) {
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

	public void updateMake(int makeID, String makeName) {
		try {
			Statement st = con.createStatement();
			st.execute("update 'makes' set makeName='" + makeName + "' where makeID= " + makeID + ";");
		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на оновлення інформації про групу товарів");
			e.printStackTrace();
		}
	}

	public void updateModel(AutomotiveParts_Model model) {
		try {
			Statement st = con.createStatement();
			st.execute("update 'models' set makeID='" + model.getMakeID() + "', modelName='" + model.getModelName()
					+ "' where modelID =" + model.getModelID() + ";");

		} catch (SQLException e) {
			System.out.println("Не вірний SQL запит на оновлення інформації про товар");
			e.printStackTrace();
		}
	}

	public void updatePart(AutomotiveParts_Part part) {
		try {
			Statement st = con.createStatement();
			st.execute("update 'parts' set makeID='" + part.getMakeID() + "', modelID='" + part.getModelID()
					+ "', partName='" + part.getPartName() + "', catlogID='" + part.getCatlogID() + "', description='"
					+ part.getDescription() + "', side='" + part.getSide() + "', maker='" + part.getMaker()
					+ "', quantity='" + part.getQuantity() + "', storagePlace='" + part.getStoragePlace()
					+ "', itemImage='" + part.getPartImage() + "' where partID = " + part.getPartID() + ";");

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