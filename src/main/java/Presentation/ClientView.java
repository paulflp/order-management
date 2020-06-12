package Presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import DAO.TableGenerator;
import Model.Client;

/**
 * Clasa ClientView pentru interfata grafica pentru lucrul cu tabelul client din
 * BD
 * 
 * @author user
 *
 */
public class ClientView extends JFrame {
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
	private JTextField emailTf = new JTextField(20);
	private JTextField ageTf = new JTextField(20);
	private JPanel panelForTable = new JPanel();

	public ClientView() {
		this.setSize(700, 800);
		this.setTitle("Client Frame");
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

		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout());
		addressPanel.add(new JLabel("Introduce address: "));
		addressPanel.add(addressTf);

		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		emailPanel.add(new JLabel("Introduce email: "));
		emailPanel.add(emailTf);

		JPanel agePanel = new JPanel();
		agePanel.setLayout(new FlowLayout());
		agePanel.add(new JLabel("Introduce age: "));
		agePanel.add(ageTf);

		dataPanel.add(idPanel);
		dataPanel.add(namePanel);
		dataPanel.add(addressPanel);
		dataPanel.add(emailPanel);
		dataPanel.add(agePanel);

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

	/**
	 * Adaugare listener pentru butonul de show all
	 * 
	 * @param lst
	 *            listener
	 */
	public void addShowAllListener(ActionListener lst) {
		showAllBtn.addActionListener(lst);
	}

	/**
	 * Adaugare listener pentru butonul de find
	 * 
	 * @param lst
	 *            listener
	 */
	public void addFindListener(ActionListener lst) {
		findBtn.addActionListener(lst);
	}

	/**
	 * Adaugare listener pentru butonul de insert
	 * 
	 * @param lst
	 *            listener
	 */
	public void addInsertListener(ActionListener lst) {
		insertBtn.addActionListener(lst);
	}

	/**
	 * Adaugare listener pentru butonul de delete
	 * 
	 * @param lst
	 *            listener
	 */
	public void addDeleteListener(ActionListener lst) {
		deleteBtn.addActionListener(lst);
	}

	/**
	 * Adaugare listener pentru butonul de update
	 * 
	 * @param lst
	 *            listener
	 */
	public void addUpdateListener(ActionListener lst) {
		updateBtn.addActionListener(lst);
	}

	/**
	 * Adaugare listener pentru butonul de clear
	 * 
	 * @param lst
	 *            listener
	 */
	public void addClearListener(ActionListener lst) {
		clearBtn.addActionListener(lst);
	}

	/**
	 * 
	 * @return intreg ce reprezinta valoarea adaugata in text field
	 */
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

	/**
	 * 
	 * @return String ce reprezinta numele adaugat in text field
	 */
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

	/**
	 * 
	 * @return String ce reprezinta adresa scrisa in text field
	 */
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

	/**
	 * 
	 * @return String ce reprezinta email-ul scris in text field
	 */
	public String getUserInputEmail() {
		try {
			String text = emailTf.getText();
			return text;
		} catch (NullPointerException e) {
			return new String("blank");
		}
	}

	/**
	 * 
	 * @return intreg ce reprezinta varsta introdusa
	 */
	public int getUserInputAge() {
		try {
			String text = ageTf.getText();
			int valueForAge = Integer.parseInt(text);
			return valueForAge;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * reseteaza campurile pentru introducerea datelor
	 */
	public void reset() {
		idTf.setText("");
		nameTf.setText("");
		addressTf.setText("");
		emailTf.setText("");
		ageTf.setText("");
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

	/**
	 * face update la JTable ce se afiseaza in GUI
	 */
	public void repaintTable() {

		TableGenerator<Client> tableGenerator = new TableGenerator<Client>();
		ClientBLL clientBLL = new ClientBLL();
		List<Client> list = clientBLL.findAllClients();
		this.setClientTable(tableGenerator.createTable(list));

	}

	public JPanel getPanelForTable() {
		return panelForTable;
	}

	public void setPanelForTable(JPanel panelForTable) {
		this.panelForTable = panelForTable;
	}

}
