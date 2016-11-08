package com.github.mameli;

import com.github.mameli.Bean.*;
import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

/**
 * Created by mameli on 19/10/2016.
 * Classe test di teacher
 */
public class TeacherTest {

    private Teacher teacher1;
    private Teacher teacher2;
    private Student student1;
    private Student student2;
    private Student student3;
    @Mock DB2 database;
    @Mock MailService mailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        teacher1 = new Teacher(1,"Michele","Loreti","michele.loreti@unifi.it",database,mailService);
        teacher2 = new Teacher(1,"Cecilia","Verri","cecilia.verri@unifi.it",database,mailService);
        student1 = new Student(1234,"Mario","Rossi",1,"mario.rossi@unifi.it",database,mailService);
        student2 = new Student(5678,"Lorenzo","Neri",null,"lorenzo.neri@unifi.it",database,mailService);
        student3 = new Student(4321,"Luca","Bianchi",null,"luca.bianchi@unifi.it",database,mailService);
        Course course1 = new Course(1,"Reti","reti@unifi.it",teacher1.getID(),database,mailService);

        when(database.getTeacherByID(teacher1.getID())).thenReturn(teacher1);
        when(database.getCourseByID(1)).thenReturn(course1);
    }

    @Test(expected = Exception.class)
    public void tutorRequestTestAlredyThreeStudents() throws Exception {
        when(database.getListOfStudentTutoredByTeacherID(teacher1.getID())).thenReturn(asList(student1,student2,student3));
        teacher1.tutorRequest(1234);
    }

    @Test
    public void tutorRequestTestBase() throws Exception {
        when(database.getListOfStudentTutoredByTeacherID(teacher2.getID())).thenReturn(new ArrayList<Student>());
        when(database.getStudentByID(1234)).thenReturn(student1);
        teacher2.tutorRequest(1234);
    }

    @Test
    public void sendEmailsToStudents(){
        when(database.getListOfStudentTutoredByTeacherID(teacher1.getID())).thenReturn(asList(student1,student2,student3));
        teacher1.sendEmailToAllStudents("Contenuto email");
    }

}