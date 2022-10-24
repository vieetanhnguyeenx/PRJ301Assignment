/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import helper.DateTimeHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentDAO {
    public Student getStudentById(int id) {
        Student s = new Student();
        try {
            String sql = "select stdid, stdname, [login], imgUrl, email, phone, gender, dob from Student where stdid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setLogin(rs.getString(3));
                s.setImgUrl(rs.getString(4));
                s.setEmail(rs.getString(5));
                s.setPhone(rs.getString(6));
                s.setGender(rs.getBoolean(7));
                s.setDob(DateTimeHelper.toLocalDate(rs.getDate(8)));
            }
        } catch (Exception ex) {
            Logger.getLogger(LecturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static void main(String[] args) {
        Student s = new StudentDAO().getStudentById(1);
        System.out.println(s.toString());
    }
    
}
