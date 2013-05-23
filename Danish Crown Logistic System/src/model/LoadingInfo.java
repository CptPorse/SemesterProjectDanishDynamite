package model;

import java.util.Date;

import service.Service;

//Author: Jens Nyberg Porse
public class LoadingInfo
{

	private Date timeOfLoadingStart;
	private Date timeOfLoadingEnd;
	private SubOrder subOrder;
	private LoadingBay loadingBay;
	private LoadingInfoState state;

	public LoadingInfo(SubOrder subOrder, LoadingBay loadingBay)
	{
		super();
		this.subOrder = subOrder;
		this.loadingBay = loadingBay;
		state = LoadingInfoState.PENDING;
	}

	/**
	 * @return the timeOfLoadingStart
	 */
	public Date getTimeOfLoadingStart()
	{
		return timeOfLoadingStart;
	}

	/**
	 * @param timeOfLoadingStart: The timeOfLoadingStart to set
	 */
	public void setTimeOfLoadingStart(Date timeOfLoadingStart)
	{
		this.timeOfLoadingStart = timeOfLoadingStart;
	}

	/**
	 * @return the timeOfLoadingEnd
	 */
	public Date getTimeOfLoadingEnd()
	{
		return timeOfLoadingEnd;
	}

	/**
	 * @param timeOfLoadingEnd: The timeOfLoadingEnd to set
	 */
	public void setTimeOfLoadingEnd(Date timeOfLoadingEnd)
	{
		this.timeOfLoadingEnd = timeOfLoadingEnd;
	}

	/**
	 * @return the subOrder
	 */
	public SubOrder getSubOrder()
	{
		return subOrder;
	}

	/**
	 * @param subOrder: The subOrder to set
	 */
	public void setSubOrder(SubOrder subOrder)
	{
		this.subOrder = subOrder;
	}

	/**
	 * @return the loadingBay
	 */
	public LoadingBay getLoadingBay()
	{
		return loadingBay;
	}

	/**
	 * @param loadingBay: The loadingBay to set
	 */
	public void setLoadingBay(LoadingBay loadingBay)
	{
		this.loadingBay = loadingBay;
	}

	/**
	 * @return the state
	 */
	public LoadingInfoState getState()
	{
		return state;
	}

	/**
	 * @param state: The state to set
	 */
	public void setState(LoadingInfoState state)
	{
		this.state = state;
	}

	/**
	 * Method used when loading of a suborder is commenced.
	 * @param timeOfBegunLoading: Time of which loading begun.
	 * @author Søren Møller Nielsen
	 */
	public void beginLoading(Date timeOfBegunLoading)
	{
		setTimeOfLoadingStart(timeOfBegunLoading);
		setTimeOfLoadingEnd(Service.getEndTime(timeOfBegunLoading,
				subOrder.getEstimatedLoadingTime()));
		setState(LoadingInfoState.LOADING);
		loadingBay.setNextAvailableTime(timeOfLoadingEnd);
	}

	/**
	 * Method used then loading of a suborder is complete.
	 * @param timeOfEndedLoading: Time of which loading ended.
	 * @author Søren Møller Nielsen
	 */
	public void endLoading(Date timeOfEndedLoading)
	{
		setTimeOfLoadingEnd(timeOfEndedLoading);
		setState(LoadingInfoState.FINISHED);
		loadingBay.setNextAvailableTime(timeOfLoadingEnd);
		subOrder.setLoaded(true);

	}

	//Author: Christian Møller Pedersen
	@Override
	public String toString()
	{
		String line = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
		String bgColor = "";
		if (state == LoadingInfoState.FINISHED) {
			bgColor = "<body bgcolor='lime'>";
		} else if (state == LoadingInfoState.READY_TO_LOAD) {
			bgColor = "<body bgcolor='aqua'>";
		} else if (state == LoadingInfoState.LOADING) {
			bgColor = "<body bgcolor='yellow'>";
		}

		String hp = "";
		if (subOrder.isHighPriority() == true) {
			hp = "<h3 align='center'>HIGH PRIORITY</h3><br>";
		}
		return "<html>" + bgColor + hp + "<table><tr><td width='255'>" + subOrder
				+ "</td><td width='255' align='right'>State: " + state
				+ "</td></tr><br><tr><td>Estimated start: " + timeOfLoadingStart
				+ " </td><td align='right'>Estimated loading time: "
				+ subOrder.getEstimatedLoadingTime() + " min</td></tr></table>" + line;
	}
}
