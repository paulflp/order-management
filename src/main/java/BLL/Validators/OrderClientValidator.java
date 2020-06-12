package BLL.Validators;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import BLL.ClientBLL;
import Model.Client;
import Model.Order;

/**
 * Clasa care implementeaza interfata Validator pentru a verifica daca clientul
 * pentru care se emite factura exista in BD
 * 
 * @author user
 *
 */
public class OrderClientValidator implements Validator<Order> {

	public void validate(Order t) {
		// TODO Auto-generated method stub
		ClientBLL clientBLL = new ClientBLL();
		try {
			Client customer = clientBLL.findClientById(t.getIdClient());
		} catch (NoSuchElementException er) {
			throw new IllegalArgumentException(er.getMessage());
		}
		System.out.println("Order Client checked!");
	}

}
