package com.models;

import com.LogHelper;
import com.db.DbInit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name="course")
@SessionScoped
public class Course {

    private String name;
    private boolean optional;
    private Integer packageNumber;
    private Integer credits;
    private boolean editable;
    LogHelper log = new LogHelper();

    public Course() throws SQLException, ClassNotFoundException, IOException {
    }

    private String lecturerName;
    private DbInit dbInit = new DbInit();

    public Course(String name, boolean optional, Integer packageNumber, Integer credits, String lecturerName) throws SQLException, ClassNotFoundException, IOException {
        this.name = name;
        this.optional = optional;
        this.packageNumber = packageNumber;
        this.credits = credits;
        this.lecturerName = lecturerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerList) {
        this.lecturerName = lecturerList;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void add() throws SQLException, IOException {
        dbInit.ExecuteSqlPost("insert into courses (name, isoptional, packagenumber, credits, lecturername) values (\'" + getName() + "\',\'" + getOptional() + "\'," + getPackageNumber() + "," + getCredits() + ",\'" + getLecturerName() +"\')");
    }

    public void edit() throws SQLException, IOException {
//        log.writeLine();
        dbInit.ExecuteSqlPost("update courses set isoptional = \'" + getOptional() + "\', packagenumber =\'" + getPackageNumber() + "\', credits =\'" + getCredits() + "\', lecturername =\'" + getLecturerName() +"\' where name=\'" + getName() + "\'");
    }

}
