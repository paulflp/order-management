package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import BLL.ProductBLL;
import BLL.Validators.Validator;
import DAO.TableGenerator;
import Model.Product;
import Presentation.ClientController.ShowAllListener;

/**
 * Clasa Controller pentru controlul datelor venite din GUI pentru produse
 * 
 * @author user
 *
 */
public class ProductController {
	private ProductView view;
	private ProductBLL productBLL;

	public ProductController(ProductView view) {
		this.view = view;
		productBLL = new ProductBLL();

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
					Product prod = productBLL.findProducttById(id);
					System.out.println(prod.toString());
				} catch (Exception er) {
					System.out.println(er.getMessage());
				}
				Product prd = productBLL.findProducttById(id);
				ArrayList<Product> list = new ArrayList<Product>();
				list.add(prd);
				TableGenerator<Product> tableGen = new TableGenerator<Product>();
				view.setClientTable(tableGen.createTable(list));
			}
		}
	}

	class InsertListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = view.getUserInputName();
			float price = view.getUserInputPrice();
			int quantity = view.getUserInputQuantity();
			String producer = view.getUserInputProducer();
			if (name.equals("blank") || price == -1.0f || quantity == -1 || producer.equals("blank")) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				Product productToAdd = new Product(name, price, quantity, producer);
				try {
					for (Validator<Product> val : productBLL.getValidators()) {
						val.validate(productToAdd);
					}
					productBLL.insert(productToAdd);
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
					productBLL.deleteProductById(idForDelete);
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
			float price = view.getUserInputPrice();
			int quantity = view.getUserInputQuantity();
			String producer = view.getUserInputProducer();
			if (name.equals("blank") || price == -1.0f || quantity == -1 || producer.equals("blank")) {
				JOptionPane.showMessageDialog(null, "Provide valide data!");
			} else {
				try {
					Product newProd = new Product(name, price, quantity, producer);
					for (Validator<Product> val : productBLL.getValidators()) {
						val.validate(newProd);
					}
					productBLL.updateProductById(id, newProd);
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
