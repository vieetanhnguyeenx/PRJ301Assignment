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
import model.Group;
import model.Subject;
import model.Term;

/**
 *
 * @author Admin
 */
public class GroupDAO {

    public ArrayList<Group> getGroupsForStudent(int stid, int tid) {
        ArrayList<Group> list = new ArrayList<>();
        try {
            String sql = "select\n"
                    + "g.gid, g.gname, g.startdate,\n"
                    + "t.termid,\n"
                    + "sub.subid, sub.subname, sub.subfullname\n"
                    + "from [Group] g \n"
                    + "join Student_Group sg on g.gid = sg.gid\n"
                    + "join Student s on sg.stdid = s.stdid\n"
                    + "join Term t on g.termid = t.termid\n"
                    + "join [Subject] sub on sub.subid = g.subid\n"
                    + "where s.stdid = ?\n"
                    + "and t.termid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stid);
            ps.setInt(2, tid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                g.setStartDate(rs.getDate("startdate").toLocalDate());
                
                Term t = new Term();
                t.setTearmId(rs.getInt("termid"));
                g.setTerm(t);
                
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                sub.setFullName(rs.getString("subfullname"));
                g.setSubject(sub);
                
                list.add(g);
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
        ArrayList<Group> list = new GroupDAO().getGroupsForStudent(1, 1);
        System.out.println(list.get(0).getId());
    }
}
