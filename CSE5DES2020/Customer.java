import java.util.List;
import java.util.ArrayList;

public class Customer implements SimpleKey {
	private String id;
	private String name;
	private List<Address> addresses;
	private List<Order> orders;

	public Customer(String id, String name) {
		this.id = id;
		this.name = name;
		this.addresses = new ArrayList<Address>();
		this.orders = new ArrayList<Order>();
	}

	public Customer(String id, String name, List<Address> addresses, List<Order> orders) {
		this.id = id;
		this.name = name;
		this.addresses = addresses;
		this.orders = orders;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public String toString() {
		List<String> addressDetails = new ArrayList<String>();
		for (Address a : addresses) {
			addressDetails.add(a.getId());
		}
		List<String> orderDetails = new ArrayList<String>();
		for (Order o : orders) {
			orderDetails.add(o.getId());
		}
		String cust = "Customer[id:" + id + "name:" + name + "address:" + addressDetails + "orders:" + orderDetails
				+ "]";
		return cust;
	}

	@Override
	public String getKey() {
		return id;
	}
}