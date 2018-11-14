package com.models;

import com.LogHelper;
import com.db.DbInit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

@ManagedBean(name="lecturer")
@SessionScoped
public class Lecturer implements Serializable {
    private String name;
    private String courseName;
    private String email;
    LogHelper log = new LogHelper();

    boolean editable;

    private DbInit dbInit = new DbInit();

    public Lecturer() throws SQLException, ClassNotFoundException, IOException {
    }

    public Lecturer(String name, String courseName, String email) throws SQLException, ClassNotFoundException, IOException {
        this.name = name;
        this.courseName = courseName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return courseName;
    }

    public void setCourse(String course) {
        this.courseName = course;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void add() throws SQLException {
        dbInit.ExecuteSqlPost("insert into lecturers (name, coursename, email) values (\'" + getName() + "\',\'" + getCourse() +"\',\'" + getEmail() +"\')");
    }

    public void edit() throws SQLException, IOException {
//        log.writeLine();
        dbInit.ExecuteSqlPost("update lecturers set coursename = \'" + getCourse() + ", email = \'" + getEmail() +"\' where name=\'" + getName() + "\'");
    }

}
