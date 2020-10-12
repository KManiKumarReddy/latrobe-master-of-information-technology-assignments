import java.util.*;

public class StandingOrderSystem {
	List<Product> productList;
	List<Customer> customerList;
	List<Order> orderList;
	List<Delivery> deliveryList;
	List<Invoice> invoiceList;
	List<Address> addressList;

	public StandingOrderSystem() {
		productList = new ArrayList<Product>();
		customerList = new ArrayList<Customer>();
		orderList = new ArrayList<Order>();
		deliveryList = new ArrayList<Delivery>();
		invoiceList = new ArrayList<Invoice>();
		addressList = new ArrayList<Address>();
	}

	public String toString() {
		String desc = "StandingOrderSystem[" + "\n productList:" + productList + "\n customerList:" + customerList
				+ "\n orderList:" + orderList + "\n deliveryList:" + deliveryList + "\n invoiceList:" + invoiceList
				+ "\n addressList:" + addressList + "\n]";
		return desc;
	}

	public void addProduct(String id, String description) throws Exception {
		Product product = (Product) Helper.search(productList, id);
		boolean pre = (product == null);
		if (!pre) {
			String msg = "ERROR MESSAGE: The product id " + id + " already exists!";
			System.out.println(msg);
			throw new Exception(msg);
		}
		product = new Product(id, description);
		productList.add(product);
	}

	public void addCustomer(String id, String name, String addressId, String line1, String line2, String contactPerson,
			String contactPhone) throws Exception {
		Customer customer = (Customer) Helper.search(customerList, id);
		boolean pre = (customer == null);
		if (!pre) {
			String msg = "ERROR MESSAGE: The customer id " + id + " already exists!";
			System.out.println(msg);
			throw new Exception(msg);
		}
		Address address = (Address) Helper.search(addressList, addressId);
		boolean pre1 = (address == null);
		if (!pre1) {
			String msg1 = "ERROR MESSAGE: The address id " + addressId + " already exists!";
			System.out.println(msg1);
			throw new Exception(msg1);
		}

		// create new address
		address = new Address(addressId, line1, line2, contactPerson, contactPhone);
		// add address to address list
		addressList.add(address);

		// create new customer
		customer = new Customer(id, name);
		// set Address to Customer
		customer.addAddress(address);
		// add customer to customerList
		customerList.add(customer);
	}

}