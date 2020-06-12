package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BLL.Validators.ProductPriceValidator;
import BLL.Validators.ProductQuantityValidator;
import BLL.Validators.Validator;
import Model.Client;
import Model.Product;
import DAO.ProductDAO;

/**
 * Clasa business pentru produse
 * 
 * @author user
 *
 */
public class ProductBLL {
	private List<Validator<Product>> validators;
	private ProductDAO productDAO;

	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductPriceValidator());
		validators.add(new ProductQuantityValidator());

		productDAO = new ProductDAO();
	}

	public List<Validator<Product>> getValidators() {
		return validators;
	}

	public List<Product> findAllProducts() {
		List<Product> list = productDAO.findAll();
		return list;
	}

	public Product findProducttById(int id) {
		Product prd = productDAO.findById(id);
		if (prd == null) {
			throw new NoSuchElementException("Product with id = " + id + " doesn't exist");
		}
		return prd;
	}

	public void insert(Product product) {
		Product prd = productDAO.insert(product);
		if (prd == null) {
			throw new NoSuchElementException("Insert error");
		}
	}

	public boolean deleteProductById(int id) {
		Product prod = productDAO.findById(id);
		if (prod == null) {
			throw new NoSuchElementException("An error occured");
		}
		boolean executed = productDAO.deleteById(id);
		return executed;

	}

	public Product updateProductById(int id, Product product) {
		Product prd = productDAO.updateById(id, product);
		if (prd == null) {
			throw new NoSuchElementException("Product with id = " + id + " doesn't exist");
		}
		return prd;
	}
}
