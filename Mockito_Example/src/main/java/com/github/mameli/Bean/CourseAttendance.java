package com.github.mameli.Bean;

/**
 * Created by mameli on 20/10/2016.
 */
public class CourseAttendance {
    private Integer IDStudent;
    private Integer IDCourse;

    public CourseAttendance(Integer IDStudent, Integer IDCourse) {
        this.IDStudent = IDStudent;
        this.IDCourse = IDCourse;
    }

    public Integer getIDStudent() {
        return IDStudent;
    }

    public void setIDStudent(Integer IDStudent) {
        this.IDStudent = IDStudent;
    }

    public Integer getIDCourse() {
        return IDCourse;
    }

    public void setIDCourse(Integer IDCourse) {
        this.IDCourse = IDCourse;
    }
}
