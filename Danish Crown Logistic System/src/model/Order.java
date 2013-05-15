package model;

import java.util.ArrayList;
import java.util.Date;

public class Order
{

	private int orderNumber;
	private double weightMargin;
	private Date loadingDate;
	private ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();

	public Order(int orderNumber, double weightMargin, Date loadingDate)
	{
		super();
		this.orderNumber = orderNumber;
		this.weightMargin = weightMargin;
		this.loadingDate = loadingDate;
	}

	public int getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public double getWeightMargin()
	{
		return weightMargin;
	}

	public void setWeightMargin(double weightMargin)
	{
		this.weightMargin = weightMargin;
	}

	public Date getLoadingDate()
	{
		return loadingDate;
	}

	public void setLoadingDate(Date loadingDate)
	{
		this.loadingDate = loadingDate;
	}

	/**
	 * Returns a list of sub-orders in this Order.
	 */
	public ArrayList<SubOrder> getSubOrders()
	{
		return new ArrayList<SubOrder>(subOrders);
	}

	/**
	 * Adds the sub-order to this Order.
	 */
	public void addSubOrder(SubOrder subOrder)
	{
		subOrders.add(subOrder);
		subOrder.setOrder(this);
	}

	/**
	 * Removes the sub-order from this Order.
	 */
	public void removeSubOrder(SubOrder subOrder)
	{
		subOrders.remove(subOrder);
	}

	@Override
	public String toString()
	{
		return "OrderNumber: " + orderNumber;
	}

}
