package com.tw.cmdend;

/**
 * Created by jxzhong on 2017/9/7.
 */
public class CmdParam {
    private String output;
    private String status;

    public void setOutput(String msg) {
        this.output = msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getOutput() {
        return output;
    }
}
