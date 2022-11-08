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
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attandance;
import model.Group;
import model.Lecturer;
import model.Room;

import model.Session;
import model.Student;
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

    public ArrayList<Session> getSessionByStudentId(int stid, LocalDate from, LocalDate to) {
        ArrayList<Session> sesList = new ArrayList<>();
        try {
            String sql = "select ses.sesid, ses.[date], ses.[index], ses.attanded,\n"
                    + "g.gid, g.gname, g.subid, sub.subname,\n"
                    + "r.rid, r.rname,\n"
                    + "l.lid, l.lname,\n"
                    + "ts.tid, ts.[description], ts.tname,\n"
                    + "s.stdid,\n"
                    + "a.present, a.record_time\n"
                    + "from Session ses\n"
                    + "join [Group] g on g.gid = ses.gid\n"
                    + "join Student_Group sg on sg.gid = g.gid\n"
                    + "join Student s on sg.stdid = s.stdid\n"
                    + "join Lecturer l on ses.lid = l.lid\n"
                    + "join [Subject] sub on sub.subid = g.subid\n"
                    + "join Room r on r.rid = ses.rid\n"
                    + "join TimeSlot ts on ts.tid = ses.tid\n"
                    + "left join Attandance a on a.stdid = s.stdid and ses.sesid = a.sesid\n"
                    + "where s.stdid = ?\n"
                    + "and ses.[date] >= ?\n"
                    + "and ses.[date] <= ?\n";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stid);
            Date fromSql = Date.valueOf(from);
            Date toSql = Date.valueOf(to);
            ps.setDate(2, fromSql);
            ps.setDate(3, toSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Subject sub = new Subject();
                Attandance a = new Attandance();
                Student s = new Student();

//                session.setId(rs.getInt("sesid"));
//                session.setDate(rs.getDate("date").toLocalDate());
//                session.setIndex(rs.getInt("index"));
//                session.setAttandated(rs.getBoolean("attanded"));
//
//                g.setId(rs.getInt("gid"));
//                g.setName(rs.getString("gname"));
//                session.setGroup(g);
//
//                sub.setId(rs.getInt("subid"));
//                sub.setName(rs.getString("subname"));
//                g.setSubject(sub);
//
//                r.setId(rs.getInt("rid"));
//                r.setName(rs.getString("rname"));
//                session.setRoom(r);
//
//                l.setId(rs.getInt("lid"));
//                l.setName(rs.getString("lname"));
//                session.setLecturer(l);
//
//                t.setId(rs.getInt("tid"));
//                t.setDescription(rs.getString("description"));
//                t.setName(rs.getString("tname"));
//                session.setTimeslot(t);
//
//                s.setId(rs.getInt("stdid"));
//                a.setStudent(s);
//                a.setPresent(rs.getBoolean("present"));
//                Timestamp ts = rs.getTimestamp("record_time");
//                a.setRecordTime(ts.toLocalDateTime());
//
//                session.setAtten(a);
//
//                sesList.add(session);
                session.setId(rs.getInt(1));
                session.setDate(rs.getDate(2).toLocalDate());
                session.setIndex(rs.getInt(3));
                session.setAttandated(rs.getBoolean(4));

                g.setId(rs.getInt(5));
                g.setName(rs.getString(6));
                session.setGroup(g);

                sub.setId(rs.getInt(7));
                sub.setName(rs.getString(8));
                g.setSubject(sub);

                r.setId(rs.getInt(9));
                r.setName(rs.getString(10));
                session.setRoom(r);

                l.setId(rs.getInt(11));
                l.setName(rs.getString(12));
                session.setLecturer(l);

                t.setId(rs.getInt(13));
                t.setDescription(rs.getString(14));
                t.setName(rs.getString(15));
                session.setTimeslot(t);

                s.setId(rs.getInt(16));
                a.setStudent(s);
                a.setPresent(rs.getBoolean(17));
                if (rs.getTimestamp(18) == null) {
                    a.setRecordTime(null);
                } else {
                    Timestamp ts = rs.getTimestamp(18);
                    a.setRecordTime(ts.toLocalDateTime());
                }

                session.setAtten(a);

                sesList.add(session);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesList;
    }

    public Session getSessionForAttending(int id) {
        try {
            String sql = "SELECT ses.sesid,ses.[index],ses.date,ses.attanded\n"
                    + ",g.gid,g.gname\n"
                    + ",r.rid,r.rname\n"
                    + ",t.tid,t.[description] tdescription, t.tname\n"
                    + ",l.lid,l.lname\n"
                    + ",sub.subid,sub.subname\n"
                    + ",s.stdid,s.stdname, s.imgUrl\n"
                    + ",ISNULL(a.present,0) present, ISNULL(a.[description],'') [description]\n"
                    + "FROM [Session] ses\n"
                    + "INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "INNER JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "INNER JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "WHERE ses.sesid = ?";

            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Session ses = null;
            while (rs.next()) {
                if (ses == null) {
                    ses = new Session();
                    Room r = new Room();
                    r.setId(rs.getInt("rid"));
                    r.setName(rs.getString("rname"));
                    ses.setRoom(r);

                    TimeSlot t = new TimeSlot();
                    t.setId(rs.getInt("tid"));
                    t.setDescription(rs.getString("tdescription"));
                    t.setName(rs.getString("tname"));
                    ses.setTimeslot(t);

                    Lecturer l = new Lecturer();
                    l.setId(rs.getInt("lid"));
                    l.setName(rs.getString("lname"));
                    ses.setLecturer(l);

                    Group g = new Group();
                    g.setId(rs.getInt("gid"));
                    g.setName(rs.getString("gname"));
                    ses.setGroup(g);

                    Subject sub = new Subject();
                    sub.setId(rs.getInt("subid"));
                    sub.setName(rs.getString("subname"));
                    g.setSubject(sub);

                    ses.setId(rs.getInt("sesid"));
                    ses.setDate(rs.getDate("date").toLocalDate());
                    ses.setIndex(rs.getInt("index"));
                    ses.setAttandated(rs.getBoolean("attanded"));
                }
                Student s = new Student();
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));
                s.setImgUrl(rs.getString("imgUrl"));

                Attandance a = new Attandance();
                a.setStudent(s);
                a.setSession(ses);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                ses.getAttandances().add(a);
            }
            return ses;
        } catch (Exception ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateAttendent(Session model) {
        try {
            String sql = "UPDATE [Session] SET attanded = 1 WHERE sesid = ?";
            Connection conn = new DBContext().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, model.getId());
            ps.executeUpdate();

            sql = "DELETE Attandance WHERE sesid = ?";
            PreparedStatement psDelete = conn.prepareStatement(sql);
            psDelete.setInt(1, model.getId());
            psDelete.executeUpdate();

            for (Attandance att : model.getAttandances()) {
                sql = "INSERT INTO [Attandance]\n"
                        + "           ([sesid]\n"
                        + "           ,[stdid]\n"
                        + "           ,[present]\n"
                        + "           ,[description]\n"
                        + "           ,[record_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement psInsert = conn.prepareStatement(sql);
                psInsert.setInt(1, model.getId());
                psInsert.setInt(2, att.getStudent().getId());
                psInsert.setBoolean(3, att.isPresent());
                psInsert.setString(4, att.getDescription());
                psInsert.executeUpdate();
            }
            conn.commit();

        } catch (Exception ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Session> getAllSessionDate(int gid, int termid, int lid) {
        ArrayList<Session> list = new ArrayList<>();
        try {
            String sql = "select s.sesid, s.[date], s.[index]\n"
                    + "from [Group] g join [Session] s on g.gid = s.gid\n"
                    + "where g.gid = ? \n"
                    + "and g.termid = ?\n"
                    + "and g.lid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, gid);
            ps.setInt(2, termid);
            ps.setInt(3, lid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));
                ses.setDate(rs.getDate("date").toLocalDate());
                ses.setIndex(rs.getInt("index"));
                list.add(ses);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Session> list = new SessionDAO().getAllSessionDate(1, 1, 1);
        for (Session s : list) {
            System.out.println(s.getDate());
        }
        

    }
}
