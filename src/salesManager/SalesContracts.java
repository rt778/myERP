package salesManager;


public class SalesContracts {
	
	private String contractId;
	private String contractDate;
	private String counterpartyName;
	private double quantitySold;
	private String productSold;
	private double price;
	private String deliveryTerms;
	private String deliveryPeriodStart;
	private String deliveryPeriodEnd;
	private String paymentTerms;
	



	public String getContractDate() {
		return contractDate;
	}




	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}



	public String getCounterpartyName() {
		return counterpartyName;
	}



	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}




	public double getQuantitySold() {
		return quantitySold;
	}



	public void setQuantitySold(double quantitySold) {
		this.quantitySold = quantitySold;
	}




	public String getProductSold() {
		return productSold;
	}



	public void setProductSold(String productSold) {
		this.productSold = productSold;
	}




	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getDeliveryTerms() {
		return deliveryTerms;
	}




	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}



	public String getDeliveryPeriodStart() {
		return deliveryPeriodStart;
	}



	public void setDeliveryPeriodStart(String deliveryPeriodStart) {
		this.deliveryPeriodStart = deliveryPeriodStart;
	}




	public String getDeliveryPeriodEnd() {
		return deliveryPeriodEnd;
	}



	public void setDeliveryPeriodEnd(String deliveryPeriodEnd) {
		this.deliveryPeriodEnd = deliveryPeriodEnd;
	}



	public String getPaymentTerms() {
		return paymentTerms;
	}



	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}



	public String getContractId() {
		return contractId;
	}



	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Override
	public String toString(){
		return ""
				+ contractId + "," 
				+ contractDate + "," 
				+ counterpartyName + ","
				+ quantitySold + "," 
				+ productSold + "," 
				+ price + "," 
				+ deliveryTerms  + "," 
				+ deliveryPeriodStart + "," 
				+ deliveryPeriodEnd + "," 
				+ paymentTerms;
	}

	public static void main(String[] args) {
		
		SalesContracts ct1 = new SalesContracts();
		
		ct1.setContractDate("2017-07-17");
		ct1.setCounterpartyName("Citytrade");
		ct1.setProductSold("BK");
		ct1.setQuantitySold(25.92);
		ct1.setPrice(1500.00);
		ct1.setDeliveryTerms("CIF Shanghai");
		ct1.setDeliveryPeriodStart("2017-08-01");
		ct1.setDeliveryPeriodEnd("2017-08-31");
		ct1.setPaymentTerms("L/C at sight");

		
		System.out.println(ct1.getContractDate());
		
		
		

	}

}
