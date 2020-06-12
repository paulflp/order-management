package Model;

/**
 * Clasa Product modeleaza tabelul product din DB. Atributele corespund cu
 * coloanele din tabel.
 * 
 * @author Paul Filip
 *
 */
public class Product {
	private int id;
	private String name;
	private float price;
	private int quantity;
	private String producer;

	public Product() {

	}

	public Product(int id, String name, float price, int quantity, String producer) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.producer = producer;
	}

	public Product(String name, float price, int quantity, String producer) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.producer = producer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", producer="
				+ producer + "]";
	}

}
