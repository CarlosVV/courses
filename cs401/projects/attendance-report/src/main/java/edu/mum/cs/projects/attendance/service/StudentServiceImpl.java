package edu.mum.cs.projects.attendance.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.mum.cs.projects.attendance.domain.entity.Student;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Service layer facade, hides away details of dataaccess layer from client.</p>
 *
 * @see edu.mum.cs.projects.attendance.service.StudentService
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class StudentServiceImpl implements StudentService {
	
	private DataAccessService dataAccessService = ServiceFactory.getDataAccessService();
	
	private final Map<String, Student> studentsMapById;
	
	{
		// Generate maps once, only during object instantiation
		studentsMapById = getStudentsMap(Student::getId);
	}
	
	private Map<String, Student> getStudentsMap(Function<? super Student, ? extends String> mapper) {
		return getAllStudents()
				.stream()
				.collect(Collectors.toMap(mapper, Function.identity()));
	}

	@Override
	public List<Student> getAllStudents() {
		return dataAccessService.getAllStudents();
	}

	@Override
	public Map<String, Student> getStudentsMapById() {
		return studentsMapById;
	}

	@Override
	public Student getStudentById(String studentId) {
		if(null == studentId) {
			return null;
		}
		
		Student student = getStudentsMapById().get(studentId);
		
		return (null == student) ? new Student(studentId) : student;
	}

}
