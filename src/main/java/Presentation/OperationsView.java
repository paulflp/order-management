package Presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Clasa pentru GUI meniu principal
 * 
 * @author user
 *
 */
public class OperationsView extends JFrame {
	private JButton clientsBtn = new JButton("Manage Clients");
	private JButton productsBtn = new JButton("Manage Products");
	private JButton employeesBtn = new JButton("Manage Employees");
	private JButton ordersBtn = new JButton("Manage Orders");
	private ClientView clientView;
	private ClientController clientController;
	private ProductView productView;
	private ProductController productController;
	private OrderView orderView;
	private OrderController orderController;
	private EmployeeView employeeView;
	private EmployeeController employeeController;

	public OperationsView() {
		this.setTitle("Main Frame");
		this.setSize(600, 300);
		this.setResizable(false);
		this.setLayout(new GridLayout(4, 0));
		this.add(clientsBtn);
		this.add(productsBtn);
		this.add(employeesBtn);
		this.add(ordersBtn);

		clientsBtn.addActionListener(new ClientsListener());
		productsBtn.addActionListener(new ProductsListener());
		employeesBtn.addActionListener(new EmployeesListener());
		ordersBtn.addActionListener(new OrdersListener());

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ClientsListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			clientView = new ClientView();
			clientController = new ClientController(clientView);
		}
	}

	class ProductsListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			productView = new ProductView();
			productController = new ProductController(productView);
		}
	}

	class OrdersListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			orderView = new OrderView();
			orderController = new OrderController(orderView);
		}
	}

	class EmployeesListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			employeeView = new EmployeeView();
			employeeController = new EmployeeController(employeeView);
		}

	}

}
