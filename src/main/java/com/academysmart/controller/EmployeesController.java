package com.academysmart.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;
import com.academysmart.repository.EmployeeRepositorySingleton;

@Controller
@RequestMapping("/emp")
public class EmployeesController {
	
	//EmployeeRepositorySingleton ers;// = new EmployeeRepositorySingleton();

	@PostConstruct
    public void getDB() {
		EmployeeRepositorySingleton.getAllEmployeeWhenStartDB();
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllEmployees(ModelMap model){
		model.addAttribute("employees", EmployeeRepositorySingleton
				.getRepository().getAllEmployees());
		return "employee";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addEmployee(ModelMap model,
			@RequestParam("fname") String fname,
			@RequestParam("lname") String lname,
			@RequestParam("email") String email)
			throws IncorrectEmailException, ServletException, SQLException {
		try {
			EmployeeRepositorySingleton.getRepository().addEmployee(fname, lname, email);
		} catch (ServletException | IncorrectEmailException e) {
			String a = e.toString().substring(e.toString().indexOf(":") + 2);
			model.addAttribute("errMsg", a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.getAllEmployees(model);
	}
}
