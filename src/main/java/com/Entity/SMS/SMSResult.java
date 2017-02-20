package com.Entity.SMS;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Donnie on 2017/2/20.
 */
@XmlRootElement(name = "returnsms")
public class SMSResult {
    private String returnstatus;
    private String message;
    private int remainpoint;
    private long taskID;
    private int successCounts;

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRemainpoint() {
        return remainpoint;
    }

    public void setRemainpoint(int remainpoint) {
        this.remainpoint = remainpoint;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public int getSuccessCounts() {
        return successCounts;
    }

    public void setSuccessCounts(int successCounts) {
        this.successCounts = successCounts;
    }
}
