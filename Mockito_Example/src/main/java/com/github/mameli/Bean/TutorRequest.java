package com.github.mameli.Bean;

/**
 * Created by mameli on 20/10/2016.
 */
public class TutorRequest {
    private Integer IDStudent;
    private Integer IDTeacher;

    public TutorRequest(Integer IDStudent, Integer IDTeacher) {
        this.IDStudent = IDStudent;
        this.IDTeacher = IDTeacher;
    }

    public Integer getIDStudent() {
        return IDStudent;
    }

    public Integer getIDTeacher() {
        return IDTeacher;
    }

}
