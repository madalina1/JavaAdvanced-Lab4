package com;

import com.db.DbInit;
import com.models.Course;
import com.models.Lecturer;
import com.models.OptionalPackage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;

@ManagedBean(name="optionalPackages")
@SessionScoped
public class OptionalPackages implements Serializable {
    private OptionalPackage[] allOptionalPackages = new OptionalPackage[]{};
    private DbInit dbInit = new DbInit();

    public OptionalPackages() throws SQLException, ClassNotFoundException {
    }

    public void setAllOptionalPackages(OptionalPackage[] allOptionalPackages){
        this.allOptionalPackages = allOptionalPackages;
    }

    public OptionalPackage[] getAllOptionalPackages() throws SQLException, ClassNotFoundException {
        return allOptionalPackages;
    }

    public String getAll() throws SQLException, ClassNotFoundException, IOException {
        int i = 0;
        OptionalPackage[] newOptionalPackages = new OptionalPackage[dbInit.countRows("lecturers")];

        ResultSet rs = dbInit.ExecuteSqlGet("SELECT * FROM lecturers");
        while ( rs.next()) {
            String packageNumber = rs.getString("packageNumber");
            String year = rs.getString("year");
            String semester = rs.getString("semester");
            newOptionalPackages[i] = new OptionalPackage(Integer.parseInt(packageNumber), Integer.parseInt(year), Integer.parseInt(semester));
            i++;
        }
        setAllOptionalPackages(newOptionalPackages);
        return rs.toString();
    }

    public String edit(OptionalPackage optionalPackage) throws SQLException, IOException {
        optionalPackage.setEditable(true);
        return null;
    }

    public String save() throws SQLException, ClassNotFoundException, IOException {
        for (OptionalPackage optionalPackage : getAllOptionalPackages()){
            if (optionalPackage != null){
                optionalPackage.setEditable(false);
                optionalPackage.edit();
            }
        }
        return null;
    }

    public void delete(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "cami");
        PreparedStatement pst = con.prepareStatement("DELETE FROM optionalpackage WHERE packagenumber = ?");
        pst.setString(1, name);
        pst.executeUpdate();
    }
}
