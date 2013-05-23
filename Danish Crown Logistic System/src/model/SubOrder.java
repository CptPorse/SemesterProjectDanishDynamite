package model;

import java.util.Date;

// Author: Jens Nyberg Porse
public class SubOrder
{

	private double estimatedWeight;
	private int estimatedLoadingTime;
	private boolean isLoaded = false;
	private boolean highPriority = false;
	private Date earliestLoadingTime;
	private Order order;
	private Trailer trailer;
	private ProductType productType;
	private LoadingInfo loadingInfo;

	public SubOrder(double estimatedWeight, Trailer trailer, ProductType productType)
	{
		super();
		this.estimatedWeight = estimatedWeight;
		this.trailer = trailer;
		this.productType = productType;
		this.estimatedLoadingTime = (int)(this.productType.getMinuteToKiloRatio() * this.estimatedWeight);
	}

	/**
	 * @return the estimatedWeight
	 */
	public double getEstimatedWeight()
	{
		return estimatedWeight;
	}

	/**
	 * @param estimatedWeight: The estimatedWeight to set
	 */
	public void setEstimatedWeight(double estimatedWeight)
	{
		this.estimatedWeight = estimatedWeight;
	}

	/**
	 * @return the estimatedLoadingTime
	 */
	public int getEstimatedLoadingTime()
	{
		return estimatedLoadingTime;
	}

	/**
	 * @param estimatedLoadingTime: The estimatedLoadingTime to set
	 */
	public void setEstimatedLoadingTime(int estimatedLoadingTime)
	{
		this.estimatedLoadingTime = estimatedLoadingTime;
	}

	/**
	 * @return the isLoaded
	 */
	public boolean isLoaded()
	{
		return isLoaded;
	}

	/**
	 * @param isLoaded: The isLoaded to set
	 */
	public void setLoaded(boolean isLoaded)
	{
		this.isLoaded = isLoaded;
	}

	/**
	 * @return the highPriority
	 */
	public boolean isHighPriority()
	{
		return highPriority;
	}

	/**
	 * @param highPriority: The highPriority to set
	 */
	public void setHighPriority(boolean highPriority)
	{
		this.highPriority = highPriority;
	}

	/**
	 * @return the earliestLoadingTime
	 */
	public Date getEarliestLoadingTime()
	{
		return earliestLoadingTime;
	}

	/**
	 * @param earliestLoadingTime: The earliestLoadingTime to set
	 */
	public void setEarliestLoadingTime(Date earliestLoadingTime)
	{
		this.earliestLoadingTime = earliestLoadingTime;
	}

	/**
	 * @return the order
	 */
	public Order getOrder()
	{
		return order;
	}

	/**
	 * @param order: The order to set
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}

	/**
	 * @return the trailer
	 */
	public Trailer getTrailer()
	{
		return trailer;
	}

	/**
	 * @param trailer: The trailer to set
	 */
	public void setTrailer(Trailer trailer)
	{
		this.trailer = trailer;
	}

	/**
	 * @return the productType
	 */
	public ProductType getProductType()
	{
		return productType;
	}

	/**
	 * @param productType: The productType to set
	 */
	public void setProductType(ProductType productType)
	{
		this.productType = productType;
	}

	/**
	 * @return the loadingInfo
	 */
	public LoadingInfo getLoadingInfo()
	{
		return loadingInfo;
	}

	/**
	 * @param loadingInfo: The loadingInfo to set
	 */
	public void setLoadingInfo(LoadingInfo loadingInfo)
	{
		this.loadingInfo = loadingInfo;
	}

	@Override
	public String toString()
	{
		if (getOrder() == null) {
			return estimatedWeight + "kg of " + productType;
		} else
			return "Suborder of order nr: " + order.getOrderNumber();
	}
}
