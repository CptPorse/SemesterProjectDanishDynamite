package gui;

import service.Service;
import service.StartUpService;

//Author: Jens Nyberg Porse
public class SystemStartUp
{

	private static ExternalSystemView externalSystemView;
	private static TrailerView trailerView;
	private static LoadingBayView loadingBayView;

	public static void main(String[] args)
	{

		StartUpService.startUpData();
		Service.sortTrailerArrival();
		StartUpService.setSubOrderEarliestLoadingTime();
		StartUpService.createLoadingBaySchedule();
		trailerView = new TrailerView();
		loadingBayView = new LoadingBayView();
		externalSystemView = new ExternalSystemView();
	}
}
