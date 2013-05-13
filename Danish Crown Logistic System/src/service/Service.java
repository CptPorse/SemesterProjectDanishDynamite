package service;

import java.util.Date;

import model.Driver;
import model.LoadingBay;
import model.LoadingInfo;
import model.Order;
import model.ProductType;
import model.SubOrder;
import model.Trailer;
import dao.Dao;
import dateutil.DU;

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
			int estimatedLoadingTime, Trailer trailer, ProductType productType) {
		SubOrder subOrder = new SubOrder(estimatedWeight, estimatedLoadingTime,
				trailer, productType);
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
		return loadingInfo;
	}

	public static void startUpData() {

		Driver d1 = Service.createDriver("Peter Hansen", "22 37 54 98",
				"EH 95 128");
		Driver d2 = Service.createDriver("Søren Overgaard", "22 52 12 71",
				"GH 81 411");
		Driver d3 = Service.createDriver("Morgens Nygaard", "22 14 21 45",
				"TR 92 798");
		Driver d4 = Service.createDriver("Ove Pedersen", "22 74 45 19",
				"DW 96 725");
		Driver d5 = Service.createDriver("Kasper Bilder", "22 49 98 94",
				"EQ 87 224");
		Driver d6 = Service.createDriver("Jens Skærbæk", "22 18 74 67",
				"KE 92 465");

		Date date1 = new Date(113, 0, 1, 9, 0);
		Date date2 = new Date(113, 0, 1, 10, 0);
		Date date3 = new Date(113, 0, 1, 11, 0);
		Date date4 = new Date(113, 0, 1, 12, 0);

		Trailer t1 = Service.createTrailer("1", 25000, date1);
		Trailer t2 = Service.createTrailer("2", 25000, date2);
		Trailer t3 = Service.createTrailer("3", 25000, date3);
		Trailer t4 = Service.createTrailer("4", 25000, date4);

		ProductType p1 = Service.createProductType("Christmas Trees", 0.0031);
		ProductType p2 = Service.createProductType("Pallets", 0.0028);
		ProductType p3 = Service.createProductType("Barrels", 0.0035);

		t1.addProductType(p1);
		t2.addProductType(p1);
		t2.addProductType(p2);
		t3.addProductType(p2);
		t4.addProductType(p3);

		t1.setDriver(d1);
		t2.setDriver(d2);
		t3.setDriver(d3);
		t4.setDriver(d4);

		d1.setTrailer(t1);
		d2.setTrailer(t2);
		d3.setTrailer(t3);
		d4.setTrailer(t4);

		SubOrder so1 = Service.createSubOrder(20000, 62, t1, p1);
		SubOrder so2 = Service.createSubOrder(19000, 53, t2, p2);

		Order o1 = Service.createOrder(1, 150, DU.createDate("2013-01-01"));

		o1.addSubOrder(so1);
		o1.addSubOrder(so2);

		// Test trailer for TrailerView
		Date dateTest = new Date(113, 0, 1, 8, 0);
		Trailer testTrailer = Service.createTrailer("TestTrailer", 25000,
				dateTest);
		testTrailer.setHasArrived(true);
		testTrailer.setLoaded(true);

	}
}
