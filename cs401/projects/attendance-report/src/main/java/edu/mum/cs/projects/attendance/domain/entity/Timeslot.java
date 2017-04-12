package edu.mum.cs.projects.attendance.domain.entity;

import java.time.LocalTime;

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
public class Timeslot {

	private String abbreviation;

    private String description;

    private LocalTime beginTime;

    private LocalTime endTime;

    public Timeslot() {
    }

    public Timeslot(String abbreviation, String description, LocalTime beginTime, LocalTime endTime) {
        this.abbreviation = abbreviation;
        this.description = description;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timeslot timeslot = (Timeslot) o;

        if (abbreviation != null ? !abbreviation.equals(timeslot.abbreviation) : timeslot.abbreviation != null)
            return false;
        if (description != null ? !description.equals(timeslot.description) : timeslot.description != null)
            return false;
        if (beginTime != null ? !beginTime.equals(timeslot.beginTime) : timeslot.beginTime != null) return false;
        return endTime != null ? endTime.equals(timeslot.endTime) : timeslot.endTime == null;
    }

    @Override
    public int hashCode() {
        int result = abbreviation != null ? abbreviation.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
	public String toString() {
		return "Timeslot [abbreviation=" + abbreviation + ", description=" + description + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + "]";
	}
}
