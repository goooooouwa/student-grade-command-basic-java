package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.Gradereport;
import com.tw.core.Klass;
import com.tw.core.StudentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jxzhong on 2017/8/31.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentGradeServiceTest {

    @Mock
    private Klass klass;
    @Mock
    private GradeReportBuilder gradeReportBuilder;

    @Test
    public void shoud_add_student() throws Exception {
        //Given
        StudentInfo stu = new StudentInfo("Tom", "1", 90, 88, 98, 100);
        StudentGradeService studentGradeService = new StudentGradeService(this.klass, this.gradeReportBuilder);
        //When
        studentGradeService.addStudent(stu);
        //Then
        verify(this.klass, times(1)).addStudents(stu);
    }

    @Test
    public void shoud_generate_report() throws Exception {
        //Given
        StudentInfo stu = new StudentInfo("Tom", "1", 90, 88, 98, 100);
        StudentGradeService studentGradeService = new StudentGradeService(this.klass, this.gradeReportBuilder);
        //When
        List<StudentInfo> stuList = asList(stu);
        Gradereport report = studentGradeService.generateReport(stuList);
        //Then
        verify(this.gradeReportBuilder,times(1)).buildIndicatedStuReport(stuList);

    }
}
