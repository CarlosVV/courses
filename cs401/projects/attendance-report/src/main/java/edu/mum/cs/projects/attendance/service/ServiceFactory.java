package edu.mum.cs.projects.attendance.service;

public class ServiceFactory {
	private volatile static CourseService courseService;
	private volatile static StudentService studentService;
	private volatile static AttendanceService attendanceService;
	private volatile static DataAccessService dataAccessService;
	
	public static CourseService getCourseService() {
		if(null == courseService) {
			synchronized(ServiceFactory.class) {
				if(null == courseService) {
					courseService = new CourseServiceImpl();
				}
			}
		}
		return courseService;
	}
	
	public static StudentService getStudentService() {
		if(null == studentService) {
			synchronized(ServiceFactory.class) {
				if(null == studentService) {
					studentService = new StudentServiceImpl();
				}
			}
		}
		return studentService;
	}
	
	public static AttendanceService getAttendanceService() {
		if(null == attendanceService) {
			synchronized(ServiceFactory.class) {
				if(null == attendanceService) {
					attendanceService = new AttendanceServiceImpl();
				}
			}
		}
		return attendanceService;
	}

	public static DataAccessService getDataAccessService() {
		if(null == dataAccessService) {
			synchronized(ServiceFactory.class) {
				if(null == dataAccessService) {
					dataAccessService = new DataAccessServiceImpl();
				}
			}
		}
		return dataAccessService;
	}
}
