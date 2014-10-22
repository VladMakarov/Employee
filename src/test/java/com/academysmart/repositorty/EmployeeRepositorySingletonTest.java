package com.academysmart.repositorty;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass()throws Exception {
//		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
//				"Иванов", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		Assert.fail("Test is not implemented");
	}
	
	
	@Test(expected=IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws IncorrectEmailException  {
		Assert.fail("Test is not implemented");
	}

}
