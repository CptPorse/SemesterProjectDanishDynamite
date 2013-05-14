package service;

import java.util.ArrayList;
import java.util.Date;

import model.Driver;
import model.LoadingBay;
import model.LoadingInfo;
import model.Order;
import model.ProductType;
import model.SubOrder;
import model.Trailer;
import model.TrailerState;
import dao.Dao;

public class Service {

	public static Driver createDriver(String name, String phoneNumber,
			String licensePlate) {
		Driver driver = new Driver(name, phoneNumber, licensePlate);
		Dao.addDriver(driver);
		return driver;
	}

	public static Trailer createTrailer(String trailerID, double weightMax,
			Date timeOfArrival) {
		Trailer trailer = new Trailer(trailerID, weightMax, timeOfArrival);
		Dao.addTrailer(trailer);
		return trailer;
	}

	public static ProductType createProductType(String description,
			double minuteToKiloRatio) {
		ProductType productType = new ProductType(description,
				minuteToKiloRatio);
		Dao.addProductType(productType);
		return productType;
	}

	public static Order createOrder(int orderNumber, double weightMargin,
			Date loadingDate) {
		Order order = new Order(orderNumber, weightMargin, loadingDate);
		Dao.addOrder(order);
		return order;
	}

	public static SubOrder createSubOrder(double estimatedWeight,
			Trailer trailer, ProductType productType) {
		SubOrder subOrder = new SubOrder(estimatedWeight, trailer, productType);
		trailer.addSubOrder(subOrder);
		Dao.addSubOrder(subOrder);
		trailer.setTrailerState(TrailerState.ENROUTE);
		return subOrder;
	}

	public static LoadingBay createLoadingBay(int loadingBayNumber,
			ProductType productType) {
		LoadingBay loadingBay = new LoadingBay(loadingBayNumber, productType);
		Dao.addLoadingBay(loadingBay);
		return loadingBay;
	}

	public static LoadingInfo createLoadingInfo(SubOrder subOrder,
			LoadingBay loadingBay) {
		LoadingInfo loadingInfo = new LoadingInfo(subOrder, loadingBay);
		Dao.addLoadingInfo(loadingInfo);
		loadingBay.addLoadingInfo(loadingInfo);
		return loadingInfo;
	}

	public static void sortTrailerArrival() {

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

	public static void sortSubOrders() {

		ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();
		subOrders.addAll(Dao.getSubOrders());

		int position, scan;
		for (position = subOrders.size() - 1; position >= 0; position--) {
			for (scan = 0; scan <= position - 1; scan++) {
				if (subOrders
						.get(scan)
						.getEarliestLoadingTime()
						.after((subOrders.get(scan + 1)
								.getEarliestLoadingTime())))
					swap(subOrders, scan, scan + 1);
			}
		}
		for (int i = subOrders.size() - 1; i >= 0; i--) {
			Dao.removeSubOrder(Dao.getSubOrders().get(i));
		}
		for (int i = 0; i < subOrders.size(); i++) {
			Dao.addSubOrder(subOrders.get(i));
		}
	}

	private static <T> void swap(ArrayList<T> items, int index1, int index2) {
		T temp = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, temp);
	}

	public static LoadingBay firstAvailableLoadingBay(ProductType productType,
			Date earliestLoadingTime) {

		System.out
				.println("Started Method firstAvailableLoadingBay(ProductType "
						+ productType + ", Date " + earliestLoadingTime + ")");
		ArrayList<LoadingBay> loadingBays = new ArrayList<LoadingBay>();

		for (int i = 0; i < Dao.getLoadingBays().size(); i++) {
			if (Dao.getLoadingBays().get(i).getProductType() == productType) {
				loadingBays.add(Dao.getLoadingBays().get(i));
			}
		}

		LoadingBay earliestLoadingBay = loadingBays.get(0);
		Long shortestWaitTime = earliestLoadingBay.getNextFreeTime(
				earliestLoadingTime).getTime();
		for (int n = 0; n < loadingBays.size(); n++) {
			Date bayAvailable = loadingBays.get(n).getNextFreeTime(
					earliestLoadingTime);
			Long waitTime = (bayAvailable.getTime() - earliestLoadingTime
					.getTime());
			if (waitTime <= shortestWaitTime && waitTime >= 0) {
				earliestLoadingBay = loadingBays.get(n);
				shortestWaitTime = waitTime;
				System.out.println("Found Shorter waiting time");
			}
		}
		System.out.println("LoadingBay with shortest Waiting time: "
				+ earliestLoadingBay);
		return earliestLoadingBay;
	}

	// Author: Jens Porse
	public static Date getEndTime(Date startTime, int loadingTime) {
		Long time = startTime.getTime();
		Long loadingTimeInMS = (long) (loadingTime * 60000);

		Date endTime = new Date(time + loadingTimeInMS);
		return endTime;
	}
}
