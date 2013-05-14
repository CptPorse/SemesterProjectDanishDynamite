package model;

import java.util.Date;

//Author: Jens Nyberg Porse
public class LoadingInfo
{

	private Date timeOfLoadingStart;
	private Date timeOfLoadingEnd;
	private boolean highPriority;
	private SubOrder subOrder;
	private LoadingBay loadingBay;
	private LoadingInfoState state;

	public LoadingInfo(SubOrder subOrder, LoadingBay loadingBay)
	{
		super();
		this.subOrder = subOrder;
		this.loadingBay = loadingBay;
		this.highPriority = false;
		state = LoadingInfoState.FINISHED;
	}

	public Date getTimeOfLoadingStart()
	{
		return timeOfLoadingStart;
	}

	public void setTimeOfLoadingStart(Date timeOfLoadingStart)
	{
		this.timeOfLoadingStart = timeOfLoadingStart;
	}

	public Date getTimeOfLoadingEnd()
	{
		return timeOfLoadingEnd;
	}

	public void setTimeOfLoadingEnd(Date timeOfLoadingEnd)
	{
		this.timeOfLoadingEnd = timeOfLoadingEnd;
	}

	public boolean isHighPriority()
	{
		return highPriority;
	}

	public void setHighPriority(boolean highPriority)
	{
		this.highPriority = highPriority;
	}

	public SubOrder getSubOrder()
	{
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder)
	{
		this.subOrder = subOrder;
	}

	public LoadingBay getLoadingBay()
	{
		return loadingBay;
	}

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
	 * @param state the state to set
	 */
	public void setState(LoadingInfoState state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		String line = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
		String green = "lime";
		String yellow = "yellow";
		String white = "white";

		String bgColor = "<body bgcolor=" + green + ">";
		if (state == LoadingInfoState.PENDING)
		{
			bgColor = "<body bgcolor=" + white + ">";
		}
		else if (state == LoadingInfoState.LOADING)
		{
			bgColor = "<body bgcolor=" + yellow + ">";
		}

		if (highPriority == true)
		{
			return "<html>" + bgColor + "<h3 align=" + "center" + ">HIGH PRIORITY</h3><br>Suborder: " + subOrder + "<br>Estimated start: " + timeOfLoadingStart
					+ "<br>Estimated loading time: " + subOrder.getEstimatedLoadingTime() + " min<br>" + line;
		}
		else
//		{
//			return "<html>" + bgColor + "Suborder: " + subOrder + "<br>Estimated start: " + timeOfLoadingStart + "<p align=" + "right"
//					+ ">Estimated loading time: " + subOrder.getEstimatedLoadingTime() + " min<br>" + line;
//		}
		{
			return "<html><table border=" + "1" + "><tr><td width='275'>Suborder: " + subOrder + "</td><td width='275'></td></tr><br><tr><td>Estimated start: "
					+ timeOfLoadingStart + " </td><td>Estimated loading time: " + subOrder.getEstimatedLoadingTime() + " min";
		}
	}

}
