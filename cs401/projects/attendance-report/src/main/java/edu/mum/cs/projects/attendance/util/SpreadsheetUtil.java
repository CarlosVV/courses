package edu.mum.cs.projects.attendance.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Location;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Session;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Timeslot;
import edu.mum.cs.projects.attendance.util.parser.AcademicBlockParser;
import edu.mum.cs.projects.attendance.util.parser.CourseOfferingParser;
import edu.mum.cs.projects.attendance.util.parser.CourseParser;
import edu.mum.cs.projects.attendance.util.parser.LocationParser;
import edu.mum.cs.projects.attendance.util.parser.RegistrationParser;
import edu.mum.cs.projects.attendance.util.parser.SessionParser;
import edu.mum.cs.projects.attendance.util.parser.StudentParser;
import edu.mum.cs.projects.attendance.util.parser.TimeslotParser;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Utility that loads data from Excel file "BaseData.xlsx". Acts as the main dataaccess layer. 
 * All lists are singleton so that data would only be read once from spreadsheet.</p>
 *
 * @author Hong An Nguyen
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class SpreadsheetUtil {
	public static final String FILE_LOCATION = "src/main/resources/BaseData.xlsx";

	private static final String SHEET_COURSE = "Course";
	private static final String SHEET_COURSE_OFFERING = "CourseOffering";
	private static final String SHEET_LOCATION = "Location";
	private static final String SHEET_SESSION = "Session";
	private static final String SHEET_STUDENT = "Student";
	private static final String SHEET_TIMESLOT = "Timeslot";
	private static final String SHEET_BLOCK = "AcademicBlock";
	private static final String SHEET_REGISTRATION = "Registration";

	private volatile static List<Course> courseList = null;
	private volatile static List<CourseOffering> courseOfferingList = null;
	private volatile static List<Timeslot> timeslotList = null;
	private volatile static List<Student> studentList = null;
	private volatile static List<Session> sessionList = null;
	private volatile static List<Location> locationList = null;
	private volatile static List<AcademicBlock> academicBlockList = null;
	private volatile static List<Registration> registrationList = null;

	private static Workbook workbook = null;
	
	public static Workbook getWorkbook() {
		if (workbook == null) {
			try {
				workbook = new XSSFWorkbook(FILE_LOCATION);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}

	public static void closeWorkbook() throws IOException {
		workbook.close();
	}

	public static List<Registration> getAllRegistrations() {
		if (null == registrationList) {
			System.out.println("Loading list of registrations...");
			registrationList = getListEntities(getWorkbook().getSheet(SHEET_REGISTRATION), registrationList, new RegistrationParser());
		}
		return registrationList;
	}

	public static List<AcademicBlock> getAcademicBlockList() {
		if (null == academicBlockList) {
			System.out.println("Loading list of academic blocks...");
			academicBlockList = getListEntities(getWorkbook().getSheet(SHEET_BLOCK), academicBlockList, new AcademicBlockParser());
		}
		return academicBlockList;
	}

	public static List<Course> getCourseList() {
		if (null == courseList) {
			System.out.println("Loading list of courses...");
			courseList = getListEntities(getWorkbook().getSheet(SHEET_COURSE), courseList, new CourseParser());
		}
		return courseList;
	}

	public static List<CourseOffering> getAllCourseOfferings() {
		if (null == courseOfferingList) {
			System.out.println("Loading list of course offerings...");
			courseOfferingList = getListEntities(getWorkbook().getSheet(SHEET_COURSE_OFFERING), courseOfferingList,
					new CourseOfferingParser());
		}
		return courseOfferingList;
	}

	public static List<Timeslot> getTimeslotList() {
		if (null == timeslotList) {
			System.out.println("Loading list of course timeslots...");
			timeslotList = getListEntities(getWorkbook().getSheet(SHEET_TIMESLOT), timeslotList, new TimeslotParser());
		}
		return timeslotList;
	}

	public static List<Student> getStudentList() {
		if (null == studentList) {
			System.out.println("Loading list of students...");
			studentList = getListEntities(getWorkbook().getSheet(SHEET_STUDENT), studentList, new StudentParser());
		}
		return studentList;
	}

	public static List<Session> getSessionList() {
		if (null == sessionList) {
			System.out.println("Loading list of sessions...");
			sessionList = getListEntities(getWorkbook().getSheet(SHEET_SESSION), sessionList, new SessionParser());
		}
		return sessionList;
	}

	public static List<Location> getLocationList() {
		if (null == locationList) {
			System.out.println("Loading list of course locations...");
			locationList = getListEntities(getWorkbook().getSheet(SHEET_LOCATION), locationList, new LocationParser());
		}
		return locationList;
	}

	private static synchronized <T> List<T> getListEntities(Sheet sheet, List<T> list, Function<Row, T> mapper) {
		if (null != list)
			return list;

		list = new ArrayList<T>();

		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			list.add(mapper.apply(rowIterator.next()));
		}

		return list;
	}
}
