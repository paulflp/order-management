package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BLL.Validators.ClientAgeValidator;
import BLL.Validators.ClientEmailValidator;
import BLL.Validators.Validator;
import DAO.ClientDAO;
import Model.Client;

/**
 * Clasa Business pentru clienti
 * 
 * @author user
 *
 */
public class ClientBLL {
	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientAgeValidator());
		validators.add(new ClientEmailValidator());

		clientDAO = new ClientDAO();
	}

	public List<Validator<Client>> getValidators() {
		return validators;
	}

	public List<Client> findAllClients() {
		List<Client> list = clientDAO.findAll();
		return list;
	}

	public Client findClientById(int id) {
		Client customer = clientDAO.findById(id);
		if (customer == null) {
			throw new NoSuchElementException("Client with id = " + id + " doesn't exist");
		}
		return customer;
	}

	public void insert(Client customer) {
		Client cst = clientDAO.insert(customer);
		if (cst == null) {
			throw new NoSuchElementException("Insert error");
		}
	}

	public boolean deleteClientById(int id) {
		Client clt = clientDAO.findById(id);
		if (clt == null) {
			throw new NoSuchElementException("Client with id = " + id + " doesn't exist");
		}
		boolean executed = clientDAO.deleteById(id);
		return executed;

	}

	public Client updateClientById(int id, Client customer) {
		Client cst = clientDAO.updateById(id, customer);
		if (cst == null) {
			throw new NoSuchElementException("Client with id = " + id + " doesn't exist");
		}
		return cst;
	}
}
