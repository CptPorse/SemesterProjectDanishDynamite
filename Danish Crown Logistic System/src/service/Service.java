package service;

import java.util.Date;

import model.Driver;
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

	public static Trailer createTrailer(String trailerID, double weightMax) {
		Trailer trailer = new Trailer(trailerID, weightMax);
		Dao.addTrailer(trailer);
		return trailer;
	}

	public static ProductType createProductType(String description) {
		ProductType productType = new ProductType(description);
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

		Trailer t1 = Service.createTrailer("1", 25000);
		Trailer t2 = Service.createTrailer("2", 25000);
		Trailer t3 = Service.createTrailer("3", 25000);
		Trailer t4 = Service.createTrailer("4", 25000);

		ProductType p1 = Service.createProductType("Christmas Trees");
		ProductType p2 = Service.createProductType("Pallets");
		ProductType p3 = Service.createProductType("Barrels");

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

		SubOrder so1 = Service.createSubOrder(20000, 45, t1, p1);
		SubOrder so2 = Service.createSubOrder(19000, 48, t2, p2);

		Order o1 = Service.createOrder(1, 150, DU.createDate("2013-02-02"));

		o1.addSubOrder(so1);
		o1.addSubOrder(so2);
	}
}
