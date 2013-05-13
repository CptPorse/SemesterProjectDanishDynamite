package model;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class Trailer
{

	private String trailerID;
	private Date timeOfArrival;
	private Date timeOfDeparture = null;
	private boolean isLoaded = false;
	private boolean hasArrived = false;
	private double weightCurrent;
	private double weightMax;

	private Driver driver;
	private ArrayList<ProductType> productTypes = new ArrayList<ProductType>();

	public Trailer(String trailerID, double weightMax, Date timeOfArrival)
	{
		super();
		this.timeOfArrival = timeOfArrival;
		this.trailerID = trailerID;
		this.weightMax = weightMax;
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

	public boolean isLoaded()
	{
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded)
	{
		this.isLoaded = isLoaded;
	}

	public boolean hasArrived()
	{
		return hasArrived;
	}

	public void setHasArrived(boolean hasArrived)
	{
		this.hasArrived = hasArrived;
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

	@Override
	public String toString()
	{
		return "Trailer " + trailerID;
	}

}
