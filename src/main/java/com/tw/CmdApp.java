package com.tw;

import com.tw.cmdend.CmdEntry;
import com.tw.core.GradeReportBuilder;
import com.tw.core.Klass;
import com.tw.service.StudentGradeService;
import com.tw.transform.CmdIOTransformer;

import java.util.Scanner;

import static com.tw.cmdend.CmdStatusManager.MAIN_MENU_DISPLAY_COMMAND;

/**
 * Created by jxzhong on 2017/8/31.
 */
public class CmdApp {

    public static Scanner sc = new Scanner(System.in);
    private static final String CMD_APP_EXIT = "3";

    public static void main(String[] args) {
        try {
            CmdEntry cmdEntry = createCmdEntry();
            String inputToCmd = MAIN_MENU_DISPLAY_COMMAND;
            do {

                System.out.println(cmdEntry.execute(inputToCmd).getOutput());
                inputToCmd = sc.nextLine();
            }
            while (!inputToCmd.equals(CMD_APP_EXIT));
        } catch (Exception e) {
            System.out.println("\n App Exist");
        } finally {
            sc.close();
        }
    }

    private static CmdEntry createCmdEntry() {
        Klass klass = new Klass();
        GradeReportBuilder gradeReportBuilder = new GradeReportBuilder(klass);
        StudentGradeService studentGradeService = new StudentGradeService(klass, gradeReportBuilder);
        CmdIOTransformer inputTransformer = new CmdIOTransformer();
        return new CmdEntry(studentGradeService, inputTransformer);
    }
}
