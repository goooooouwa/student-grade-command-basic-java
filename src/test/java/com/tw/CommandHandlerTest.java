package com.tw;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jxzhong on 2017/8/31.
 */
public class CommandHandlerTest {

    @Test
    public void should_return_add_student_print_msg_when_input_1() throws Exception {
        //Given
        CommandHandler commandHandler = new CommandHandler();
        //When
        String msg = commandHandler.execute("1");
        //Then
        assertThat(msg, is("请输入学生信息（格式：姓名, 学号，数学：分数，语文：分数，英语：分数，编程：分数），按回车提交："));
    }

    @Test
    public void should_return_add_stu_input_error_msg_when_input_1_and_then_wrong_stu_info() throws Exception {
        //Given
        CommandHandler commandHandler = new CommandHandler();
        //When
        commandHandler.execute("1");
        String msg = commandHandler.execute("anything wrong format input");
        //Then
        assertThat(msg, is("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交："));
    }

    @Test
    public void should_return_main_menus_print_msg_when_input_0() throws Exception {
        //Given
        CommandHandler commandHandler = new CommandHandler();
        //When
        String msg = commandHandler.execute("0");
        //Then
        assertThat(msg, is("***********\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3):\n" +
                "***********\n"));
    }

    @Test
    public void should_return_gen_score_sheet_print_msg_when_input_2() throws Exception {
        //Given
        CommandHandler commandHandler = new CommandHandler();
        //When
        String msg = commandHandler.execute("2");
        //Then
        assertThat(msg, is("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交："));
    }
}
