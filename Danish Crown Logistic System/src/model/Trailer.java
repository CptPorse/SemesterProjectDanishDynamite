package model;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class Trailer
{
	//fields
	private String trailerID;
	private Date timeOfArrival;
	private Date timeOfDeparture = null;

	private double weightCurrent;
	private double weightMax;
	private TrailerState trailerState;

	//Links
	private Driver driver;
	private ArrayList<ProductType> productTypes = new ArrayList<ProductType>();
	private ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();

	public Trailer(String trailerID, double weightMax, Date timeOfArrival)
	{
		super();
		this.timeOfArrival = timeOfArrival;
		this.trailerID = trailerID;
		this.weightMax = weightMax;
		this.trailerState = TrailerState.IDLE;

	}

	public TrailerState getTrailerState()
	{
		return trailerState;
	}

	public void setTrailerState(TrailerState trailerState)
	{
		this.trailerState = trailerState;
	}

	public String getTrailerID()
	{
		return trailerID;
	}

	public void setTrailerID(String trailerID)
	{
		this.trailerID = trailerID;
	}

	public Date getTimeOfArrival()
	{
		return timeOfArrival;
	}

	public void setTimeOfArrival(Date timeOfArrival)
	{
		this.timeOfArrival = timeOfArrival;
	}

	@Nullable
	public Date getTimeOfDeparture()
	{
		return timeOfDeparture;
	}

	public void setTimeOfDeparture(Date timeOfDeparture)
	{
		this.timeOfDeparture = timeOfDeparture;
	}

	public double getWeightCurrent()
	{
		return weightCurrent;
	}

	public void setWeightCurrent(double weightCurrent)
	{
		this.weightCurrent = weightCurrent;
	}

	public double getWeightMax()
	{
		return weightMax;
	}

	public void setWeightMax(double weightMax)
	{
		this.weightMax = weightMax;
	}

	@Nullable
	public Driver getDriver()
	{
		return driver;
	}

	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

	public void clearDriver()
	{
		this.driver = null;
	}

	/**
	 * Returns a list of productTypes in this Trailer.
	 */
	public ArrayList<ProductType> getProductTypes()
	{
		return new ArrayList<ProductType>(productTypes);
	}

	/**
	 * Adds the productType to this Trailer.
	 */
	public void addProductType(ProductType productType)
	{
		productTypes.add(productType);
	}

	/**
	 * Removes the productType from this Trailer.
	 */
	public void removeProductType(ProductType productType)
	{
		productTypes.remove(productType);
	}

	/**
	 * Returns a list of productTypes in this Trailer.
	 */
	public ArrayList<SubOrder> getSubOrders()
	{
		return new ArrayList<SubOrder>(subOrders);
	}

	/**
	 * Adds the productType to this Trailer.
	 */
	public void addSubOrder(SubOrder subOrder)
	{
		subOrders.add(subOrder);
		//Updates the current weight of the trailer
		weightCurrent += subOrder.getEstimatedWeight();
	}

	/**
	 * Removes the productType from this Trailer.
	 */
	public void removeSubOrder(SubOrder subOrder)
	{
		subOrders.remove(subOrder);
	}

	@Override
	public String toString()
	{
		String min = "00";
		String tempTrailerID = "" + trailerID + "                       ";
		if (timeOfArrival.getMinutes() != 0) {
			min = "" + timeOfArrival.getMinutes();
		}

		if (trailerState == TrailerState.ENROUTE) {

			return "<html><table border=1 width=120 >" + "Trailer: " + tempTrailerID.substring(0, 12) + "<br/>" + "ETA: " + timeOfArrival.getHours() + ":" + min + "</table> </html>";
		}
		if (trailerState == TrailerState.LOADED) {
			double weightProcent = 0;
			if (weightCurrent < 1) {

			} else {
				weightProcent = (weightCurrent / weightMax) * 100;
			}
			return "<html><table border=1 width=120 >" + "Trailer: " + tempTrailerID.substring(0, 12) + "<br/>" + "Weight: " + weightProcent + "%" + "</table> </html>";
		} else {
			return "Trailer: " + trailerID;
		}
	}
}
