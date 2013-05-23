package model;

import java.util.ArrayList;
import java.util.Date;

// Author: Jens Nyberg Porse
public class Order
{

	private int orderNumber;
	private double weightMarginPercent = 2;
	private double weightMarginKilo;
	private Date loadingDate;
	private ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();

	public Order(int orderNumber, Date loadingDate)
	{
		super();
		this.orderNumber = orderNumber;
		this.loadingDate = loadingDate;
	}

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber()
	{
		return orderNumber;
	}

	/**
	 * @param orderNumber: The orderNumber to set
	 */
	public void setOrderNumber(int orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the weightMarginPercent
	 */
	public double getWeightMarginPercent()
	{
		return weightMarginPercent;
	}

	/**
	 * @param weightMarginPercent: The weightMarginPercent to set
	 */
	public void setWeightMarginPercent(double weightMarginPercent)
	{
		this.weightMarginPercent = weightMarginPercent;
	}

	/**
	 * @return the weightMarginKilo
	 */
	public double getWeightMarginKilo()
	{
		return weightMarginKilo;
	}

	/**
	 * @param weightMarginKilo: The weightMarginKilo to set
	 */
	public void setWeightMarginKilo(double weightMarginKilo)
	{
		this.weightMarginKilo = weightMarginKilo;
	}

	/**
	 * @return the loadingDate
	 */
	public Date getLoadingDate()
	{
		return loadingDate;
	}

	/**
	 * @param loadingDate the loadingDate to set
	 */
	public void setLoadingDate(Date loadingDate)
	{
		this.loadingDate = loadingDate;
	}

	/**
	 * @return a list of sub-orders in this Order.
	 */
	public ArrayList<SubOrder> getSubOrders()
	{
		return new ArrayList<SubOrder>(subOrders);
	}

	/**
	 * @param subOrder: Adds this suborder to this Order.
	 */
	public void addSubOrder(SubOrder subOrder)
	{
		subOrders.add(subOrder);
		subOrder.setOrder(this);
		weightMarginKilo = calculateWeightMargin();
		subOrder.getTrailer().setTrailerState(TrailerState.ENROUTE);
	}

	/**
	 * @param SubOrder: Removes this subOrder from this Order.
	 */
	public void removeSubOrder(SubOrder subOrder)
	{
		subOrders.remove(subOrder);
	}

	/**
	 * Calculated the weight margin in kilo based on a default weight margin in percent, and the total weight of the order.
	 * @return The weight Margin in Kilo.
	 */
	private double calculateWeightMargin()
	{
		double totalWeight = 0;
		for (SubOrder subOrder : subOrders) {
			totalWeight += subOrder.getEstimatedWeight();
		}
		return totalWeight * ((weightMarginPercent / 100));
	}

	@Override
	public String toString()
	{
		return "Order ID: " + orderNumber;
	}

}
