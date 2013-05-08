package model;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ProductType {

	private String description;
	private ArrayList<Trailer> trailers = new ArrayList<Trailer>();

	public ProductType(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Nullable
	public ArrayList<Trailer> getTrailer() {
		return new ArrayList<Trailer>(trailers);
	}

	public void addTrailer(Trailer trailer) {
		trailers.add(trailer);
	}

	public void removeTrailer(Trailer trailer) {
		trailers.remove(trailer);
	}

	@Override
	public String toString() {
		return "" + description;
	}

}
