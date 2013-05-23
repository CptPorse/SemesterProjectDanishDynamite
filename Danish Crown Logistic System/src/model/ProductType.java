package model;

import org.eclipse.jdt.annotation.NonNullByDefault;

//Author: Jens Nyberg Porse
@NonNullByDefault
public class ProductType
{

	private String description;
	private double minuteToKiloRatio;

	public ProductType(String description, double minuteToKiloRatio)
	{
		super();
		this.description = description;
		this.minuteToKiloRatio = minuteToKiloRatio;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description: The description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the minuteToKiloRatio
	 */
	public double getMinuteToKiloRatio()
	{
		return minuteToKiloRatio;
	}

	/**
	 * @param minuteToKiloRatio: The minuteToKiloRatio to set
	 */
	public void setMinuteToKiloRatio(double minuteToKiloRatio)
	{
		this.minuteToKiloRatio = minuteToKiloRatio;
	}

	@Override
	public String toString()
	{
		return "" + description;
	}

}
