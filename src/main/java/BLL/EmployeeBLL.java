package BLL;

import java.util.List;
import java.util.NoSuchElementException;

import DAO.EmployeeDAO;
import Model.Employee;
import Model.Product;

/**
 * clasa Business pentru angajati
 * 
 * @author user
 *
 */
public class EmployeeBLL {
	private EmployeeDAO employeeDAO;

	public EmployeeBLL() {
		employeeDAO = new EmployeeDAO();
	}

	public List<Employee> findAllEmployees() {
		List<Employee> list = employeeDAO.findAll();
		return list;
	}

	public Employee getEmployeeById(int id) {
		Employee empl = employeeDAO.findById(id);
		if (empl == null) {
			throw new NoSuchElementException("Employee with id = " + id + " doesn't exist");
		}
		return empl;
	}

	public void insert(Employee newEmployee) {
		Employee empl = employeeDAO.insert(newEmployee);
		if (empl == null) {
			throw new NoSuchElementException("An error occured");
		}
	}

	public boolean deleteEmployeeById(int id) {
		Employee empl = employeeDAO.findById(id);
		if (empl == null) {
			throw new NoSuchElementException("An error occured");
		}
		boolean executed = employeeDAO.deleteById(id);
		return executed;
	}

	public Employee updateEmployeeById(int id, Employee t) {
		Employee empl = employeeDAO.updateById(id, t);
		if (empl == null) {
			throw new NoSuchElementException("Product with id = " + id + " doesn't exist");
		}
		return empl;
	}
}
