package model;

//Author: Christian Møller Pedersen
/*
 * PENDING: 		Trailer is on its way to DC.
 * READY_TO_LOAD: 	Trailer has arrived at DC and is waiting to begin loading.
 * LOADING: 		Trailer is currently being loaded.
 * FINISHED: 		Trailer is loaded and leaving DC.
 */
public enum LoadingInfoState
{
	PENDING, READY_TO_LOAD, LOADING, FINISHED;
}
