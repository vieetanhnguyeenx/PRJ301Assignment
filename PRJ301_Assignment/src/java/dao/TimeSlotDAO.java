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
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class TimeSlotDAO {

    public ArrayList<TimeSlot> getAllTimeSlot() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        try {
            String sql = "select tid, [description] from TimeSlot";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                TimeSlot ts = new TimeSlot();
                ts.setId(rs.getInt(1));
                ts.setDescription(rs.getString(2));
                list.add(ts);
            }
        } catch (Exception ex) {
            Logger.getLogger(TimeSlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
}
