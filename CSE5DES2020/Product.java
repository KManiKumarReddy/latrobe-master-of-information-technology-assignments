public class Product implements SimpleKey {
	private String id;
	private String description;

	public Product(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return "Product[id:" + id + ",description" + description + "]";
	}

	@Override
	public String getKey() {
		return id;
	}
}