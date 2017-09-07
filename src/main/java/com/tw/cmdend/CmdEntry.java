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

    public CmdParam execute(String input) {

        CmdParam param = new CmdParam();
        String nextStatus = this.cmdStatusManager.getNextStatus(input);
        switch (nextStatus) {
            case MAIN_MENU_STATUS:
                param = handleMainMenuPage(nextStatus);
                break;
            case ADD_STUDENT_STATUS:
                param = handleStudentAddPage(input, nextStatus);
                break;
            case PRINT_REPORT_STATUS:
                param = handlePrintReportPage(input, nextStatus);
                break;
        }

        this.cmdStatusManager.setCurrentStatue(param.getStatus());
        return param;
    }

    private CmdParam handleMainMenuPage(String nextStatus) {
        if (cmdStatusManager.isTheSameStatus(nextStatus)) {
            return handlePageDisplay(MAIN_MENU_STATUS, "");
        } else {
            return handlePageDisplay(MAIN_MENU_STATUS, MAIN_MENU_MSG);
        }

    }

    private CmdParam handlePrintReportPage(String input, String nextStatus) {
        CmdParam cmdParam;
        if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {

            cmdParam = handleGradeReport(input, nextStatus);
        } else {
            cmdParam = handlePageDisplay(nextStatus, PRINT_REPORT_MSG);
        }
        return cmdParam;
    }

    private CmdParam handleStudentAddPage(String input, String nextStatus) {
        CmdParam cmdParam;
        if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {

            cmdParam = handleSudentAdding(input, nextStatus);
        } else {
            cmdParam = handlePageDisplay(nextStatus, ADD_STUDENT_INFO_MSG);
        }
        return cmdParam;
    }

    private CmdParam handlePageDisplay(String nextStatus, String addStudentInfoMsg) {
        CmdParam cmdParam = new CmdParam();
        cmdParam.setOutput(addStudentInfoMsg);
        cmdParam.setStatus(nextStatus);
        return cmdParam;
    }

    private CmdParam handleGradeReport(String input, String nextStatus) {
        CmdParam cmdParam;
        List<StudentInfo> studentInfos = this.inputTransformer.formatStudentNos(input);
        if (studentInfos.isEmpty()) {
            cmdParam = handlePageDisplay(nextStatus, STUDENG_ADD_ERROR_MSG);
        } else {
            Gradereport gradereport = studentGradeService.generateReport(studentInfos);
            String displayMsg = this.inputTransformer.formatReportText(gradereport) + MAIN_MENU_MSG;
            cmdParam = handlePageDisplay(MAIN_MENU_STATUS, displayMsg);
        }
        return cmdParam;
    }

    private CmdParam handleSudentAdding(String input, String nextStatus) {
        CmdParam cmdParam;
        StudentInfo studentInfo = this.inputTransformer.formatStudentInfo(input);
        if (studentInfo == null) {
            cmdParam = handlePageDisplay(nextStatus, ADD_STUDENT_ERROR_MSG);
        } else {
            studentGradeService.addStudent(studentInfo);
            cmdParam = handlePageDisplay(nextStatus, MAIN_MENU_MSG);


        }
        return cmdParam;
    }

}

