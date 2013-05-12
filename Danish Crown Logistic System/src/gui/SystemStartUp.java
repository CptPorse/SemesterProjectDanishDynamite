package gui;

import service.Service;

//Author: Jens Nyberg Porse
public class SystemStartUp {

	private static ExternalSystemView externalSystemView;
	private static TrailerView trailerView;
	private static LoadingBayView loadingBayView;

	public static void main(String[] args) {

		Service.startUpData();
		trailerView = new TrailerView();
		loadingBayView = new LoadingBayView();
		externalSystemView = new ExternalSystemView();

	}
}
