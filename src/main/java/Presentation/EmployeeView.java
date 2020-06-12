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
import BLL.EmployeeBLL;
import DAO.TableGenerator;
import Model.Client;
import Model.Employee;

/**
 * Clasa pentru implementarea GUI employees
 * 
 * @author user
 *
 */
public class EmployeeView extends JFrame {
	private JButton findBtn = new JButton("Find");
	private JButton insertBtn = new JButton("Insert");
	private JButton deleteBtn = new JButton("Delete");
	private JButton updateBtn = new JButton("Update");
	private JButton clearBtn = new JButton("Clear");
	private JButton showAllBtn = new JButton("Show All Data");
	private JTable clientTable;
	private JTextField idTf = new JTextField(20);
	private JTextField nameTf = new JTextField(20);
	private JTextField addressTf = new JTextField(20);
	private JTextField phoneTf = new JTextField(20);

	private JPanel panelForTable = new JPanel();

	public EmployeeView() {
		this.setSize(700, 800);
		this.setTitle("Employee Frame");
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

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout());
		phonePanel.add(new JLabel("Introduce phone: "));
		phonePanel.add(phoneTf);

		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout());
		addressPanel.add(new JLabel("Introduce address: "));
		addressPanel.add(addressTf);

		dataPanel.add(idPanel);
		dataPanel.add(namePanel);
		dataPanel.add(phonePanel);
		dataPanel.add(addressPanel);

		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(new FlowLayout());

		operationsPanel.add(showAllBtn);
		operationsPanel.add(findBtn);
		operationsPanel.add(insertBtn);
		operationsPanel.add(deleteBtn);
		operationsPanel.add(updateBtn);
		operationsPanel.add(clearBtn);

		clientTable = new JTable();
		// this.repaintTable();
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

	public String getUserInputAddress() {
		try {
			String text = addressTf.getText();
			if (text.equals("")) {
				throw new NullPointerException();
			}
			return text;
		} catch (NullPointerException e) {
			return new String("blank");
		}
	}

	public String getUserInputPhone() {
		try {
			String text = phoneTf.getText();
			return text;
		} catch (NullPointerException e) {
			return new String("blank");
		}
	}

	public void reset() {
		idTf.setText("");
		nameTf.setText("");
		addressTf.setText("");
		phoneTf.setText("");
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

		TableGenerator<Employee> tableGenerator = new TableGenerator<Employee>();
		EmployeeBLL employeeBLL = new EmployeeBLL();
		List<Employee> list = employeeBLL.findAllEmployees();
		this.setClientTable(tableGenerator.createTable(list));

	}

	public JPanel getPanelForTable() {
		return panelForTable;
	}

	public void setPanelForTable(JPanel panelForTable) {
		this.panelForTable = panelForTable;
	}
}
