package inventoryManager;

public class StoragePlace {
	
	private String storagePlaceName;

	public StoragePlace(String storagePlaceName) {
		super();
		this.storagePlaceName = storagePlaceName;	
	}
	
	
	public String getStoragePlaceName() {
		return storagePlaceName;
	}


	public void setStoragePlaceName(String storagePlaceName) {
		this.storagePlaceName = storagePlaceName;
	}


	@Override
	public String toString(){
		return storagePlaceName ;
	}

	public static void main(String[] args) {
	
		StoragePlace STP = new StoragePlace("Saint Petersburg");
		System.out.println(STP);

	}

}
