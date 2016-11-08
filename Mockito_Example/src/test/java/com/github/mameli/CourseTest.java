package com.github.mameli;

import com.github.mameli.Bean.Course;
import com.github.mameli.Bean.Student;
import com.github.mameli.Bean.Teacher;
import com.github.mameli.Interface.DB2;
import com.github.mameli.Interface.MailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by mameli on 22/10/2016.
 * Test per Course
 */
public class CourseTest {

    private Teacher teacher1;
    private Teacher teacher2;
    private Course course1;
    private Student student1;
    @Mock DB2 database;
    @Mock MailService mailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        teacher1 = new Teacher(1,"Michele","Loreti","michele.loreti@unifi.it",database,mailService);
        teacher2 = new Teacher(2,"Cecilia","Verri","cecilia.verri@unifi.it",database,mailService);
        student1 = new Student(1234,"Mario","Rossi",1,"mario.rossi@unifi.it",database,mailService);
        course1 = new Course(1,"Reti","reti@unifi.it",teacher1.getID(),database,mailService);
        when(database.getCourseByID(1)).thenReturn(course1);
    }

    @Test
    public void assignTeacherToCourseTestBase(){
        when(database.getTeacherByID(teacher1.getID())).thenReturn(teacher1);
        when(database.getTeacherByID(teacher2.getID())).thenReturn(teacher2);
        course1.assignTeacherToCourse(2);
    }

    @Test
    public void courseRequestTestBase(){
        when(database.getStudentByID(student1.getID())).thenReturn(student1);
        course1.courseRequest(student1.getID());
    }

}