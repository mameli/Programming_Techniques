package com.github.mameli.Bean;

import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.apache.log4j.Logger;

/**
 * Created by mameli on 19/10/2016.
 * Classe student
 */
public class Student {

    public Logger LOGGER = Logger.getLogger(Student.class);

    private Integer ID;
    private String nome;
    private String cognome;
    private Integer IDTutor;
    private String mail;
    private DB2 database;
    private MailService mailService;

    public Student(Integer ID, String nome, String cognome, Integer IDTutor, String email,DB2 database,MailService mailService) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.IDTutor = IDTutor;
        this.mail = email;
        this.database = database;
        this.mailService = mailService;
    }

    public void tutorRequest(Integer IDTutor) throws Exception {
        if (getIDTutor() != null) {
            Teacher tutorTeacher = database.getTeacherByID(getIDTutor());
            LOGGER.error("Lo studente " + this.getNome() + " " + this.getCognome() + " ha gia' il tutor " +
                        tutorTeacher.getNome()+ " " + tutorTeacher.getCognome());
            throw new Exception("Lo studente "+ this.getNome() + " " + this.getCognome() + " ha gia' un tutor");
        }
        TutorRequest tutorRequestPending = database.getTutorRequestByStudentID(this.getID());
        if (tutorRequestPending != null) {
            Teacher teacherRequested = database.getTeacherByID(tutorRequestPending.getIDTeacher());
            LOGGER.error("Lo studente " + this.getNome() + " " + this.getCognome() + " ha gia' una richiesta tutor " +
                         "al professore " + teacherRequested.getNome() +" " + teacherRequested.getCognome());
            throw new Exception("Lo studente " + this.getNome() + " " + this.getCognome() + " ha gia' una tutor request");
        }

        database.addStudentTutorRequest(new TutorRequest(this.getID(),IDTutor));
        mailService.sendEmail(database.getTeacherByID(IDTutor).getMail(),getMail(), "Request"+IDTutor.toString());
        LOGGER.info("Richiesta di tutor inviata a " + database.getTeacherByID(IDTutor).getNome()+
                                                " " + database.getTeacherByID(IDTutor).getCognome());
    }

    public void courseRequest (Integer IDCourse) throws Exception {
        CourseRequest courseRequestPending = database.getCourseRequestByStudentID(this.getID());
        if (courseRequestPending != null){
            Course courseRequested = database.getCourseByID(courseRequestPending.getIDCourse());
            LOGGER.error("Lo studente " + this.getNome() + " " + this.getCognome() + " ha gia' una richiesta al corso ID:"
                         + courseRequested.getID() +" Nome:" + courseRequested.getNome());
            throw new Exception("Lo studente "+ this.getNome() + " " + this.getCognome() + " ha gia' una course request");
        }
        database.addStudentCourseRequest(new CourseRequest(this.getID(),IDCourse));
        LOGGER.info("Richiesta di corso per "+ database.getCourseByID(IDCourse).getNome() +" inviata ");
    }

    public void sendEmailToTutor(String content) {
        mailService.sendEmail(
                database.getTeacherByID(getIDTutor()).getMail(),
                getMail(),
                content);
        LOGGER.info("Mail inviata a "+ database.getTeacherByID(getIDTutor()).getMail());
    }

    public Integer getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Integer getIDTutor() {
        return IDTutor;
    }

    public String getMail() {
        return mail;
    }
}
