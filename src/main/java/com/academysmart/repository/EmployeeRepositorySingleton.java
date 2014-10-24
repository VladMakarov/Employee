package com.academysmart.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {
	static EmployeeRepositorySingleton repository;
	static List<Employee> employees = new ArrayList<Employee>();
	static String user = "Makarov";
	static String password = "12251991";
	static String url = "jdbc:oracle:thin:@localhost:1521/XE";
	static String driver = "oracle.jdbc.driver.OracleDriver";

	
	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (repository == null) {
			repository = new EmployeeRepositorySingleton();
		}
		return repository;
	}

	public static void getAllEmployeeWhenStartDB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Employee newEmployee = null;
		Connection connect = null;
		Statement statement = null;
		ResultSet choiseResult = null;
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = (Statement) connect.createStatement();
			choiseResult = statement.executeQuery("SELECT * FROM Employee ORDER BY id");
			while (choiseResult.next()) {
				newEmployee = new Employee();
				newEmployee.setId(choiseResult.getString("id"));
				newEmployee.setName(choiseResult.getString("name"));
				newEmployee.setSurName(choiseResult.getString("surname"));
				newEmployee.setEmail(choiseResult.getString("email"));
				employees.add(newEmployee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, SQLException {
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		int id = 1;
		if (fname.equals("") || lname.equals("") || email.equals("")) {
			throw new ServletException("Enter all field!");
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		Statement statement = null;
		ResultSet choiseResult = null;
		PreparedStatement preperedStatement = null;
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = (Statement) connect.createStatement();
			choiseResult = statement.executeQuery("SELECT id FROM Employee");
			while (choiseResult.next()) {
				id = 1 + Integer.parseInt(choiseResult.getString("id"));
			}
			choiseResult = statement.executeQuery("SELECT email FROM Employee");
			preperedStatement = connect.prepareStatement("insert into employee "
							+ "values(?, ?, ?, ?)");
			preperedStatement.setInt(1, id);
			preperedStatement.setString(2, fname);
			preperedStatement.setString(3, lname);
			preperedStatement.setString(4, email);
			preperedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Employee newEmployee = new Employee();
		newEmployee.setId(String.valueOf(id));
		newEmployee.setName(fname);
		newEmployee.setSurName(lname);
		newEmployee.setEmail(email);
		for (Employee i : employees) {
			if (i.getEmail().equals(newEmployee.getEmail())) {
				throw new IncorrectEmailException(
						"This email is not available!");
			}
		}
		employees.add(newEmployee);
		
	}

	public List<Employee> getAllEmployees() {
		// TODO implement method that returns list of all employees
		return employees;
	}
}
