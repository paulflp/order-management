package Presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Clasa ce implementeaza GUI pentru comenzi
 * 
 * @author user
 *
 */
public class OrderView extends JFrame {
	private JButton generateBtn = new JButton("Generate Order");
	private JButton clearBtn = new JButton("Clear");
	private JTextField clientIdTf = new JTextField(20);
	private JTextField productIdTf = new JTextField(20);
	private JTextField employeeIdTf = new JTextField(20);
	private JTextField quantityTf = new JTextField(20);

	public OrderView() {
		this.setTitle("Order Frame");
		this.setSize(400, 500);
		this.setLayout(new GridLayout(5, 2));
		this.add(new JLabel("Client ID: "));
		this.add(clientIdTf);

		this.add(new JLabel("Product ID: "));
		this.add(productIdTf);

		this.add(new JLabel("Employee ID: "));
		this.add(employeeIdTf);

		this.add(new JLabel("Quantity: "));
		this.add(quantityTf);

		this.add(generateBtn);
		this.add(clearBtn);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public int getUserInputClientId() {
		try {
			String text = clientIdTf.getText();
			int valueForId = Integer.parseInt(text);
			return valueForId;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public int getUserInputProductId() {
		try {
			String text = productIdTf.getText();
			int valueForId = Integer.parseInt(text);
			return valueForId;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public int getUserInputEmployeeId() {
		try {
			String text = employeeIdTf.getText();
			int valueForId = Integer.parseInt(text);
			return valueForId;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public int getUserInputQuantity() {
		try {
			String text = quantityTf.getText();
			int valueForId = Integer.parseInt(text);
			return valueForId;
		} catch (NullPointerException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public void addGenerateListener(ActionListener mal) {
		generateBtn.addActionListener(mal);
	}

	public void addClearListener(ActionListener mal) {
		clearBtn.addActionListener(mal);
	}

	public void reset() {
		clientIdTf.setText("");
		productIdTf.setText("");
		employeeIdTf.setText("");
		quantityTf.setText("");
	}
}
