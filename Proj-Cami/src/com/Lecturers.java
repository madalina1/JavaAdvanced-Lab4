package com;

import com.db.DbInit;
import com.models.Course;
import com.models.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@ManagedBean(name="lecturers")
@SessionScoped
public class Lecturers implements Serializable {
    private Lecturer[] allLecturers = new Lecturer[]{};
    private DbInit dbInit = new DbInit();

    public Lecturers() throws SQLException, ClassNotFoundException {
    }

    public void setAllLecturers(Lecturer[] lecturers){
        this.allLecturers = lecturers;
    }

    public Lecturer[] getAllLecturers() throws SQLException, ClassNotFoundException {
        return allLecturers;
    }

    public String getAll() throws SQLException, ClassNotFoundException, IOException {
        int i = 0;
        Lecturer[] newLectures = new Lecturer[dbInit.countRows("lecturers")];

        ResultSet rs = dbInit.ExecuteSqlGet("SELECT * FROM lecturers");
        while ( rs.next()) {
            String name = rs.getString("name");
            String courseName = rs.getString("coursename");
            String email = rs.getString("email");
            newLectures[i] = new Lecturer(name, courseName, email);
            i++;
        }
        setAllLecturers(newLectures);
        return rs.toString();
    }

    public String edit(Lecturer lecturer) throws SQLException, IOException {
        lecturer.setEditable(true);
        return null;
    }

    public String save() throws SQLException, ClassNotFoundException, IOException {
        for (Lecturer lecturer : getAllLecturers()){
            if (lecturer != null){
                lecturer.setEditable(false);
                lecturer.edit();
            }
        }
        return null;
    }

    public void delete(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "cami");
        PreparedStatement pst = con.prepareStatement("DELETE FROM lecturers WHERE name = ?");
        pst.setString(1, name);
        pst.executeUpdate();
    }
}

