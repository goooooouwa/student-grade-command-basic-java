package com.tw.core;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2017/8/31.
 */
public class GradeReportBuilder {
    private final Klass klass;

    public GradeReportBuilder(Klass klass) {
        this.klass = klass;
    }

    public Gradereport buildIndicatedStuReport(List<StudentInfo> stuLIst) {
        Gradereport gradereport = new Gradereport();
        gradereport.setStudentGradeItems(this.klass.getAllStudent().stream()
                .filter(s -> stuLIst.stream().anyMatch(indicatedStu -> indicatedStu.getNumber().equals(s.getNumber())))
                .map(s -> new StudentGradeItem(
                        s.getName(),
                        s.getNumber(),
                        s.getMathsScore(),
                        s.getChineseScore(),
                        s.getEnglishScore(),
                        s.getProgramScore())).collect(Collectors.toList()));
        return gradereport;
    }
}
