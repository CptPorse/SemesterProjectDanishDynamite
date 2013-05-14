package model;

public class SubOrder {

	private double estimatedWeight;
	private int estimatedLoadingTime;
	private boolean isLoaded = false;
	private Order order;
	private Trailer trailer;
	private ProductType productType;

	public SubOrder(double estimatedWeight, Trailer trailer,
			ProductType productType) {
		super();
		this.estimatedWeight = estimatedWeight;
		this.trailer = trailer;
		this.productType = productType;
		this.estimatedLoadingTime = (int) (this.productType
				.getminuteToKiloRatio() * this.estimatedWeight);
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public double getEstimatedWeight() {
		return estimatedWeight;
	}

	public void setEstimatedWeight(double estimatedWeight) {
		this.estimatedWeight = estimatedWeight;
	}

	public int getEstimatedLoadingTime() {
		return estimatedLoadingTime;
	}

	public void setEstimatedLoadingTime(int estimatedLoadingTime) {
		this.estimatedLoadingTime = estimatedLoadingTime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return estimatedWeight + " kg " + productType;
	}

}
