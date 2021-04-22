package inventoryManager;

public class Product {
	
	private String productName;
	
	public Product(String productName) {
		super();
		this.productName = productName;	
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Override
	public String toString(){
		return productName ;
	}
	
	public static void main(String[] args) {
		
		

	}

}
