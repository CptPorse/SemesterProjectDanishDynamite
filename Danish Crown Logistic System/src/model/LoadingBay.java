package model;

import java.util.ArrayList;

//Author: Jens Nyberg Porse
public class LoadingBay {

	private int loadingBayNumber;
	private ProductType productType;
	private ArrayList<LoadingInfo> loadingInfos;

	public LoadingBay(int loadingBayNumber, ProductType productType) {
		super();
		this.loadingBayNumber = loadingBayNumber;
		this.productType = productType;
	}

	public int getLoadingBayNumber() {
		return loadingBayNumber;
	}

	public void setLoadingBayNumber(int loadingBayNumber) {
		this.loadingBayNumber = loadingBayNumber;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	/**
	 * Returns a list of LoadingInfo.
	 */
	public ArrayList<LoadingInfo> getLoadingInfos() {
		return new ArrayList<LoadingInfo>(loadingInfos);
	}

	/**
	 * Adds the LoadingInfo to the list.
	 */
	public void addLoadingInfo(LoadingInfo loadingInfo) {
		loadingInfos.add(loadingInfo);
	}

	/**
	 * Removes the LoadingInfo from the list.
	 */
	public void removeLoadingInfo(LoadingInfo loadingInfo) {
		loadingInfos.remove(loadingInfo);
	}
}
