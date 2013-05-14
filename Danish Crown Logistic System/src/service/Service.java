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

	// Author: Jens Porse
	public static void startUpSubOrderSort(ProductType productType) {
		ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();
		for (int i = 0; i < Dao.getSubOrders().size(); i++) {
			if (Dao.getSubOrders().get(i).getProductType() == productType)
				subOrders.add(Dao.getSubOrders().get(i));
		}

		// Bobblesort
		int position, scan;
		for (position = subOrders.size() - 1; position >= 0; position--) {
			for (scan = 0; scan <= position - 1; scan++) {
				if (subOrders
						.get(scan)
						.getTrailer()
						.getTimeOfArrival()
						.after((subOrders.get(scan + 1).getTrailer()
								.getTimeOfArrival())))
					swap(subOrders, scan, scan + 1);
			}
		}
		System.out.println(subOrders);
		Service.startUpCreateLoadingInfo(productType, subOrders);
	}

	private static <T> void swap(ArrayList<T> items, int index1, int index2) {
		T temp = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, temp);
	}

	// Author: Jens Porse
	public static void startUpCreateLoadingInfo(ProductType productType,
			ArrayList<SubOrder> subOrders) {

		for (int i = 0; i < subOrders.size(); i++) {
			SubOrder subOrder = subOrders.get(i);
			LoadingBay loadingBay = Service
					.getEarliestAccesibleLoadingBay(productType);
			LoadingInfo loadingInfo = Service.createLoadingInfo(subOrder,
					loadingBay);

			if (loadingBay.getLoadingInfos().size() == 1) {
				Date startLoadingTime = subOrder.getTrailer()
						.getTimeOfArrival();
				loadingInfo.setTimeOfLoadingStart(startLoadingTime);
				loadingInfo.setTimeOfLoadingEnd(Service.getEndTime(
						startLoadingTime, subOrder.getEstimatedLoadingTime()));

			} else {
				LoadingInfo previousLoadingInfo = loadingBay.getLoadingInfos()
						.get((loadingBay.getLoadingInfos().size() - 2));
				loadingInfo.setTimeOfLoadingStart(previousLoadingInfo
						.getTimeOfLoadingEnd());
				loadingInfo.setTimeOfLoadingEnd(Service.getEndTime(
						previousLoadingInfo.getTimeOfLoadingEnd(),
						subOrder.getEstimatedLoadingTime()));
			}
		}
	}

	// Author: Jens Porse
	public static LoadingBay getEarliestAccesibleLoadingBay(
			ProductType productType) {
		ArrayList<LoadingBay> loadingBays = new ArrayList<LoadingBay>();

		for (int i = 0; i < Dao.getLoadingBays().size(); i++) {
			if (Dao.getLoadingBays().get(i).getProductType() == productType) {
				loadingBays.add(Dao.getLoadingBays().get(i));
			}
		}

		LoadingBay earliestLoadingBay = loadingBays.get(0);
		for (int n = 0; n < loadingBays.size(); n++) {
			if (loadingBays.get(n).getBayWaitingTime() < earliestLoadingBay
					.getBayWaitingTime()) {
				earliestLoadingBay = loadingBays.get(n);
			}
		}
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
