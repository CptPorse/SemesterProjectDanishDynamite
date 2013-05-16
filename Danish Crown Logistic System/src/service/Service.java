package service;

import java.util.ArrayList;
import java.util.Date;

import model.Driver;
import model.LoadingBay;
import model.LoadingInfo;
import model.LoadingInfoState;
import model.Order;
import model.ProductType;
import model.SubOrder;
import model.Trailer;
import model.TrailerState;
import dao.Dao;

public class Service
{

	public static Driver createDriver(String name, String phoneNumber, String licensePlate)
	{
		Driver driver = new Driver(name, phoneNumber, licensePlate);
		Dao.addDriver(driver);
		return driver;
	}

	public static Trailer createTrailer(String trailerID, double weightMax, Date timeOfArrival)
	{
		Trailer trailer = new Trailer(trailerID, weightMax, timeOfArrival);
		Dao.addTrailer(trailer);
		return trailer;
	}

	public static ProductType createProductType(String description, double minuteToKiloRatio)
	{
		ProductType productType = new ProductType(description, minuteToKiloRatio);
		Dao.addProductType(productType);
		return productType;
	}

	public static Order createOrder(int orderNumber, Date loadingDate)
	{
		Order order = new Order(orderNumber, loadingDate);
		Dao.addOrder(order);
		return order;
	}

	public static SubOrder createSubOrder(double estimatedWeight, Trailer trailer,
			ProductType productType)
	{
		SubOrder subOrder = new SubOrder(estimatedWeight, trailer, productType);
		trailer.addSubOrder(subOrder);
		Dao.addSubOrder(subOrder);
		trailer.setTrailerState(TrailerState.ENROUTE);
		return subOrder;
	}

	public static LoadingBay createLoadingBay(int loadingBayNumber, ProductType productType)
	{
		LoadingBay loadingBay = new LoadingBay(loadingBayNumber, productType);
		Dao.addLoadingBay(loadingBay);
		return loadingBay;
	}

	public static LoadingInfo createLoadingInfo(SubOrder subOrder, LoadingBay loadingBay)
	{
		LoadingInfo loadingInfo = new LoadingInfo(subOrder, loadingBay);
		Dao.addLoadingInfo(loadingInfo);
		loadingBay.addLoadingInfo(loadingInfo);
		subOrder.setLoadingInfo(loadingInfo);
		return loadingInfo;
	}

	public static void sortTrailerArrival()
	{

		ArrayList<Trailer> trailers = new ArrayList<Trailer>();
		trailers.addAll(Dao.getTrailer());

		int position, scan;
		for (position = trailers.size() - 1; position >= 0; position--) {
			for (scan = 0; scan <= position - 1; scan++) {
				if (trailers.get(scan).getTimeOfArrival()
						.after((trailers.get(scan + 1).getTimeOfArrival())))
					swap(trailers, scan, scan + 1);
			}
		}
		for (int i = trailers.size() - 1; i >= 0; i--) {
			Dao.removeTrailer(Dao.getTrailer().get(i));
		}
		for (int i = 0; i < trailers.size(); i++) {
			Dao.addTrailer(trailers.get(i));
		}
	}

	public static ArrayList<SubOrder> sortSubOrders(ArrayList<SubOrder> subOrders)
	{

		ArrayList<SubOrder> subOrdersSorted = subOrders;
		int position, scan;
		for (position = subOrdersSorted.size() - 1; position >= 0; position--) {
			for (scan = 0; scan <= position - 1; scan++) {
				if (subOrdersSorted.get(scan).getEarliestLoadingTime()
						.after((subOrdersSorted.get(scan + 1).getEarliestLoadingTime())))
					swap(subOrdersSorted, scan, scan + 1);
			}
		}
		for (int i = subOrdersSorted.size() - 1; i > 0; i--) {
			System.out.println("High Priority? " + subOrdersSorted.get(i).isHighPriority());
			if (subOrdersSorted.get(i).isHighPriority() == true) {
				System.out.println("Found a High Priority: " + subOrdersSorted.get(i));
				subOrdersSorted.add(0, subOrdersSorted.get(i));
				subOrdersSorted.remove(i + 1);
			}
		}
		return subOrdersSorted;
	}

