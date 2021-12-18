package com.miniproject.miniproject;

public class StudentInfo {

    //Class containing object framework for Students.

    private String univRollNo;
    private String name;
    private String fname;
    private String smobile;
    private String fmobile;
    private String sec;
    private String roll;
    private String year;
    private String maths;
    private String english;
    private String cse;
    private String physics;
    private String chemistry;
    private String electronics;

    public StudentInfo(String univRollNo, String year, String roll, String sec, String fname, String name, String smobile, String fmobile, String maths, String english, String cse, String chemistry, String physics, String electronics, String electrical) {

        //Parametric Constructor 1.

        this.univRollNo = univRollNo;
        this.name = name;
        this.fname = fname;
        this.smobile = smobile;
        this.fmobile = fmobile;
        this.sec = sec;
        this.roll = roll;
        this.year = year;
        this.maths = maths;
        this.english = english;
        this.cse = cse;
        this.physics = physics;
        this.chemistry = chemistry;
        this.electronics = electronics;
        this.electrical = electrical;
    }

    private String electrical;

    public StudentInfo(){} //Default Constructor.

    public StudentInfo(String univRollNo, String name, String sec, String year, String roll) {

        //Parametric Constructor 2.

        this.univRollNo = univRollNo;
        this.name = name;
        this.sec = sec;
        this.roll = roll;
        this.year = year;
    }

    //Getters And Setters for every student related value.

    public String getUnivRollNo() {
        return univRollNo;
    }

    public void setUnivRollNo(String univRollNo) {
        this.univRollNo = univRollNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getFmobile() {
        return fmobile;
    }

    public void setFmobile(String fmobile) {
        this.fmobile = fmobile;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getMaths() {
        return maths;
    }

    public void setMaths(String maths) {
        this.maths = maths;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getCse() {
        return cse;
    }

    public void setCse(String cse) {
        this.cse = cse;
    }

    public String getPhysics() {
        return physics;
    }

    public void setPhysics(String physics) {
        this.physics = physics;
    }

    public String getChemistry() {
        return chemistry;
    }

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    public String getElectronics() {
        return electronics;
    }

    public void setElectronics(String electronics) {
        this.electronics = electronics;
    }

    public String getElectrical() {
        return electrical;
    }

    public void setElectrical(String electrical) {
        this.electrical = electrical;
    }

}
