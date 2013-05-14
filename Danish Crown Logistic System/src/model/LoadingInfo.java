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
		state = LoadingInfoState.PENDING;
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

	//Author: Christian Møller Pedersen
	@Override
	public String toString()
	{
		String line = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
		String bgColor = "";
		if (state == LoadingInfoState.FINISHED)
		{
			bgColor = "<body bgcolor='lime'>";
		}
		else if (state == LoadingInfoState.LOADING)
		{
			bgColor = "<body bgcolor='yellow'>";
		}

		String hp = "";
		if (highPriority == true)
		{
			hp = "<h3 align='center'>HIGH PRIORITY</h3><br>";
		}
		return "<html>" + bgColor + hp + "<table><tr><td width='255'>Suborder: " + subOrder + "</td><td width='255' align='right'>State: " + state
				+ "</td></tr><br><tr><td>Estimated start: " + timeOfLoadingStart + " </td><td align='right'>Estimated loading time: "
				+ subOrder.getEstimatedLoadingTime() + " min</td></tr></table>" + line;
	}
}
