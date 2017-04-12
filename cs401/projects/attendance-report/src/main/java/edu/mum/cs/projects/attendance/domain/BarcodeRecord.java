package edu.mum.cs.projects.attendance.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Domain entity. Simple POJO.</p>
 *
 * @author Hong An Nguyen
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class BarcodeRecord {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

	private String barcode;
	private LocalDate date;
	private String timeslot;
	private String location;
	
	public BarcodeRecord(String fileData) {
		String[] parts = fileData.split(",");
		this.barcode = parts[0];
		this.date = LocalDate.parse(parts[1], formatter);
		this.timeslot = parts[2];
		this.location = parts[3];
	}

	public BarcodeRecord(String barcode, LocalDate date, String timeslot, String location) {
		super();
		this.barcode = barcode;
		this.date = date;
		this.timeslot = timeslot;
		this.location = location;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((timeslot == null) ? 0 : timeslot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarcodeRecord other = (BarcodeRecord) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (timeslot == null) {
			if (other.timeslot != null)
				return false;
		} else if (!timeslot.equals(other.timeslot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BarcodeRecord [barcode=" + barcode + ", date=" + date + ", timeslot=" + timeslot + ", location="
				+ location + "]";
	}

}
