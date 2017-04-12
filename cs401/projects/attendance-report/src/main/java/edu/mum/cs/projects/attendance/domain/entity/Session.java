package edu.mum.cs.projects.attendance.domain.entity;

import java.time.LocalDate;

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
public class Session {
	
    private int id;

    private AcademicBlock block;

    private Timeslot timeslot;

    private LocalDate date;

	public Session() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AcademicBlock getBlock() {
		return block;
	}

	public void setBlock(AcademicBlock block) {
		this.block = block;
	}

	public Timeslot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
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
		Session other = (Session) obj;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
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
		return "Session [id=" + id + ", block=" + block + ", timeslot=" + timeslot + ", date=" + date + "]";
	}
}
