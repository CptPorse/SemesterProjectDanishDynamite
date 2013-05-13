package model;

import java.util.Date;

//Author: Jens Nyberg Porse
public class LoadingInfo {

	private Date timeOfLoadingStart;
	private Date timeOfLoadingEnd;
	private boolean highPriority;
	private SubOrder subOrder;
	private LoadingBay loadingBay;

	public LoadingInfo(SubOrder subOrder, LoadingBay loadingBay) {
		super();
		this.subOrder = subOrder;
		this.loadingBay = loadingBay;
		this.highPriority = false;
	}

	public Date getTimeOfLoadingStart() {
		return timeOfLoadingStart;
	}

	public void setTimeOfLoadingStart(Date timeOfLoadingStart) {
		this.timeOfLoadingStart = timeOfLoadingStart;
	}

	public Date getTimeOfLoadingEnd() {
		return timeOfLoadingEnd;
	}

	public void setTimeOfLoadingEnd(Date timeOfLoadingEnd) {
		this.timeOfLoadingEnd = timeOfLoadingEnd;
	}

	public boolean isHighPriority() {
		return highPriority;
	}

	public void setHighPriority(boolean highPriority) {
		this.highPriority = highPriority;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public LoadingBay getLoadingBay() {
		return loadingBay;
	}

	public void setLoadingBay(LoadingBay loadingBay) {
		this.loadingBay = loadingBay;
	}

}
