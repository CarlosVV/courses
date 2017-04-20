package edu.mum.cs.projects.attendance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import edu.mum.cs.projects.attendance.domain.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.complex.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Session;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Service layer facade, hides away details of dataaccess layer from client.</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class AttendanceServiceImpl implements AttendanceService {
	
	private CourseService courseService = ServiceFactory.getCourseService();
	private DataAccessService dataAccessService = ServiceFactory.getDataAccessService();

	@Override
	public List<StudentAttendance> retrieveStudentAttendanceRecords(String courseOfferingId) {
		
		CourseOffering offering = courseService.getCourseOffering(courseOfferingId);
		
		List<Registration> registrationList = courseService.getRegistrationList(courseOfferingId);
		
		if(null == registrationList || registrationList.isEmpty()) {
			return null;
		}
		
		List<BarcodeRecord> barcodeRecords = dataAccessService.getBarcodeRecordsList(offering.getBlock().getStartDate(), offering.getBlock().getEndDate());
		
		System.out.println("\nCreating attendance report for: " + courseOfferingId + ", " + offering.getFaculty());

		List<StudentAttendance> studentAttendanceRecords = registrationList.stream()
			.map(r -> new StudentAttendance(r.getStudent(), offering))
			.peek(populateAttendanceArray(barcodeRecords))
			.peek(System.out::println)
			.collect(Collectors.toList());
		
		OptionalDouble average = studentAttendanceRecords.stream().mapToDouble(sa -> sa.getMeditaionPercentage()).average();		
		if(average.isPresent()) {
			System.out.printf("Average group meditation participation for this class is: %5.1f%s\n", average.getAsDouble(), "%");
		}
		
		return studentAttendanceRecords;
	}
	
	private Consumer<? super StudentAttendance> populateAttendanceArray(List<BarcodeRecord> barcodeRecords) {
		return sa -> { 
			List<Boolean> attendance = new ArrayList<Boolean>(sa.getCourseOffering().getSessions().size());
			sa.setAttendance(attendance);
			
			boolean noBarcode = (null == sa.getStudent().getBarCode());
		
			for(Session session : sa.getCourseOffering().getSessions()) {
				if(noBarcode) {
					attendance.add(false);
				}
				else {
					attendance.add(barcodeRecords.stream()
						.filter(br -> br.getBarcode().equals(sa.getStudent().getBarCode()))
						.filter(br -> br.getDate().equals(session.getDate()))
						.filter(br -> br.getTimeslot().equals(session.getTimeslot().getAbbreviation()))
						.findFirst().isPresent());
				}
			}	
			
		};
	}
	
	@Override
	public void createStudentAttendanceSpreadsheetsForBlock(String blockId) {
		for(CourseOffering offering : courseService.getCourseOfferings(blockId)) {
			createStudentAttendanceSpreadsheetForOffering(offering.getId());
		}
		
	}

	@Override
	public void createStudentAttendanceSpreadsheetForOffering(String courseOfferingId) {
		List<StudentAttendance> studentAttendanceList = retrieveStudentAttendanceRecords(courseOfferingId);
		dataAccessService.createAttendanceReportSpreadsheet(studentAttendanceList);
	}

}
