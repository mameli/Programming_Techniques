package com.github.mameli.Interface;

import com.github.mameli.Bean.*;

import java.util.List;


/**
 * Created by mameli on 20/10/2016.
 */
public interface DB2 {

    public Student getStudentByID(Integer IDStudent);

    public Teacher getTeacherByID(Integer IDTeacher);

    public Course  getCourseByID(Integer IDCourse);

    public TutorRequest getTutorRequestByStudentID(Integer IDStudent);

    public CourseRequest getCourseRequestByStudentID(Integer IDStudent);

    public List<Student> getListOfStudentTutoredByTeacherID(Integer IDTeacher);

    public void assignStudentToTeacher(Integer IDTeacher, Integer IDStudent);

    public void addStudentTutorRequest(TutorRequest tutorRequest);

    public void addStudentCourseRequest(CourseRequest courseRequest);

    public void addStudentToCourse(CourseAttendance courseAttendance);

    public void updateCourseTeacher(Integer id, Integer idTeacher);
}
