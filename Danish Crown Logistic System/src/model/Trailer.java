package model;

import gui.SmsDialog;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import service.Service;
import dao.Dao;

@NonNullByDefault
public class Trailer
{
	//fields
	private String trailerID;
	private Date timeOfArrival;
	private Date timeOfDeparture = null;
	private double weightCurrent;
	private double weightMax;

	//Links
	private TrailerState trailerState;
	private LoadingBay loadingBay = null;
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

	/**
	 * @return the trailerID
	 */
	public String getTrailerID()
	{
		return trailerID;
	}

	/**
	 * @param trailerID: The trailerID to set
	 */
	public void setTrailerID(String trailerID)
	{
		this.trailerID = trailerID;
	}

	/**
	 * @return the timeOfArrival
	 */
	public Date getTimeOfArrival()
	{
		return timeOfArrival;
	}

	/**
	 * @param timeOfArrival: The timeOfArrival to set
	 */
	public void setTimeOfArrival(Date timeOfArrival)
	{
		this.timeOfArrival = timeOfArrival;
	}

	/**
	 * @return the timeOfDeparture
	 */
	@Nullable
	public Date getTimeOfDeparture()
	{
		return timeOfDeparture;
	}

	/**
	 * @param timeOfDeparture: The timeOfDeparture to set
	 */
	public void setTimeOfDeparture(Date timeOfDeparture)
	{
		this.timeOfDeparture = timeOfDeparture;
	}

	/**
	 * @return the weightCurrent
	 */
	public double getWeightCurrent()
	{
		return weightCurrent;
	}

	/**
	 * @param weightCurrent: The weightCurrent to set
	 */
	public void setWeightCurrent(double weightCurrent)
	{
		this.weightCurrent = weightCurrent;
	}

	/**
	 * @return the weightMax
	 */
	public double getWeightMax()
	{
		return weightMax;
	}

	/**
	 * @param weightMax: The weightMax to set
	 */
	public void setWeightMax(double weightMax)
	{
		this.weightMax = weightMax;
	}

	/**
	 * @return the trailerState
	 */
	public TrailerState getTrailerState()
	{
		return trailerState;
	}

	/**
	 * @param trailerState: The trailerState to set
	 */
	public void setTrailerState(TrailerState trailerState)
	{
		this.trailerState = trailerState;
	}

	/**
	 * @return the driver
	 */
	@Nullable
	public Driver getDriver()
	{
		return driver;
	}

	/**
	 * @param driver: The driver to set
	 */
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

	/**
	 * Clears the link between this trailer and the its driver
	 */
	public void clearDriver()
	{
		this.driver = null;
	}

	/**
	* @return the loadingBay the trailer is currently parked at.
	*/
	public LoadingBay getLoadingBay()
	{
		return loadingBay;
	}

	/**
	 * @param loadingBay: sets the loadingBay the trailer is currently parked at.
	 */
	public void setLoadingBay(@Nullable LoadingBay loadingBay)
	{
		this.loadingBay = loadingBay;
	}

	/**
	 * Clears the loadingbay
	 */
	public void clearLoadingBay()
	{
		this.loadingBay = null;
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

	/**
	 * Method used to register a trailers arrival at Danish Crown .
	 * @author S�ren M�ller Nielsen
	 */
	public void registerArrival()
	{
		setTrailerState(TrailerState.ARRIVED);
		getSubOrders().get(0).getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
	}

	/**
	 * Method used when loading of a suborder is commenced.
	 * @param loadingInfo: The loadingInfo which is associated with the suborder being loaded
	 * @author S�ren M�ller Nielsen
	 */
	public void beginLoading(LoadingInfo loadingInfo)
	{
		setTrailerState(TrailerState.BEING_LOADED);
		setLoadingBay(loadingInfo.getLoadingBay());
	}

	/**
	 * Method used then loading of a suborder is completed.
	 * @param loadingInfo: The loadingInfo which is associated with the suborder being loaded
	 * @author S�ren M�ller Nielsen
	 */
	public void endLoading(LoadingInfo loadingInfo)
	{
		ArrayList<SubOrder> subOrders = loadingInfo.getSubOrder().getTrailer().getSubOrders();
		// searches if any of the attached sub orders to the trailer, aren't done loading, if any, it will set their LoadingInfoStat
		boolean trailerFullyLoaded = true;
		boolean changed = false;
		for (SubOrder s : subOrders) {
			if (s.isLoaded() == false) {
				if (changed == false) {
					s.getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
					changed = true;
				}
				trailerFullyLoaded = false;
			}
		}
		// if all the sub orders are done, trailer changes trailerstate to: loaded
		if (trailerFullyLoaded == true) {
			setTrailerState(TrailerState.LOADED);
			setTimeOfDeparture(loadingInfo.getTimeOfLoadingEnd());
			setLoadingBay(loadingInfo.getLoadingBay());
			SmsDialog sms = new SmsDialog(this);
		} else {
			setTrailerState(TrailerState.ARRIVED);
		}
	}

	/**
	 * A method used to register when a trailer leaves Danish Crown
	 * @author S�ren M�ller Nielsen
	 */
	public void registerDeparture()
	{
		setTrailerState(TrailerState.DEPARTED);
		setLoadingBay(null);
	}

	/**
	 * A method used when a trailer needs to be repacked
	 * @author Jens Nyberg Porse
	 */
	public void repackTrailer()
	{
		setTrailerState(TrailerState.ARRIVED);
		for (SubOrder subOrder : getSubOrders()) {
			System.out.println("Looping. Looking at: " + subOrder);

			subOrder.setEarliestLoadingTime(subOrder.getLoadingInfo().getTimeOfLoadingEnd());
			System.out.println("Setting " + subOrder + " earliestLoadingTime to: "
					+ subOrder.getLoadingInfo().getTimeOfLoadingEnd());
			LoadingInfo newLoadingInfo = Service.createLoadingInfo(subOrder, subOrder
					.getLoadingInfo().getLoadingBay());
			newLoadingInfo.setState(LoadingInfoState.READY_TO_LOAD);
			subOrder.setHighPriority(true);
			Service.refreshLoadingBays(subOrder.getProductType());
		}

	}

	//Author: Christian M�ller Pedersen
	@Override
	public String toString()
	{
		String string = null;
		switch (trailerState) {
		case ARRIVED:
			string = "<html><table>Trailer: " + trailerID + "<br>" + subOrders.get(0).getOrder();
			break;
		case BEING_LOADED:
			LoadingBay lb = null;
			for (LoadingInfo li : Dao.getLoadingInfos()) {
				if (li.getSubOrder().getTrailer() == this
						&& li.getSubOrder().getLoadingInfo().getState() == LoadingInfoState.LOADING) {
					lb = li.getLoadingBay();
				}
			}
			string = "<html><table>Trailer: " + trailerID + "<br>" + lb;
			break;
		case DEPARTED:
			string = "<html><table>Trailer: " + trailerID + "<br>Departed: "
					+ Service.getDateToStringTime(timeOfDeparture);
			break;
		case ENROUTE:
			string = "<html><table>Trailer: " + trailerID + "<br>ETA: "
					+ Service.getDateToStringTime(timeOfArrival);
			break;
		case LOADED:
			string = "<html><table>Trailer: " + trailerID + "<br>Weight: " + weightCurrent
					+ " kg<br>Max: " + weightMax + " kg";
			break;
		default:
			string = "<html><table>Trailer: " + trailerID + "<br>ETA: "
					+ Service.getDateToStringTime(timeOfArrival);
			break;

		}
		return string;
	}
}
