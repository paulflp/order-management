package BLL.Validators;

import java.util.NoSuchElementException;

import BLL.ProductBLL;
import Model.Order;
import Model.Product;

/**
 * Clasa care implementeaza interfata validator pentru a verifica daca un produs
 * exista in BD pentru a putea fi pus pe o comanda
 * 
 * @author user
 *
 */
public class OrderProductValidator implements Validator<Order> {

	public void validate(Order t) {
		// TODO Auto-generated method stub
		ProductBLL prodBLL = new ProductBLL();
		try {
			Product prod = prodBLL.findProducttById(t.getIdProduct());
		} catch (NoSuchElementException er) {
			throw new IllegalArgumentException(er.getMessage());
		}
		System.out.println("Order Product checked!");
	}

}
