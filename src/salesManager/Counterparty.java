package salesManager;

public class Counterparty {
	
	private String counterpartyName ;
	

	public Counterparty(String name) {
		super();
		this.counterpartyName = name;
	}
	
	
	public String getCounterpartyName() {
		return counterpartyName;
	}

	
	public void setCounterpartyName(String buyersName) {
		this.counterpartyName = buyersName;
	}


	@Override
	public String toString(){
		return counterpartyName; 
	}

	public static void main(String[] args) {
	
		Counterparty counterparty1 = new Counterparty("Vitatrade");
		System.out.println(counterparty1.getCounterpartyName());

	}

}
