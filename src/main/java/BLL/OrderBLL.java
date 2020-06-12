package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BLL.Validators.OrderClientValidator;
import BLL.Validators.OrderEmployeeValidator;
import BLL.Validators.OrderProductValidator;
import BLL.Validators.OrderQuantityValidator;
import BLL.Validators.Validator;
import DAO.OrderDAO;
import Model.Client;
import Model.Order;

/**
 * Clasa business pentru comenzi
 * 
 * @author user
 *
 */
public class OrderBLL {

	private List<Validator<Order>> validators;
	private OrderDAO orderDAO;

	public OrderBLL() {
		validators = new ArrayList<Validator<Order>>();
		validators.add(new OrderClientValidator());
		validators.add(new OrderProductValidator());
		validators.add(new OrderEmployeeValidator());
		validators.add(new OrderQuantityValidator());

		orderDAO = new OrderDAO();
	}

	public List<Validator<Order>> getValidators() {
		return validators;
	}

	public void insert(Order t) {
		Order ord = orderDAO.insert(t);
		if (t == null) {
			throw new NoSuchElementException("Insert error");
		}
	}

}
