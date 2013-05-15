package model;

import java.util.ArrayList;
import java.util.Date;

//Author: Jens Nyberg Porse
public class LoadingBay {

	private int loadingBayNumber;
	private boolean isloading;
	private ProductType productType;
	private ArrayList<LoadingInfo> loadingInfos;

	public LoadingBay(int loadingBayNumber, ProductType productType) {
		super();
		this.loadingBayNumber = loadingBayNumber;
		this.productType = productType;
		loadingInfos = new ArrayList<>();
	}

	public int getLoadingBayNumber() {
		return loadingBayNumber;
	}

	public void setLoadingBayNumber(int loadingBayNumber) {
		this.loadingBayNumber = loadingBayNumber;
	}

	public boolean isIsloading() {
		return isloading;
	}

	public void setIsloading(boolean isloading) {
		this.isloading = isloading;
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

	public Date getNextFreeTime(Date earliestPacking) {

		Date loadingDone = earliestPacking;

		if (isIsloading() == true) {
			for (LoadingInfo loadingInfo : loadingInfos) {
				if (loadingInfo.getState() == LoadingInfoState.LOADING) {
					loadingInfo.getTimeOfLoadingEnd();
				}
			}
		} else {
			if (loadingInfos.size() == 1) {

				loadingDone = loadingInfos.get(loadingInfos.size() - 1)
						.getTimeOfLoadingEnd();
			}
			if (loadingInfos.size() > 1) {

				loadingDone = loadingInfos.get(loadingInfos.size() - 2)
						.getTimeOfLoadingEnd();
			}
		}
		return loadingDone;
	}

	@Override
	public String toString() {
		return "Bay " + loadingBayNumber + " (" + productType + ")";
	}

}
