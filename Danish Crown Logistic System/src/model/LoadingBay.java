package model;

import java.util.ArrayList;
import java.util.Date;

//Author: Jens Nyberg Porse
public class LoadingBay {

	private int loadingBayNumber;
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

	public int getBayWaitingTime() {

		int waitingTime = 0;
		for (int i = 0; i < loadingInfos.size(); i++) {
			int timeStart = (loadingInfos.get(i).getTimeOfLoadingStart()
					.getHours())
					* 60
					+ (loadingInfos.get(i).getTimeOfLoadingEnd().getMinutes());
			int timeEnd = (loadingInfos.get(i).getTimeOfLoadingEnd().getHours())
					* 60
					+ (loadingInfos.get(i).getTimeOfLoadingStart().getMinutes());
			waitingTime = +timeEnd - timeStart;
		}
		return waitingTime;
	}

	// NOT WORKING
	public Date getNextFreeTime() {

		Date loadingDone = null;
		for (int i = 0; i < loadingInfos.size(); i++) {
			System.out.println(loadingInfos.get(i).getTimeOfLoadingStart());
			if (loadingInfos.get(i).getState() == LoadingInfoState.LOADING) {
				loadingDone = loadingInfos.get(i).getTimeOfLoadingEnd();
			} else {
				loadingDone = loadingInfos.get(loadingInfos.size() - 1)
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
