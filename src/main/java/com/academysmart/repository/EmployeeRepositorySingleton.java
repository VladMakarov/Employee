package com.academysmart.repository;

import java.sql.Connection;
import java.sql.DriverManager;
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
	List <Employee> employee = new ArrayList<Employee>();
	
//	public EmployeeRepositorySingleton() {
//		Employee emp = new Employee();
//		emp.setId("ID");
//		emp.setName("Name");
//		emp.setSurName("Surname");
//		emp.setEmail("E-mail");
//		employee.add(emp);
//	}

	public static EmployeeRepositorySingleton getRepository() {
		//TODO implement method that returns repository instance
		if (repository == null) {
			repository = new EmployeeRepositorySingleton();
		}
		return repository;
	}
	
	int id = 1;
	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, SQLException {
		//TODO implement method that adds an employee to repository
		//This method should check that email is not used by other employees 
		 String user = "Makarov";//Логин пользователя
	        String password = "12251991";//Пароль пользователя
	        String url = "jdbc:oracle:thin:@localhost:1521/XE";//URL адрес
	        String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера
	        try {
	             Class.forName(driver);//Регистрируем драйвер
	        } catch (ClassNotFoundException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	        }
	        Connection c = null;//Соединение с БД
	        try{
	             c = DriverManager.getConnection(url, user, password);//Установка соединения с БД
	             Statement st = (Statement) c.createStatement();//Готовим запрос
	             ResultSet rs = st.executeQuery("select * from Employee");//Выполняем запрос к БД, результат в переменной rs
	             while(rs.next()){
	                  System.out.println(rs.getString(2));
	             }
	        } catch(Exception e){
	             e.printStackTrace();
	        }
	        finally{
	             //Обязательно необходимо закрыть соединение
	             try {
	                  if(c != null)
	                  c.close();
	             } catch (SQLException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	             }
	        }
		//-------------------------------------------------
		Employee newEmployee = new Employee();
		if(fname.equals("") || lname.equals("") || email.equals("")){
			throw new ServletException("Enter all field!");
		}
		newEmployee.setId(String.valueOf(id));
		newEmployee.setName(fname);
		newEmployee.setSurName(lname);
		newEmployee.setEmail(email);
		for(Employee i : employee){
			if (i.getEmail().equals(newEmployee.getEmail())) {
				throw new IncorrectEmailException("This email is not available!");
			}
		}
		employee.add(newEmployee);
		id++;
	}

	public List<Employee> getAllEmployees() {
		//TODO implement method that returns list of all employees
		return employee;
	}
}
