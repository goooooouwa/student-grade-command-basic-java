package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.Gradereport;
import com.tw.core.Klass;
import com.tw.core.StudentInfo;

import java.util.List;

/**
 * Created by jxzhong on 2017/8/31.
 */
public class StudentGradeService {

    private Klass klass;
    private GradeReportBuilder gradeReportBuilder;

    public StudentGradeService(Klass klass, GradeReportBuilder gradeReportBuilder) {

        this.klass = klass;
        this.gradeReportBuilder = gradeReportBuilder;
    }

    public void addStudent(StudentInfo stu) {
        this.klass.addStudents(stu);
    }

    public Gradereport generateReport(List<StudentInfo> stuList) {
        return this.gradeReportBuilder.buildIndicatedStuReport(stuList);
    }
}
