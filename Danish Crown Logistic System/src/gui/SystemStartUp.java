package gui;

import service.StartUpService;

//Author: Jens Nyberg Porse
public class SystemStartUp {

	private static ExternalSystemView externalSystemView;
	private static TrailerView trailerView;
	private static LoadingBayView loadingBayView;

	public static void main(String[] args) {

		StartUpService.startUpData();
		trailerView = new TrailerView();
		loadingBayView = new LoadingBayView();
		externalSystemView = new ExternalSystemView();

	}
}
