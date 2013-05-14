package model;

//Author: Soren Moller Nielsen
public enum TrailerState {
	/*
	 * Idle = When a trailer haven't got a suborder yet Enroute = Whene a
	 * trailer got a trailer, but haven't arrived on the factory yet Arrived =
	 * When the trailer is ready for being loaded Being_loaded = When a trailer
	 * is in the process of being loaded Loaded = Ready to departure, just need
	 * to have its weight checked Departed = The trailer has left the factory
	 */

	IDLE, ENROUTE, ARRIVED, BEING_LOADED, LOADED, DEPARTED;
}
