package com.github.mameli.Bean;

import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by mameli on 19/10/2016.
 * Classe teacher
 */
public class Teacher {

    Logger LOGGER = Logger.getLogger(Teacher.class);

    private Integer ID;
    private String nome;
    private String cognome;
    private String mail;
    private DB2 database;
    private MailService mailService;


    public Teacher(Integer ID, String nome, String cognome, String mail, DB2 database, MailService mailService) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.database = database;
        this.mailService = mailService;
    }

    public void tutorRequest(int IDStudent) throws Exception {
        if (database.getListOfStudentTutoredByTeacherID(this.getID()).size() >= 3){
            LOGGER.error("Il professore " + this.getNome() + " " + this.getCognome() + " ha gia' tre studenti associati. " +
                         "Richiesta non Accettata.");
            throw new Exception("Numero di studenti associati superato");
        }
        database.assignStudentToTeacher(this.getID(),IDStudent);
        Student studentToTutor = database.getStudentByID(IDStudent);
        mailService.sendEmail(studentToTutor.getMail(),this.getMail(), "Richiesta di tutoraggio accettata.");
        LOGGER.info("Richiesta di tutoraggio per lo studente "+ studentToTutor.getNome() + " " + studentToTutor.getCognome()+ " accettata");
    }

    public void sendEmailToAllStudents(String content){
        List<Student> listaStudenti = database.getListOfStudentTutoredByTeacherID(this.getID());
        if (listaStudenti.size() != 0){
            for (Student s: listaStudenti) {
                mailService.sendEmail(this.getMail(),s.getMail(),content);
            }
            LOGGER.info("Email inviate a tutti gli studenti");
        }
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

    public String getMail() {
        return mail;
    }

}
