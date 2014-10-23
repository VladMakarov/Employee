package com.academysmart.repositorty;

import static org.junit.Assert.assertTrue;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass() throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("����",
				"������", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		Assert.assertSame(EmployeeRepositorySingleton.getRepository(),
				EmployeeRepositorySingleton.getRepository());
	}

	@Test(expected = IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws ServletException,
			IncorrectEmailException {
		EmployeeRepositorySingleton.getRepository().addEmployee("����",
				"������", "ivanovICH@mail.ru");
		EmployeeRepositorySingleton.getRepository().addEmployee("����",
				"������", "ivanovICH@mail.ru");
	}

}
