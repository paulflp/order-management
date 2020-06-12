package BLL.Validators;

import BLL.ProductBLL;
import Model.Order;
import Model.Product;

/**
 * Clasa care implementeaza interfata Validator pentru a verifica daca
 * cantitatea dorita exista in BD
 * 
 * @author user
 *
 */
public class OrderQuantityValidator implements Validator<Order> {

	public void validate(Order t) {
		// TODO Auto-generated method stub
		if (t.getQuantity() < 0) {
			throw new IllegalArgumentException("Negative quantity is invalid");
		} else {
			ProductBLL prodBLL = new ProductBLL();
			Product prod = prodBLL.findProducttById(t.getIdProduct());
			if (prod != null) {
				if (prod.getQuantity() < t.getQuantity()) {
					throw new IllegalArgumentException("Desired quantity is unavailable!");
				}
			} else {
				throw new IllegalArgumentException("Product with id = " + t.getIdProduct() + " doesn't exist");
			}
		}
		System.out.println("Order qty checked!");
	}

}
