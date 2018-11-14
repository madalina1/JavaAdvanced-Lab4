package com;

import com.db.DbInit;
import com.models.Course;
import com.models.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;

@ManagedBean(name="courses")
@SessionScoped
public class Courses implements Serializable {
    private Course[] allCourses = new Course[]{};
    private DbInit dbInit = new DbInit();

    public Courses() throws SQLException, ClassNotFoundException {
    }

    public void setAllCourses(Course[] lecturers){
        this.allCourses = lecturers;
    }

    public Course[] getAllCourses() throws SQLException, ClassNotFoundException {
        return allCourses;
    }

    public String getAll() throws SQLException, ClassNotFoundException, IOException {
        int i = 0;
        Course[] newCourses = new Course[dbInit.countRows("courses")];

        ResultSet rs = dbInit.ExecuteSqlGet("SELECT * FROM courses");
        while ( rs.next()) {
            String name = rs.getString("name");
            String isoptional = rs.getString("isoptional");
            String packagenumber = rs.getString("packagenumber");
            String credits = rs.getString("credits");
            String lecturername = rs.getString("lecturername");
            newCourses[i] = new Course(name, Boolean.parseBoolean(isoptional), Integer.parseInt(packagenumber), Integer.parseInt(credits), lecturername);
            i++;
        }
        setAllCourses(newCourses);
        return rs.toString();
    }

    public String edit(Course course) throws SQLException, IOException {
        course.setEditable(true);
        return null;
    }

    public String save() throws SQLException, ClassNotFoundException, IOException {
        for (Course course : getAllCourses()){
            if (course != null){
                course.setEditable(false);
                course.edit();
            }
        }
        return null;
    }

    public void delete(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "cami");
        PreparedStatement pst = con.prepareStatement("DELETE FROM courses WHERE name = ?");
        pst.setString(1, name);
        pst.executeUpdate();
    }
}

