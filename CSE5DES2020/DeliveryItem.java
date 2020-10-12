public class DeliveryItem {
	private Order order;
	private int quantity;
	private int difference;

	public DeliveryItem(Order order, int quantity, int difference) {
		this.order = order;
		this.quantity = quantity;
		this.difference = difference;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getDifference() {
		return difference;
	}

	public String toString() {
		return "DeliveryItem[order:" + order.getId() + "quantity:" + quantity + "difference:" + difference + "]";
	}
}
