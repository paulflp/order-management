package BLL.Validators;

import Model.Product;

/**
 * Clasa pentru verificarea pretului unui produs daca este strict pozitiv
 * 
 * @author user
 *
 */
public class ProductPriceValidator implements Validator<Product> {

	public void validate(Product t) {
		// TODO Auto-generated method stub
		if (t.getPrice() < 0.0f) {
			throw new IllegalArgumentException("Negative price excpetion");
		}

	}

}
