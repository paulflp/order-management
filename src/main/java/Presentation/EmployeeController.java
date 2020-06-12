package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import BLL.ClientBLL;
import BLL.EmployeeBLL;
import BLL.Validators.Validator;
import DAO.TableGenerator;
import Model.Client;
import Model.Employee;
import Presentation.ClientController.ClearListener;
import Presentation.ClientController.DeleteListener;
import Presentation.ClientController.FindListener;
import Presentation.ClientController.InsertListener;
import Presentation.ClientController.ShowAllListener;
import Presentation.ClientController.UpdateListener;

/**
 * Clasa pentru controlul datelor din GUI pentru employees
 * 
 * @author user
 *
 */
public class EmployeeController {
	private EmployeeView view;
	private EmployeeBLL employeeBLL;

	public EmployeeController(EmployeeView view) {
		this.view = view;
		employeeBLL = new EmployeeBLL();

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
					Employee clt = employeeBLL.getEmployeeById(id);
					System.out.println(clt.toString());
				} catch (Exception er) {
					System.out.println(er.getMessage());
				}
				Employee clt = employeeBLL.getEmployeeById(id);
				ArrayList<Employee> list = new ArrayList<Employee>();
				list.add(clt);
				TableGenerator<Employee> tableGen = new TableGenerator<Employee>();
				view.setClientTable(tableGen.createTable(list));
			}

		}
	}

	class InsertListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = view.getUserInputName();
			String address = view.getUserInputAddress();
			String phone = view.getUserInputPhone();
			if (name.equals("blank") || address.equals("blank") || phone.equals("blank")) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				Employee employeeToAdd = new Employee(name, phone, address);
				try {
					employeeBLL.insert(employeeToAdd);
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
					employeeBLL.deleteEmployeeById(idForDelete);
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
			String phone = view.getUserInputPhone();
			if (id == -1 || name.equals("blank") || address.equals("blank") || phone.equals("blank")) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				try {
					Employee clc = new Employee(name, phone, address);
					employeeBLL.updateEmployeeById(id, clc);
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
