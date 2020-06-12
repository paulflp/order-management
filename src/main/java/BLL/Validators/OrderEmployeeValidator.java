package BLL.Validators;

import java.util.NoSuchElementException;

import BLL.EmployeeBLL;
import Model.Employee;
import Model.Order;

/**
 * Clasa care implementeaza interfata Validator pentru a verifica daca angajatul
 * care emite factura exista in BD
 * 
 * @author user
 *
 */
public class OrderEmployeeValidator implements Validator<Order> {

	public void validate(Order t) {
		// TODO Auto-generated method stub
		EmployeeBLL emplBLL = new EmployeeBLL();
		try {
			Employee empl = emplBLL.getEmployeeById(t.getIdEmployee());
		} catch (NoSuchElementException er) {
			throw new IllegalArgumentException(er.getMessage());
		}
		System.out.println("Order Employee checked!");
	}

}
