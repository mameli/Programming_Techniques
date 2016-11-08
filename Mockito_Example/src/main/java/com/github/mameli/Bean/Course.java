package com.github.mameli.Bean;

import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.apache.log4j.Logger;

/**
 * Created by mameli on 19/10/2016.
 * Classe Course
 */
public class Course {

    private Logger LOGGER = Logger.getLogger(Course.class);
    private Integer ID;
    private String nome;
    private String mail;
    private Integer IDTeacher;
    private DB2 database;
    private MailService mailService;

    public Course(Integer ID, String nome, String mail, Integer IDTeacher, DB2 database, MailService mailService) {
        this.ID = ID;
        this.nome = nome;
        this.mail = mail;
        this.IDTeacher = IDTeacher;
        this.database = database;
        this.mailService = mailService;
    }

    public void assignTeacherToCourse(Integer IDTeacher) {
        Teacher oldTeacher = database.getTeacherByID(this.getIDTeacher());
        Teacher newTeacher = database.getTeacherByID(IDTeacher);

        mailService.sendEmail(oldTeacher.getMail(),this.getMail(),"Non tieni il corso: "+ this.getNome());
        mailService.sendEmail(newTeacher.getMail(),this.getMail(),"Ora tieni il corso: "+ this.getNome());
        this.IDTeacher = newTeacher.getID();
        database.updateCourseTeacher(this.getID(),IDTeacher);

        LOGGER.info("Professore aggiornato");
    }

    public void courseRequest(Integer IDStudent){
        Student newStudent = database.getStudentByID(IDStudent);
        CourseAttendance courseAttendance = new CourseAttendance(IDStudent,this.getID());
        database.addStudentToCourse(courseAttendance);
        mailService.sendEmail(newStudent.getMail(),this.getMail(),"Conferma richiesta corso");
        LOGGER.info("Richiesta dello studente " + courseAttendance.getIDStudent() + " al corso "
                                                + courseAttendance.getIDCourse()+ " confermata");
    }

    public Integer getID() {
        return ID;
    }


    public String getNome() {
        return nome;
    }


    public String getMail() {
        return mail;
    }

    public Integer getIDTeacher() {
        return IDTeacher;
    }
}
