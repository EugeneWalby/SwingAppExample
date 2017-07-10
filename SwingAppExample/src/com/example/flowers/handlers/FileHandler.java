package com.example.flowers.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileHandler {
	private static final String OUTPUT_FILE = "C:\\results.txt";
	private static BufferedWriter bw;
	private static FileWriter fw;

	public static void writeToFile(ResultSet rs) {
			try {
				fw = new FileWriter(OUTPUT_FILE);
				bw = new BufferedWriter(fw);

				bw.write("ID\t");
				bw.write("Title\t");
				bw.write("Count\t");
				bw.write("Cost\t");
				bw.write("Type" + System.getProperty("line.separator"));

				while (rs.next()) {
					bw.write(rs.getInt("ID") + "\t");
					bw.write(rs.getString("Title") + "\t");
					bw.write(rs.getInt("Count") + "\t");
					bw.write(rs.getInt("Cost") + "\t");
					bw.write(rs.getString("Type") + System.getProperty("line.separator"));
				}
			} catch (SQLException e) {
				
			} catch (IOException e) {
				
			} finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					
				}
			}
	}
}
