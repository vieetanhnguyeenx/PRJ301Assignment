/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;

/**
 *
 * @author Admin
 */
public class LecturerDAO {
    public ArrayList<Lecturer> getAllLec() {
        ArrayList<Lecturer> list = new ArrayList<>();
        try {
            String sql = "select lid, lname, [login], email  from Lecturer";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Lecturer l = new Lecturer();
                l.setId(rs.getInt(1));
                l.setName(rs.getString(2));
                l.setLogin(rs.getString(3));
                l.setEmail(rs.getString(4));
                list.add(l);
            }
        } catch (Exception ex) {
            Logger.getLogger(LecturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Lecturer getLecturerById(int id) {
        Lecturer l = new Lecturer();
        try {
            String sql = "select lid, lname, [login], email  from Lecturer where lid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                l.setId(rs.getInt(1));
                l.setName(rs.getString(2));
                l.setLogin(rs.getString(3));
                l.setEmail(rs.getString(4));
            }
        } catch (Exception ex) {
            Logger.getLogger(LecturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
}
