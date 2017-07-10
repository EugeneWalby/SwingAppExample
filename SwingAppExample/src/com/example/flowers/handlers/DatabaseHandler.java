package com.example.flowers.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.flowers.entities.Flower;

public class DatabaseHandler {
	private final String DB_DRIVER = "jdbc:ucanaccess://";
	private final String DB_NAME = "FlowersDB.accdb";
	private final String DB_PATH = "C:/TMP/";
	private final String DB_ACCESS = DB_DRIVER + DB_PATH + DB_NAME;

	// insert data to DB
	public void insertData(Flower flower) throws SQLException {
		Connection con;
		con = DriverManager.getConnection(DB_ACCESS);

		String sql = "Insert Into Flowers(ID, Title, Count, Cost, Type) " + "Values ('1', '" + flower.getTitle() + "','"
				+ flower.getCount() + "','" + flower.getCost() + "','" + flower.getType() + "')";

		Statement statement = con.createStatement();
		statement.execute(sql);
	}

//	// update data in DB
//	public void updateData(Flower flower) {
//		Connection con;
//		try {
//			con = DriverManager.getConnection(DB_ACCESS);
//			String sql = "Update Flowers Set Title='" + flower.getTitle() 
//					+ "',Count='" + flower.getCount()
//					+ "', Cost='" + flower.getCost() + "', Type='" 
//					+ flower.getType() + "' Where ID='" 
//					+ flower.getId() + "'";
//			Statement statement = con.createStatement();
//			statement.execute(sql);
//			createMessageBox("Updated Successfully!");
//			clearFields();
//		} catch (Exception e) {
//			createMessageBox("Error while updating!");
//		}
//	}

	// delete data from DB
	public void deleteData(int id) throws SQLException {
		Connection con;
		con = DriverManager.getConnection(DB_ACCESS);
		String sql = "delete from Flowers where ID = '" + id + "'";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}

	// update data in DB
	public List<Flower> searchData(String titleText) throws SQLException {
		List<Flower> flowers = new ArrayList<Flower>();
		Connection con;
		con = DriverManager.getConnection(DB_ACCESS);
		String sql = "Select * from Flowers" + " Where Title='" + titleText + "'";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		// iterate through the java resultset
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			int count = rs.getInt("Count");
			int cost = rs.getInt("Cost");
			String type = rs.getString("Type");
			flowers.add(new Flower(id, title, count, cost, type));
		}
		statement.close();

		return flowers;
	}

	// update data in DB
	public List<Flower> sortData() throws SQLException {
		List<Flower> flowers = new ArrayList<Flower>();
		Connection con;
		con = DriverManager.getConnection(DB_ACCESS);
		String sql = "Select * from Flowers order by Title";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		// iterate through the java resultset
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			int count = rs.getInt("Count");
			int cost = rs.getInt("Cost");
			String type = rs.getString("Type");
			flowers.add(new Flower(id, title, count, cost, type));
		}
		statement.close();

		return flowers;
	}

	// show data in DB
	public List<Flower> showData() throws SQLException {
		List<Flower> flowers = new ArrayList<Flower>();
		Connection con;

		con = DriverManager.getConnection(DB_ACCESS);
		String sql = "Select * from Flowers";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		// iterate through the java resultset
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			int count = rs.getInt("Count");
			int cost = rs.getInt("Cost");
			String type = rs.getString("Type");
			flowers.add(new Flower(id, title, count, cost, type));
		}

		statement.close();
		return flowers;
	}

	public void writeToFile() throws SQLException, IOException {
		Connection con;
		con = DriverManager.getConnection(DB_ACCESS);
		String sql = "Select * from Flowers";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		FileHandler.writeToFile(rs);
		statement.close();
	}
}
