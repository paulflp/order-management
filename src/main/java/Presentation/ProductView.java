package Presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import BLL.ClientBLL;
import BLL.ProductBLL;
import DAO.TableGenerator;
import Model.Client;
import Model.Product;

/**
 * Clasa care implementeaza GUI pentru produse
 * 
 * @author user
 *
 */
public class ProductView extends JFrame {
	private JButton findBtn = new JButton("Find");
	private JButton insertBtn = new JButton("Insert");
	private JButton deleteBtn = new JButton("Delete");
	private JButton updateBtn = new JButton("Update");
	private JButton clearBtn = new JButton("Clear");
	private JButton showAllBtn = new JButton("Show All Data");
	private JTable clientTable;
	private JTextField idTf = new JTextField(20);
	private JTextField nameTf = new JTextField(20);
	private JTextField priceTf = new JTextField(20);
	private JTextField quantityTf = new JTextField(20);
	private JTextField producerTf = new JTextField(20);
	private JPanel panelForTable = new JPanel();

	public ProductView() {
		this.setSize(700, 800);
		this.setTitle("Product Frame");
		this.setLayout(new GridLayout(3, 1));
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(5, 1));

		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout());
		idPanel.add(new JLabel("Introduce ID: "));
		idPanel.add(idTf);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(new JLabel("Introduce name: "));
		namePanel.add(nameTf);

		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new FlowLayout());
		pricePanel.add(new JLabel("Introduce price: "));
		pricePanel.add(priceTf);

		JPanel quantityPanel = new JPanel();
		quantityPanel.setLayout(new FlowLayout());
		quantityPanel.add(new JLabel("Introduce quantity: "));
		quantityPanel.add(quantityTf);

		JPanel producerPanel = new JPanel();
		producerPanel.setLayout(new FlowLayout());
		producerPanel.add(new JLabel("Introduce producer: "));
		producerPanel.add(producerTf);

		dataPanel.add(idPanel);
		dataPanel.add(namePanel);
		dataPanel.add(pricePanel);
		dataPanel.add(quantityPanel);
		dataPanel.add(producerPanel);

		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(new FlowLayout());

		operationsPanel.add(showAllBtn);
		operationsPanel.add(findBtn);
		operationsPanel.add(insertBtn);
		operationsPanel.add(deleteBtn);
		operationsPanel.add(updateBtn);
		operationsPanel.add(clearBtn);

		clientTable = new JTable();
		JScrollPane tablePanel = new JScrollPane(clientTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelForTable.add(tablePanel);

		this.add(dataPanel);
		this.add(operationsPanel);

		this.add(panelForTable);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addShowAllListener(ActionListener lst) {
		showAllBtn.addActionListener(lst);
	}

	public void addFindListener(ActionListener lst) {
		findBtn.addActionListener(lst);
	}

	public void addInsertListener(ActionListener lst) {
		insertBtn.addActionListener(lst);
	}

	public void addDeleteListener(ActionListener lst) {
		deleteBtn.addActionListener(lst);
	}

	public void addUpdateListener(ActionListener lst) {
		updateBtn.addActionListener(lst);
	}

	public void addClearListener(ActionListener lst) {
		clearBtn.addActionListener(lst);
	}

	public int getUserInputId() {
		try {
			String text = idTf.getText();
			int valueForId = Integer.parseInt(text);
			return valueForId;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String getUserInputName() {
		try {
			String text = nameTf.getText();
			if (text.equals("")) {
				throw new NullPointerException();
			}
			return text;
		} catch (NullPointerException e) {
			return new String("blank");
		}
	}

	public float getUserInputPrice() {
		try {
			String text = priceTf.getText();
			float valueForPrice = Float.parseFloat(text);
			return valueForPrice;
		} catch (NullPointerException e) {
			return -1.0f;
		} catch (NumberFormatException e) {
			return -1.0f;
		}
	}

	public int getUserInputQuantity() {
		try {
			String text = quantityTf.getText();
			int valueForQuantity = Integer.parseInt(text);
			return valueForQuantity;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String getUserInputProducer() {
		try {
			String text = producerTf.getText();
			if (text.equals("")) {
				throw new NullPointerException();
			}
			return text;
		} catch (NullPointerException e) {
			return new String("blank");
		}
	}

	public void reset() {
		idTf.setText("");
		nameTf.setText("");
		priceTf.setText("");
		quantityTf.setText("");
		producerTf.setText("");
	}

	public JTable getClientTable() {
		return clientTable;
	}

	public void setClientTable(JTable clientTable) {
		this.getPanelForTable().removeAll();

		this.clientTable = clientTable;

		JScrollPane tablePanel = new JScrollPane(clientTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelForTable.add(tablePanel);
		panelForTable.revalidate();
	}

	public void repaintTable() {

		TableGenerator<Product> tableGenerator = new TableGenerator<Product>();
		ProductBLL productBLL = new ProductBLL();
		List<Product> list = productBLL.findAllProducts();
		this.setClientTable(tableGenerator.createTable(list));

	}

	public JPanel getPanelForTable() {
		return panelForTable;
	}

	public void setPanelForTable(JPanel panelForTable) {
		this.panelForTable = panelForTable;
	}
}