	private static <T> void swap(ArrayList<T> items, int index1, int index2)
	{
		T temp = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, temp);
	}

	/**
	 * A method that used to find the first avaliable LoadingBay available for the Suborder.
	 * @param productType: The productType the SubOrder is made of. Used to filter out loadingBays that cannot load this.
	 * @param earliestLoadingTime: The earliest time the SubOrder can be loaded unto a Truck.
	 * @return An Object of type LoadingBay, which has the shortest waitingtime, compared to when the SubOrder earliest can be loaded.
	 * @author Jens Porse
	 */
	public static LoadingBay firstAvailableLoadingBay(ProductType productType,
			Date earliestLoadingTime)
	{

		//Creates a list of all LoadingBays in memory which can handle the productType in question
		ArrayList<LoadingBay> loadingBays = new ArrayList<LoadingBay>();

		for (int i = 0; i < Dao.getLoadingBays().size(); i++) {
			if (Dao.getLoadingBays().get(i).getProductType() == productType) {
				loadingBays.add(Dao.getLoadingBays().get(i));
			}
		}

		//Sets the first LoadingBay in the list as the baseline for comparison  
		LoadingBay earliestLoadingBay = loadingBays.get(0);
		Long shortestWaitTime = earliestLoadingBay.getNextFreeTime(earliestLoadingTime);

		//Loops through all LoadingBays, and asks for the loadingBays wait time in milliseconds, compared to the LoadinInfos earliest loading time
		for (int n = 0; n < loadingBays.size(); n++) {
			Long waitTime = (loadingBays.get(n).getNextFreeTime(earliestLoadingTime));
			//Compares the two waiting times
			if (waitTime <= shortestWaitTime) {
				earliestLoadingBay = loadingBays.get(n);
				shortestWaitTime = waitTime;
			}
		}
		Date bayReadyAt = new Date(earliestLoadingTime.getTime() + shortestWaitTime);
		earliestLoadingBay.setNextAvailableTime(bayReadyAt);
		return earliestLoadingBay;
	}

	/**
	 * A method that preps an ArrayList of SubOrders to be resorted in the LoadingBay Schedule
	 * @param productType: Used to create a local list of all loadingBays, whos SubOrders we are to resort
	 */
	public static void refreshLoadingBays(ProductType productType)
	{
		//Creates a local list of all loadingBays from the Dao, that can handle the productType in question.
		ArrayList<LoadingBay> loadingBays = new ArrayList<LoadingBay>();
		for (LoadingBay loadingBay : Dao.getLoadingBays()) {
			if (loadingBay.getProductType() == productType)
				loadingBays.add(loadingBay);
		}

		//Loops through the list of loadingBays and adds all subOrders, that is either Ready to be loaded or pending, to the subOrderToSort list.
		ArrayList<SubOrder> subOrdersToSort = new ArrayList<SubOrder>();
		for (LoadingBay loadingBay : loadingBays) {
			for (LoadingInfo loadingInfo : loadingBay.getLoadingInfos()) {
				//Resets the loadingBays next available time to whenever the last subOrder was either started or finished
				if (loadingInfo.getState() == LoadingInfoState.LOADING
						|| loadingInfo.getState() == LoadingInfoState.FINISHED) {
					loadingBay.setNextAvailableTime(loadingInfo.getTimeOfLoadingEnd());
				}
				if (loadingInfo.getState() == LoadingInfoState.READY_TO_LOAD
						|| loadingInfo.getState() == LoadingInfoState.PENDING) {
					subOrdersToSort.add(loadingInfo.getSubOrder());
					loadingBay.removeLoadingInfo(loadingInfo);
					Dao.removeLoadingInfo(loadingInfo);
				}
			}
		}
		System.out.println("sortSubOrder() began");
		subOrdersToSort = sortSubOrders(subOrdersToSort);
		System.out.println("createLoadingBayschedule() began");
		StartUpService.createLoadingBaySchedule(subOrdersToSort);
	}

	// Author: Jens Porse
	public static Date getEndTime(Date startTime, int loadingTime)
	{
		Long time = startTime.getTime();
		Long loadingTimeInMS = (long)(loadingTime * 60000);

		Date endTime = new Date(time + loadingTimeInMS);
		return endTime;
	}

	// Author: Jens Porse
	public static String getDateToStringTime(Date date)
	{
		String hours = String.format("%2d", date.getHours()).replace(' ', '0');
		String minutes = String.format("%2d", date.getMinutes()).replace(' ', '0');

		return hours + ":" + minutes;
	}

	//Author: Jens Porse
	public static Date getTimeStringToDate(String time)
	{
		int hours = Integer.parseInt(time.substring(0, 2));
		int minutes = Integer.parseInt(time.substring(3, 5));
		Date date = new Date(113, 0, 1, hours, minutes);

		return date;
	}
}
