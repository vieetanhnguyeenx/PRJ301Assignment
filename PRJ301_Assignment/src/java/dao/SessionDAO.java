/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import helper.DateTimeHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecturer;
import model.Room;

import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class SessionDAO {

    public ArrayList<Session> getFilteredSession(int lid, LocalDate from, LocalDate to) {
        ArrayList<Session> list = new ArrayList<>();
        try {
            String sql = "SELECT ses.sesid, ses.[date], ses.[index], ses.attanded, l.lid, l.lname, g.gid, g.gname, sub.subid, sub.subname\n"
                    + ",r.rid, r.rname, t.tid,t.[description]\n"
                    + "FROM [Session] ses\n"
                    + "INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "where l.lid = ?\n"
                    + "AND ses.[date] >= ?\n"
                    + "AND ses.[date] <= ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lid);
            Date fromSql = Date.valueOf(from);
            Date toSql = Date.valueOf(to);
            ps.setDate(2, fromSql);
            ps.setDate(3, toSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ok");
                Session session = new Session();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Subject sub = new Subject();
                
                session.setId(rs.getInt("sesid"));
                session.setDate(DateTimeHelper.toLocalDate(rs.getDate(2)));
                session.setIndex(rs.getInt("index"));
                session.setAttandated(rs.getBoolean("attanded"));
                
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                session.setLecturer(l);
                
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                session.setGroup(g);
                
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);
                
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                session.setRoom(r);
                
                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                session.setTimeslot(t);
                
                list.add(session);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        LocalDate from = LocalDate.parse("2022-10-01");
        LocalDate to = LocalDate.parse("2022-10-19");


        SessionDAO ss = new SessionDAO();
        ArrayList<Session> sl = ss.getFilteredSession(1, from, to);
        for (Session s : sl) {
            System.out.println(s.getId());
        }

    }
}
