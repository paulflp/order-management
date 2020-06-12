package BLL.Validators;

import Model.Product;

/**
 * Clasa pentru a verifica daca cantitatea data a unui produs este reala (sa nu
 * fie negativa)
 * 
 * @author user
 *
 */
public class ProductQuantityValidator implements Validator<Product> {

	public void validate(Product t) {
		// TODO Auto-generated method stub
		if (t.getQuantity() < 0) {
			throw new IllegalArgumentException("Negative quantity is invalid");
		}
	}

}
