package model;

import java.util.ArrayList;
import java.util.Date;

public class SubOrder {

	private double estimatedWeight;
	private int estimatedLoadingTime;
	private boolean isLoaded = false;
	private Date earliestLoadingTime;
	private Order order;
	private Trailer trailer;
	private ProductType productType;
	private ArrayList<LoadingInfo> loadingInfos;

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

	public Date getEarliestLoadingTime() {
		return earliestLoadingTime;
	}

	public void setEarliestLoadingTime(Date earliestLoadingTime) {
		this.earliestLoadingTime = earliestLoadingTime;
	}

	public ArrayList<LoadingInfo> getLoadingInfos() {
		return new ArrayList<LoadingInfo>(loadingInfos);
	}

	public void addLoadingInfo(LoadingInfo loadingInfo) {
		loadingInfos.add(loadingInfo);
	}

	public void removeLoadingInfo(LoadingInfo loadingInfo) {
		loadingInfos.remove(loadingInfo);
	}

	@Override
	public String toString() {
		return estimatedWeight + " kg " + productType;
	}

}
