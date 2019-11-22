package com.student.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "admin", "The@1234");
		return con;
	}

	public static List<Map<String, Object>> getMapList(Connection connection, String query, Object... args)
			throws SQLException {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		PreparedStatement ps = connection.prepareStatement(query);
		for (int i = 0; i < args.length; i++)
			ps.setObject(i + 1, args[i]);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columns; i++) {
				row.put(rsmd.getColumnLabel(i), rs.getObject(i));
			}
			rows.add(row);
		}
		ps.close();
		return rows;
	}

	public static Map<String, Object> get(Connection connection, String query, Object... args) throws SQLException {
		if (!getMapList(connection, query, args).isEmpty())
			return getMapList(connection, query, args).get(0);
		else
			return new LinkedHashMap<String, Object>();
	}

	public static int update(Connection connection, String query, Object... args)
			throws SQLException, ClassNotFoundException {
		PreparedStatement ps = connection.prepareStatement(query);
		for (int i = 0; i < args.length; i++)
			ps.setObject(i + 1, args[i]);
		int rs = ps.executeUpdate();
		ps.close();
		return rs;
	}

	public static Integer getGeneratedKey(Connection connection, String sql, Object... args) throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (Object value : args)
			preparedStatement.setObject(++index, value);
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	public static void executeBatchUpdate(Connection conn, String query,String[] args,int id) throws SQLException {
		java.sql.PreparedStatement statement = conn.prepareStatement(query);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(1, Integer.parseInt(args[i]));
			statement.setObject(2,id);
			statement.addBatch();
		}
		statement.executeBatch();
		statement.close();
	}

}
