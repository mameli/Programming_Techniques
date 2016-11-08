package com.github.mameli;

import com.github.mameli.Bean.*;
import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


/**
 * Created by mameli on 19/10/2016.
 * Classe test di student
 */
public class StudentTest {

    private Student studentTutored;
    private Student studentNullTutorOneRequest;
    private Student studentNullTutor;
    private Teacher teacher1;
    private TutorRequest tutorRequest;
    @Mock DB2 database;
    @Mock MailService mailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentTutored = new Student(1234,"Mario","Rossi",1,"mario.rossi@unifi.it",database,mailService);
        studentNullTutorOneRequest = new Student(5678,"Lorenzo","Neri",null,"lorenzo.neri@unifi.it",database,mailService);
        studentNullTutor = new Student(4321,"Luca","Bianchi",null,"luca.bianchi@unifi.it",database,mailService);
        teacher1 = new Teacher(1,"Michele","Loreti","michele.loreti@unifi.it",database,mailService);
        Course  course1 = new Course(1,"Reti","reti@unifi.it",teacher1.getID(),database,mailService);
        tutorRequest = new TutorRequest(studentNullTutorOneRequest.getID(),teacher1.getID());

        when(database.getTeacherByID(teacher1.getID())).thenReturn(teacher1);
        when(database.getCourseByID(1)).thenReturn(course1);
    }

    @Test(expected = Exception.class)
    public void tutorRequestTestAlreadyOneRequest() throws Exception {
        when(database.getTutorRequestByStudentID(studentNullTutorOneRequest.getID()))
                     .thenReturn(tutorRequest);
        tutorRequest.getIDStudent();
        studentNullTutorOneRequest.tutorRequest(2);
    }

    @Test(expected = Exception.class)
    public void tutorRequestTestAlreadyTutoredStudent() throws Exception {
        studentTutored.tutorRequest(2);
    }

    @Test
    public void tutorRequestTestBase() throws Exception {
        studentNullTutor.tutorRequest(1);
    }

    @Test(expected = Exception.class)
    public void courseRequestTestAlreadyOneRequest()throws Exception{
        when(database.getCourseRequestByStudentID(studentTutored.getID())).thenReturn(new CourseRequest(studentTutored.getID(),1));
        studentTutored.courseRequest(2);
    }

    @Test
    public void courseRequestTestBase() throws Exception {
        studentTutored.courseRequest(1);
    }

    @Test
    public void sendEmailToTeacherTestBase(){
        studentTutored.sendEmailToTutor("Salve, sono "+ studentTutored.getNome());
    }

}