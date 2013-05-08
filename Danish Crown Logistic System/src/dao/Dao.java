package dao;

import java.util.ArrayList;

import model.Driver;
import model.Order;
import model.ProductType;
import model.SubOrder;
import model.Trailer;

public class Dao {

	private static final ArrayList<ProductType> productTypes = new ArrayList<ProductType>();
	private static final ArrayList<Driver> drivers = new ArrayList<Driver>();
	private static final ArrayList<Trailer> trailers = new ArrayList<Trailer>();
	private static final ArrayList<Order> orders = new ArrayList<Order>();
	private static final ArrayList<SubOrder> subOrders = new ArrayList<SubOrder>();

	/**
	 * Returns a list of all productTypes in the database.
	 */
	public static ArrayList<ProductType> getProductTypes() {
		return new ArrayList<ProductType>(productTypes);
	}

	/**
	 * Adds the productType to this in the database.
	 */
	public static void addProductType(ProductType productType) {
		productTypes.add(productType);
	}

	/**
	 * Removes the productType from this database.
	 */
	public static void removeProductType(ProductType productType) {
		productTypes.remove(productType);
	}

	/**
	 * Returns a list of all drivers in the database.
	 */
	public static ArrayList<Driver> getDrivers() {
		return new ArrayList<Driver>(drivers);
	}

	/**
	 * Adds the driver to this in the database.
	 */
	public static void addDriver(Driver driver) {
		drivers.add(driver);
	}

	/**
	 * Removes the driver from this database.
	 */
	public static void removeDriver(Driver driver) {
		drivers.remove(driver);
	}

	/**
	 * Returns a list of all trailers in the database.
	 */
	public static ArrayList<Trailer> getTrailer() {
		return new ArrayList<Trailer>(trailers);
	}

	/**
	 * Adds the trailer to this in the database.
	 */
	public static void addTrailer(Trailer trailer) {
		trailers.add(trailer);
	}

	/**
	 * Removes the trailer from this database.
	 */
	public static void removeTrailer(Trailer trailer) {
		trailers.remove(trailer);
	}

	/**
	 * Returns a list of sub-orders in this Order.
	 */
	public static ArrayList<Order> getOrders() {
		return new ArrayList<Order>(orders);
	}

	/**
	 * Adds the sub-order to this Order.
	 */
	public static void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * Removes the sub-order from this Order.
	 */
	public static void removeOrder(Order order) {
		orders.remove(order);
	}

	/**
	 * Returns a list of sub-orders in this Order.
	 */
	public static ArrayList<SubOrder> getSubOrders() {
		return new ArrayList<SubOrder>(subOrders);
	}

	/**
	 * Adds the sub-order to this Order.
	 */
	public static void addSubOrder(SubOrder subOrder) {
		subOrders.add(subOrder);
	}

	/**
	 * Removes the sub-order from this Order.
	 */
	public static void removeSubOrder(SubOrder subOrder) {
		subOrders.remove(subOrder);
	}

}
