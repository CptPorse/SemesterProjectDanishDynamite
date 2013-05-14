package service;

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

	@SuppressWarnings("deprecation")
	public static void startUpData() {

		Driver d1 = Service.createDriver("Peter Hansen", "22 37 54 98",
				"EH 95 128");
		Driver d2 = Service.createDriver("S�ren Overgaard", "22 52 12 71",
				"GH 81 411");
		Driver d3 = Service.createDriver("Morgens Nygaard", "22 14 21 45",
				"TR 92 798");
		Driver d4 = Service.createDriver("Ove Pedersen", "22 74 45 19",
				"DW 96 725");
		Driver d5 = Service.createDriver("Kasper Bilder", "22 49 98 94",
				"EQ 87 224");
		Driver d6 = Service.createDriver("Jens Sk�rb�k", "22 18 74 67",
				"KE 92 465");
		Driver d7 = Service.createDriver("Lasse Tidemann", "22 54 74 67",
				"VF 81 841");
		Driver d8 = Service.createDriver("Peder Bruun", "22 18 12 67",
				"GH 81 124");
		Driver d9 = Service.createDriver("Adam S�ndergaard", "22 47 74 67",
				"TK 78 165");
		Driver d10 = Service.createDriver("Benny Bliktud", "22 87 74 67",
				"LD 98 114");
		Driver d11 = Service.createDriver("Carsten Green", "22 46 74 67",
				"PS 94 748");
		Driver d12 = Service.createDriver("Dennis Lindstr�m", "22 32 74 67",
				"SU 84 449");
		Driver d13 = Service.createDriver("Felix Kat", "22 48 74 67",
				"PA 79 686");
		Driver d14 = Service.createDriver("Gunnar �lstyk", "22 97 74 67",
				"YE 95 782");
		Driver d15 = Service.createDriver("Henrik Huber", "22 88 74 67",
				"AN 97 354");
		Driver d16 = Service.createDriver("Ivan J�rgensen", "22 48 74 67",
				"ME 86 987");
		Driver d17 = Service.createDriver("Janus Marius", "22 23 74 67",
				"MM 78 325");
		Driver d18 = Service.createDriver("Klaus Bundgaard", "22 11 74 67",
				"SE 92 778");

		Date date1 = new Date(113, 0, 1, 9, 0);
		Date date2 = new Date(113, 0, 1, 9, 20);
		Date date3 = new Date(113, 0, 1, 9, 40);
		Date date4 = new Date(113, 0, 1, 10, 0);
		Date date5 = new Date(113, 0, 1, 10, 20);
		Date date6 = new Date(113, 0, 1, 10, 40);
		Date date7 = new Date(113, 0, 1, 11, 0);
		Date date8 = new Date(113, 0, 1, 11, 20);
		Date date9 = new Date(113, 0, 1, 11, 40);
		Date date10 = new Date(113, 0, 1, 12, 0);
		Date date11 = new Date(113, 0, 1, 12, 20);
		Date date12 = new Date(113, 0, 1, 12, 40);
		Date date13 = new Date(113, 0, 1, 13, 0);
		Date date14 = new Date(113, 0, 1, 13, 20);
		Date date15 = new Date(113, 0, 1, 13, 40);
		Date date16 = new Date(113, 0, 1, 14, 0);

		Trailer t1 = Service.createTrailer("1", 25000, date1);
		Trailer t2 = Service.createTrailer("2", 25000, date2);
		Trailer t3 = Service.createTrailer("3", 25000, date3);
		Trailer t4 = Service.createTrailer("4", 25000, date4);
		Trailer t5 = Service.createTrailer("5", 25000, date5);
		Trailer t6 = Service.createTrailer("6", 25000, date6);
		Trailer t7 = Service.createTrailer("7", 25000, date7);
		Trailer t8 = Service.createTrailer("8", 25000, date8);
		Trailer t9 = Service.createTrailer("9", 25000, date9);
		Trailer t10 = Service.createTrailer("10", 25000, date10);
		Trailer t11 = Service.createTrailer("11", 25000, date11);
		Trailer t12 = Service.createTrailer("12", 25000, date12);
		Trailer t13 = Service.createTrailer("13", 25000, date13);
		Trailer t14 = Service.createTrailer("14", 25000, date14);
		Trailer t15 = Service.createTrailer("15", 25000, date15);
		Trailer t16 = Service.createTrailer("16", 25000, date16);

		ProductType p1 = Service.createProductType("Christmas Trees", 0.0031);
		ProductType p2 = Service.createProductType("Pallets", 0.0028);
		ProductType p3 = Service.createProductType("Barrels", 0.0035);

		t1.addProductType(p1);
		t2.addProductType(p1);
		t3.addProductType(p1);
		t4.addProductType(p1);
		t5.addProductType(p1);
		t5.addProductType(p2);
		t6.addProductType(p1);
		t6.addProductType(p3);
		t7.addProductType(p2);
		t8.addProductType(p2);
		t9.addProductType(p2);
		t10.addProductType(p2);
		t11.addProductType(p2);
		t11.addProductType(p3);
		t12.addProductType(p3);
		t13.addProductType(p3);
		t14.addProductType(p3);
		t15.addProductType(p3);
		t16.addProductType(p3);
		t16.addProductType(p2);

		t1.setDriver(d1);
		t2.setDriver(d2);
		t3.setDriver(d3);
		t4.setDriver(d4);
		t5.setDriver(d5);
		t6.setDriver(d6);
		t7.setDriver(d7);
		t8.setDriver(d8);
		t9.setDriver(d9);
		t10.setDriver(d10);
		t11.setDriver(d11);
		t12.setDriver(d12);
		t13.setDriver(d13);
		t14.setDriver(d14);
		t15.setDriver(d15);
		t16.setDriver(d16);

		d1.setTrailer(t1);
		d2.setTrailer(t2);
		d3.setTrailer(t3);
		d4.setTrailer(t4);
		d5.setTrailer(t5);
		d6.setTrailer(t6);
		d7.setTrailer(t7);
		d8.setTrailer(t8);
		d9.setTrailer(t9);
		d10.setTrailer(t10);
		d11.setTrailer(t11);
		d12.setTrailer(t12);
		d13.setTrailer(t13);
		d14.setTrailer(t14);
		d15.setTrailer(t15);
		d16.setTrailer(t16);

		SubOrder so1 = Service.createSubOrder(20000, t1, p1);
		SubOrder so2 = Service.createSubOrder(19000, t2, p1);
		SubOrder so3 = Service.createSubOrder(18000, t3, p1);
		SubOrder so4 = Service.createSubOrder(24000, t4, p1);
		SubOrder so5 = Service.createSubOrder(12000, t5, p1);
		SubOrder so6 = Service.createSubOrder(12000, t5, p2);
		SubOrder so7 = Service.createSubOrder(8000, t6, p1);
		SubOrder so8 = Service.createSubOrder(16000, t6, p3);
		SubOrder so9 = Service.createSubOrder(20000, t7, p2);
		SubOrder so10 = Service.createSubOrder(16000, t8, p2);
		SubOrder so11 = Service.createSubOrder(24000, t9, p2);
		SubOrder so12 = Service.createSubOrder(24500, t10, p2);
		SubOrder so13 = Service.createSubOrder(9000, t11, p2);
		SubOrder so14 = Service.createSubOrder(9000, t11, p3);
		SubOrder so15 = Service.createSubOrder(23000, t12, p3);
		SubOrder so16 = Service.createSubOrder(24000, t13, p3);
		SubOrder so17 = Service.createSubOrder(20000, t14, p3);
		SubOrder so18 = Service.createSubOrder(19000, t15, p3);
		SubOrder so19 = Service.createSubOrder(12000, t16, p3);
		SubOrder so20 = Service.createSubOrder(11000, t16, p2);

		Order o1 = Service.createOrder(1, 150, DU.createDate("2013-01-01"));
		Order o2 = Service.createOrder(2, 150, DU.createDate("2013-01-01"));
		Order o3 = Service.createOrder(3, 150, DU.createDate("2013-01-01"));
		Order o4 = Service.createOrder(4, 150, DU.createDate("2013-01-01"));
		Order o5 = Service.createOrder(5, 150, DU.createDate("2013-01-01"));
		Order o6 = Service.createOrder(6, 150, DU.createDate("2013-01-01"));
		Order o7 = Service.createOrder(7, 150, DU.createDate("2013-01-01"));
		Order o8 = Service.createOrder(8, 150, DU.createDate("2013-01-01"));
		Order o9 = Service.createOrder(9, 150, DU.createDate("2013-01-01"));
		Order o10 = Service.createOrder(10, 150, DU.createDate("2013-01-01"));
		Order o11 = Service.createOrder(11, 150, DU.createDate("2013-01-01"));
		Order o12 = Service.createOrder(12, 150, DU.createDate("2013-01-01"));
		Order o13 = Service.createOrder(13, 150, DU.createDate("2013-01-01"));
		Order o14 = Service.createOrder(14, 150, DU.createDate("2013-01-01"));
		Order o15 = Service.createOrder(15, 150, DU.createDate("2013-01-01"));
		Order o16 = Service.createOrder(15, 150, DU.createDate("2013-01-01"));
		Order o17 = Service.createOrder(15, 150, DU.createDate("2013-01-01"));

		o1.addSubOrder(so1);
		o2.addSubOrder(so2);
		o3.addSubOrder(so3);
		o4.addSubOrder(so4);
		o5.addSubOrder(so5);
		o5.addSubOrder(so6);
		o6.addSubOrder(so7);
		o6.addSubOrder(so8);
		o7.addSubOrder(so9);
		o8.addSubOrder(so10);
		o9.addSubOrder(so11);
		o10.addSubOrder(so12);
		o11.addSubOrder(so13);
		o11.addSubOrder(so14);
		o12.addSubOrder(so14);
		o13.addSubOrder(so15);
		o14.addSubOrder(so16);
		o15.addSubOrder(so17);
		o16.addSubOrder(so18);
		o17.addSubOrder(so19);
		o15.addSubOrder(so20);

		LoadingBay lb1 = Service.createLoadingBay(1, p1);
		LoadingBay lb2 = Service.createLoadingBay(2, p1);
		LoadingBay lb3 = Service.createLoadingBay(3, p2);
		LoadingBay lb4 = Service.createLoadingBay(4, p2);
		LoadingBay lb5 = Service.createLoadingBay(5, p3);

		// These are currently hardcoded, unsure if we meant for system to
		// create them. None the less, hardcoded for now for testing.
		LoadingInfo li1 = Service.createLoadingInfo(so1, lb1);
		LoadingInfo li2 = Service.createLoadingInfo(so2, lb1);
		LoadingInfo li3 = Service.createLoadingInfo(so3, lb1);
		LoadingInfo li4 = Service.createLoadingInfo(so4, lb1);
		LoadingInfo li5 = Service.createLoadingInfo(so5, lb1);
	}
}
