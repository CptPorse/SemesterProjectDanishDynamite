package model;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dateutil.DU;

// Author Jens Nyberg Porser
public class StateTest
{
	Trailer testingTrailer;
	LoadingInfo testingLoadingInfo;
	ProductType productType;
	LoadingBay loadingBay;
	Order order1;
	SubOrder subOrder1, subOrder2;
	LoadingInfo testLoadingInfo1, testLoadingInfo2;
	Date date1, date2;

	@Before
	public void setUp() throws Exception
	{
		date1 = new Date(113, 0, 1, 9, 0);
		date2 = new Date(113, 0, 1, 10, 0);
		testingTrailer = new Trailer("1", 25000.0, date1);
		productType = new ProductType("Christmas Trees", 0.0031);
		testingTrailer.addProductType(productType);
		loadingBay = new LoadingBay(1, productType);
		order1 = new Order(1, DU.createDate("2013-01-01"));
		subOrder1 = new SubOrder(8000, testingTrailer, productType);
		subOrder2 = new SubOrder(10000, testingTrailer, productType);
		testLoadingInfo1 = new LoadingInfo(subOrder1, loadingBay);
		testLoadingInfo2 = new LoadingInfo(subOrder2, loadingBay);
		subOrder1.setLoadingInfo(testLoadingInfo1);
		subOrder2.setLoadingInfo(testLoadingInfo2);
	}

	@Test
	public void testTrailerStateChanges()
	{
		assertTrue(TrailerState.IDLE == testingTrailer.getTrailerState());
		order1.addSubOrder(subOrder1);
		order1.addSubOrder(subOrder2);
		testingTrailer.addSubOrder(subOrder1);
		testingTrailer.addSubOrder(subOrder2);
		assertTrue(TrailerState.ENROUTE == testingTrailer.getTrailerState());
		testingTrailer.registerArrival();
		assertTrue(TrailerState.ARRIVED == testingTrailer.getTrailerState());
		testingTrailer.beginLoading(testLoadingInfo1);
		assertTrue(TrailerState.BEING_LOADED == testingTrailer.getTrailerState());
		testingTrailer.endLoading(testLoadingInfo1);
		assertTrue(TrailerState.ARRIVED == testingTrailer.getTrailerState());
		testingTrailer.beginLoading(testLoadingInfo2);
		assertTrue(TrailerState.BEING_LOADED == testingTrailer.getTrailerState());
		testingTrailer.endLoading(testLoadingInfo2);
		assertTrue(TrailerState.LOADED == testingTrailer.getTrailerState());
		testingTrailer.registerDeparture();
		assertTrue(TrailerState.DEPARTED == testingTrailer.getTrailerState());

	}

	@Test
	public void testLoadingInfoStateChanges()
	{
		assertTrue(LoadingInfoState.PENDING == testLoadingInfo1.getState());
		order1.addSubOrder(subOrder1);
		order1.addSubOrder(subOrder2);
		testingTrailer.addSubOrder(subOrder1);
		testingTrailer.addSubOrder(subOrder2);
		testingTrailer.registerArrival();
		assertTrue(LoadingInfoState.READY_TO_LOAD == testLoadingInfo1.getState());
		testLoadingInfo1.beginLoading(date1);
		assertTrue(LoadingInfoState.LOADING == testLoadingInfo1.getState());
		testLoadingInfo1.endLoading(date2);
		assertTrue(LoadingInfoState.FINISHED == testLoadingInfo1.getState());

	}
}
