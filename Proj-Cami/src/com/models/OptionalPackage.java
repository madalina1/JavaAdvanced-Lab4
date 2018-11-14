package com.models;

import com.db.DbInit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

@ManagedBean(name="optionalPackage")
@SessionScoped
public class OptionalPackage implements Serializable {
    private int packageNumber;
    private int year;
    private int semester;
    private boolean editable;
    private DbInit dbInit = new DbInit();

    public OptionalPackage() throws SQLException, ClassNotFoundException {
    }

    public OptionalPackage(int packageNumber, int year, int semester) throws SQLException, ClassNotFoundException {
        this.packageNumber = packageNumber;
        this.year = year;
        this.semester = semester;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void add() throws SQLException, IOException {
        dbInit.ExecuteSqlPost("insert into optionalpackage (packagenumber, year, semester) values (" + getPackageNumber() + "," + getYear() + "," + getSemester() +")");
    }

    public void edit() throws SQLException, IOException {
//        log.writeLine();
        dbInit.ExecuteSqlPost("update optionalpackage set year =\'" + getPackageNumber() + "\', semester =\'" + getSemester() + " where packagenumber=" + getPackageNumber() + "");
    }
}
