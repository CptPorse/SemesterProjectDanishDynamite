package model;

import java.util.Date;

public class test
{

	public static void main(String[] args)
	{
		Trailer testingTrailer = new Trailer("1", 25000.0, new Date(113, 0, 1, 9, 0));

		if (testingTrailer.getTrailerState() == TrailerState.IDLE) {
			System.out.println("True");
		}

	}

}
