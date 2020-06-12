package BLL.Validators;

import Model.Client;

/**
 * implementeaza validator, verifica daca un client are varsta eligibila pentru
 * a putea fi stocat in BD
 * 
 * @author user
 *
 */
public class ClientAgeValidator implements Validator<Client> {

	public void validate(Client t) {
		// TODO Auto-generated method stub
		if (t.getAge() < 18 || t.getAge() > 85) {
			throw new IllegalArgumentException("Age constraint violated");
		}
	}

}
