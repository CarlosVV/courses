package edu.mum.cs.projects.attendance.domain.entity;

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
public class Student {

	private String id;
	
    private String firstName;
    
    private String lastName;
    
    private String barCode;

    public Student() {
    }

    public Student(String id) {
		super();
		this.id = id;
	}

	public Student(String id, String firstName, String lastName, String barCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.barCode = barCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        return barCode != null ? barCode.equals(student.barCode) : student.barCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (barCode != null ? barCode.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", barCode="
				+ barCode + "]";
	}
}
