public class Order implements SimpleKey {
	private String id;
	private Customer customer;
	private Address address;
	private Product product;
	private double price;
	private int[] quantities;
	private int startDate;
	private int endDate;
	private OrderStatus status;

	public Order(String id, Customer customer, Address address, Product product, double price, int[] quantities,
			int startDate, int endDate, OrderStatus status) {
		this.id = id;
		this.customer = customer;
		this.address = address;
		this.product = product;
		this.price = price;
		this.quantities = quantities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		String desc = "Order[id:" + id + "customer:" + customer.getId() + "address:" + address.getId() + "product:"
				+ product.getId() + "price:" + price + "quantities:" + quantities + "startDate:" + startDate
				+ "endDate:" + endDate + "status:" + status + "]";
		return desc;
	}

	@Override
	public String getKey() {
		return id;
	}
}
