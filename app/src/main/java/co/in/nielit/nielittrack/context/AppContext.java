package co.in.nielit.nielittrack.context;

import android.app.Application;

import java.util.ArrayList;

import co.in.nielit.nielittrack.models.Attendance;
import co.in.nielit.nielittrack.models.AttendanceSession;
import co.in.nielit.nielittrack.models.Faculty;
import co.in.nielit.nielittrack.models.Student;

public class AppContext extends Application {
    private Faculty faculty;
    private AttendanceSession attendanceSession;
    private ArrayList<Student> studentList;
    private ArrayList<Attendance> attendanceList;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public AttendanceSession getAttendanceSession() {
        return attendanceSession;
    }

    public void setAttendanceSession(AttendanceSession attendanceSession) {
        this.attendanceSession = attendanceSession;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
