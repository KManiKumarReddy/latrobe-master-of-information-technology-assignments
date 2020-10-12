import java.util.ArrayList;
import java.util.List;

public class Invoice implements SimpleKey {
	private String id;
	private int fromDate;
	private int toDate;
	private Customer customer;
	private List<Delivery> deliveries;
	private double totalCost;
	private int payDate;
	private InvoiceStatus status;

	public Invoice(String id, int fromDate, int toDate, Customer customer, double totalCost, int payDate,
			InvoiceStatus status) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.customer = customer;
		deliveries = new ArrayList<Delivery>();
		this.totalCost = totalCost;
		this.payDate = payDate;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		List<String> deliveryDetails = new ArrayList<String>();
		for (Delivery d : deliveries) {
			deliveryDetails.add(d.getId());
		}
		String desc = "Invoice[id:" + id + "fromDate:" + fromDate + "toDate:" + toDate + "customer:" + customer.getId()
				+ "deliveries:" + deliveryDetails + "totalCost:" + totalCost + "payDate:" + payDate + "status:" + status
				+ "]";
		return desc;
	}

	@Override
	public String getKey() {
		return id;
	}
}