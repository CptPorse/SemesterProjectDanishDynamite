package model;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

//Author: Jens Porse Nyberg
@NonNullByDefault
public class Driver
{

	private String name;
	private String phoneNumber;
	private String licensePlate;
	private Trailer trailer = null;

	public Driver(String name, String phoneNumber, String licensePlate)
	{
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name: The name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * @param phoneNumber: The phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate()
	{
		return licensePlate;
	}

	/**
	 * @param licensePlate: The licensePlate to set
	 */
	public void setLicensePlate(String licensePlate)
	{
		this.licensePlate = licensePlate;
	}

	/**
	 * @return a list of Trailers carrying this productType.
	 */
	@Nullable
	public Trailer getTrailer()
	{
		return trailer;
	}

	/**
	 * Adds a Trailers to carry this productType.
	 */
	public void setTrailer(Trailer trailer)
	{
		this.trailer = trailer;
	}

	/**
	 * Removes a Trailers from carrying this productType.
	 */
	public void clearTrailer()
	{
		this.trailer = null;
	}

	@Override
	public String toString()
	{
		return "" + name;
	}

}
