package com.hanergy.reportForms.mapper.entity;

/**
 * SELECT
 logid,
 employeeID,
 empname,
 inputtime,
 leadercode,
 leader_empname,
 content,
 time
 FROM
 temp_input
 */

public class UrlData {
    private String logid;
    private String employeeID;
    private String empname;
    private String inputtime;
    private String leadercode;
    private String leader_empname;
    private String content;
    private String time;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getInputtime() {
        return inputtime;
    }

    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }

    public String getLeadercode() {
        return leadercode;
    }

    public void setLeadercode(String leadercode) {
        this.leadercode = leadercode;
    }

    public String getLeader_empname() {
        return leader_empname;
    }

    public void setLeader_empname(String leader_empname) {
        this.leader_empname = leader_empname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
