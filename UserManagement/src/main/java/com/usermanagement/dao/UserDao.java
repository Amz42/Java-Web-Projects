package com.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanagement.bean.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/usermanagement";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users;";
	private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
	private static final String SELECT_USER_SQL = "SELECT id, name, email, country FROM users WHERE id = ?;";
	private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?;";
	private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?;";
	
	public UserDao() {}
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void insertUser(User user) throws SQLException {
		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
		) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			System.out.println("INSERT USER: " + preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e){
			printSQLException(e);
		}
	}
	
	public User selectUser(int id) {
		User user = null;
		
		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);
		) {
			preparedStatement.setInt(1, id);
			System.out.println("SELECT USER: " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return user;
	}
	
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		
		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
		) {
			System.out.println("SELECT ALL USER: " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return users;
	}
	
	public boolean updateUser(User user) throws SQLException {
		boolean isUserUpdated = false;
		
		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
		) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getId());
			System.out.println("UPDATE USER: " + preparedStatement);
			isUserUpdated = preparedStatement.executeUpdate() > 0;
		}
		
		return isUserUpdated;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean isUserDeleted = false;
		
		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
		) {
			preparedStatement.setInt(1, id);
			isUserDeleted = preparedStatement.executeUpdate() > 0;
		}
		
		return isUserDeleted;
	}
	
	private void printSQLException(SQLException ex) {
		for(Throwable e: ex) {
			if(e instanceof SQLException){
				e.printStackTrace(System.err);
				System.err.println("SQLstate: " + ((SQLException) e).getSQLState());
				System.err.println("ErrorCode: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				
				Throwable t = ex.getCause();
				while(t != null) { 
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
}
