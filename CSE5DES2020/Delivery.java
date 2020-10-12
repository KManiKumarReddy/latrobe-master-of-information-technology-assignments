import java.util.List;
import java.util.ArrayList;

class Delivery implements SimpleKey {
	private String id;
	private Customer customer;
	private Address address;
	private int date;
	private int dayOfWeek;
	private List<DeliveryItem> deliveryItems;

	public Delivery(String id, Customer customer, Address address, int date) {
		this.id = id;
		this.customer = customer;
		this.address = address;
		this.date = date;
		this.dayOfWeek = date % 7;
		deliveryItems = new ArrayList<DeliveryItem>();
	}

	public String getId() {
		return id;
	}

	public String toString() {
		List<String> DeliveryItemDetails = new ArrayList<String>();
		for (DeliveryItem di : deliveryItems) {
			DeliveryItemDetails.add(di.toString());
		}
		String desc = "Delivery[id:" + id + "customer:" + customer.getId() + "address:" + address.getId() + "date:"
				+ date + "dayOfWeek:" + dayOfWeek + "deliveryItems:" + DeliveryItemDetails + "]";
		return desc;
	}

	@Override
	public String getKey() {
		return id;
	}
}
