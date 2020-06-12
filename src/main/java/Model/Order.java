package Model;

import java.sql.Date;

/**
 * Clasa Order modeleaza tabelul order din DB. Atributele corespund cu coloanele
 * din tabel.
 * 
 * @author Paul Filip
 *
 */
public class Order {
	private int id;
	private int idClient;
	private int idProduct;
	private int quantity;
	private Date date;
	private int idEmployee;

	public Order() {

	}

	public Order(int id, int idClient, int idProduct, int quantity, Date date, int idEmployee) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.date = date;
		this.setIdEmployee(idEmployee);
	}

	public Order(int idClient, int idProduct, int quantity, Date date, int idEmployee) {
		super();
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.date = date;
		this.setIdEmployee(idEmployee);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

}
