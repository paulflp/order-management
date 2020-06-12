package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import BLL.ClientBLL;
import BLL.Validators.Validator;
import DAO.TableGenerator;
import Model.Client;

/**
 * Clasa pentru controlul datelor din GUI pentru clienti
 * 
 * @author user
 *
 */
public class ClientController {
	private ClientView view;
	private ClientBLL clientBLL;

	public ClientController(ClientView view) {
		this.view = view;
		clientBLL = new ClientBLL();

		view.addShowAllListener(new ShowAllListener());
		view.addFindListener(new FindListener());
		view.addInsertListener(new InsertListener());
		view.addDeleteListener(new DeleteListener());
		view.addUpdateListener(new UpdateListener());
		view.addClearListener(new ClearListener());
	}

	class ShowAllListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.repaintTable();
			view.getClientTable().repaint();

		}

	}

	class FindListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = view.getUserInputId();
			if (id == -1) {
				JOptionPane.showMessageDialog(null, "ID field has invalid data!");
			} else {
				try {
					Client clt = clientBLL.findClientById(id);
					System.out.println(clt.toString());
				} catch (Exception er) {
					System.out.println(er.getMessage());
				}
				Client clt = clientBLL.findClientById(id);
				ArrayList<Client> list = new ArrayList<Client>();
				list.add(clt);
				TableGenerator<Client> tableGen = new TableGenerator<Client>();
				view.setClientTable(tableGen.createTable(list));
			}

		}
	}

	class InsertListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = view.getUserInputName();
			String address = view.getUserInputAddress();
			String email = view.getUserInputEmail();
			int age = view.getUserInputAge();
			if (name.equals("blank") || address.equals("blank") || email.equals("blank") || age == -1) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				Client clientToAdd = new Client(name, address, email, age);
				try {
					for (Validator<Client> val : clientBLL.getValidators()) {
						val.validate(clientToAdd);
					}
					clientBLL.insert(clientToAdd);
				} catch (NoSuchElementException er) {
					System.out.println(er.getMessage());
				} catch (IllegalArgumentException er) {
					System.out.println(er.getMessage());
				}
				view.repaintTable();
				view.getClientTable().repaint();

			}
		}
	}

	class DeleteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int idForDelete = view.getUserInputId();
			if (idForDelete == -1) {
				JOptionPane.showMessageDialog(null, "ID field has invalid data!");
			} else {
				try {
					clientBLL.deleteClientById(idForDelete);
				} catch (NoSuchElementException er) {
					System.out.println(er.getMessage());
				}
				view.repaintTable();
				view.getClientTable().repaint();
			}
		}

	}

	class UpdateListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = view.getUserInputId();
			String name = view.getUserInputName();
			String address = view.getUserInputAddress();
			String email = view.getUserInputEmail();
			int age = view.getUserInputAge();
			if (id == -1 || age == -1 || name.equals("blank") || address.equals("blank")) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				try {
					Client clc = new Client(name, address, email, age);
					for (Validator<Client> val : clientBLL.getValidators()) {
						val.validate(clc);
					}
					clientBLL.updateClientById(id, clc);
				} catch (NoSuchElementException er) {
					System.out.println(er.getMessage());
				} catch (IllegalArgumentException er) {
					System.out.println(er.getMessage());
				}
				view.repaintTable();
				view.getClientTable().repaint();
			}
		}

	}

	class ClearListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.reset();
			view.repaintTable();
			view.getClientTable().repaint();
		}

	}
}
