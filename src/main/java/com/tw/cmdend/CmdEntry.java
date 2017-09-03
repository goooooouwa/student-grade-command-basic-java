package com.tw.cmdend;

import com.tw.core.Gradereport;
import com.tw.core.StudentInfo;
import com.tw.service.StudentGradeService;
import com.tw.transform.InputTransformer;

import java.util.List;

import static com.tw.cmdend.CmdMessageConstants.*;
import static com.tw.cmdend.CmdStatusManager.*;

/**
 * Created by jxzhong on 2017/8/31.
 */
public class CmdEntry {

    private final InputTransformer inputTransformer;
    private StudentGradeService studentGradeService;
    private final CmdStatusManager cmdStatusManager;

    public CmdEntry(StudentGradeService studentGradeService, InputTransformer inputTransformer) {
        this.studentGradeService = studentGradeService;
        this.cmdStatusManager = new CmdStatusManager();
        this.inputTransformer = inputTransformer;
    }

    public String execute(String input) {

        String printMsg = "";
        String nextStatus = this.cmdStatusManager.getNextStatus(input);
        switch (nextStatus) {
            case MAIN_MENU_STATUS:
                printMsg = MAIN_MENU_MSG;
                break;
            case ADD_STUDENT_STATUS:
                printMsg = handleStudentAddStatus(input, nextStatus);
                break;
            case PRINT_REPORT_STATUS:
                printMsg = handlePrintReportStatus(input, nextStatus);
                break;
        }

        this.cmdStatusManager.setCurrentStatue(nextStatus);
        return printMsg;

    }

    private String handlePrintReportStatus(String input, String nextStatus) {
        String printMsg;
        if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {
            printMsg = PRINT_REPORT_MSG;
        } else {
            printMsg = handleGradeReport(input);
        }
        return printMsg;
    }

    private String handleStudentAddStatus(String input, String nextStatus) {
        String printMsg;
        if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {
            printMsg = ADD_STUDENT_INFO_MSG;
            this.cmdStatusManager.setCurrentStatue(nextStatus);
        } else {
            printMsg = handleSudentAdding(input);
        }
        return printMsg;
    }

    private String handleGradeReport(String input) {
        String printMsg;
        List<StudentInfo> studentInfos = this.inputTransformer.formatStudentNos(input);
        if (studentInfos.isEmpty()) {
            printMsg = STUDENG_ADD_ERROR_MSG;
        } else {
            Gradereport gradereport = studentGradeService.generateReport(studentInfos);
            printMsg = this.inputTransformer.formatReportText(gradereport);
            printMsg += MAIN_MENU_MSG;
        }
        return printMsg;
    }

    private String handleSudentAdding(String input) {
        String printMsg;
        StudentInfo studentInfo = this.inputTransformer.formatStudentInfo(input);
        if (studentInfo == null) {
            printMsg = ADD_STUDENT_ERROR_MSG;
        } else {
            studentGradeService.addStudent(studentInfo);
            printMsg = MAIN_MENU_MSG;

        }
        return printMsg;
    }

}

