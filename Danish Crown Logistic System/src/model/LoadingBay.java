package model;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jdt.annotation.NonNullByDefault;

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

	@NonNullByDefault
	public Date getNextFreeTime(Date earliestPacking) {

		System.out.println("Started Method getNextFreeTime(Date "
				+ earliestPacking + ")");
		Date loadingDone = earliestPacking;

		if (isIsloading() == true) {
			for (LoadingInfo loadingInfo : loadingInfos) {
				if (loadingInfo.getState() == LoadingInfoState.LOADING) {
					loadingInfo.getTimeOfLoadingEnd();
				}
			}
		} else {
			if (loadingInfos.size() == 1) {
				System.out.println("List is not empty");
				System.out.println(loadingInfos);
				System.out.println(loadingInfos.get(loadingInfos.size() - 1)
						.getTimeOfLoadingEnd());
				System.out.println(loadingInfos);
				loadingDone = loadingInfos.get(loadingInfos.size() - 1)
						.getTimeOfLoadingEnd();
			}
			if (loadingInfos.size() > 1) {
				System.out.println("List is not empty");
				System.out.println(loadingInfos);
				System.out.println(loadingInfos.get(loadingInfos.size() - 2)
						.getTimeOfLoadingEnd());
				System.out.println(loadingInfos);
				loadingDone = loadingInfos.get(loadingInfos.size() - 2)
						.getTimeOfLoadingEnd();
			}
		}
		System.out.println("LoadingBay " + loadingBayNumber + ", "
				+ getProductType());
		System.out.println("Returned: " + loadingDone);
		return loadingDone;
	}

	@Override
	public String toString() {
		return "Bay " + loadingBayNumber + " (" + productType + ")";
	}

}
