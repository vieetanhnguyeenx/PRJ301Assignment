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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attandance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Semester;
import model.Session;
import model.Student;
import model.Subject;
import model.Term;
import model.TimeSlot;
import model.Year;

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

    public Student getAttandance(int id, int termId, int gid) {
        try {
            String sql = "select s.stdid, s.stdname, s.[login],\n"
                    + "ses.sesid, ses.[date], ses.[index], ses.attanded,\n"
                    + "l.lid, l.lname,\n"
                    + "r.rid, r.rname,\n"
                    + "ts.tid, ts.tname, ts.[description],\n"
                    + "g.gid, g.gname, g.startdate,\n"
                    + "sub.subid, sub.subname,\n"
                    + "l.lid, l.lname,\n"
                    + "t.termid, \n"
                    + "sem.semid, sem.semname, sem.shortname,\n"
                    + "y.yid, y.yname,\n"
                    + "a.present, a.[description] as adescription, a.record_time\n"
                    + "from [Session] ses join [Group] g on ses.gid = g.gid\n"
                    + "join Student_Group sg on sg.gid = g.gid\n"
                    + "join Student s on s.stdid = sg.stdid\n"
                    + "join Term t on g.termid = t.termid\n"
                    + "join Semester sem on sem.semid = t.semid\n"
                    + "join [Year] y on y.yid = t.yid\n"
                    + "join Lecturer l on l.lid = g.lid\n"
                    + "join [Subject] sub on sub.subid = g.subid\n"
                    + "join Room r on r.rid = ses.rid\n"
                    + "join TimeSlot ts on ts.tid = ses.tid\n"
                    + "left join Attandance a on a.sesid = ses.sesid and a.stdid = s.stdid\n"
                    + "where s.stdid = ? \n"
                    + "and g.gid = ? \n"
                    + "and t.termid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, gid);
            ps.setInt(3, termId);
            ResultSet rs = ps.executeQuery();
            Student s = null;
            while (rs.next()) {
                if (s == null) {
                    s = new Student();
                    s.setId(rs.getInt("stdid"));
                    s.setName(rs.getString("stdname"));
                    s.setLogin(rs.getString("login"));
                }
                Attandance a = new Attandance();

                // set session
                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));
                ses.setIndex(rs.getInt("index"));
                ses.setDate(rs.getDate("date").toLocalDate());
                ses.setAttandated(rs.getBoolean("attanded"));

                //set lecture to session
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                ses.setLecturer(l);

                //set room to session
                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot ts = new TimeSlot();
                ts.setId(rs.getInt("tid"));
                ts.setName(rs.getString("tname"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);
                // set term to group 
                Term t = new Term();
                t.setTearmId(rs.getInt("termid"));
                Semester sem = new Semester();
                sem.setSemId(rs.getInt("semid"));
                sem.setSemesterName(rs.getString("semname"));
                sem.setShortName(rs.getString("shortname"));
                t.setSemester(sem);
                Year y = new Year();
                y.setYid(rs.getInt("yid"));
                y.setYearName(rs.getString("yname"));
                t.setYear(y);
                g.setTerm(t);
                ses.setGroup(g);
                Attandance a1 = new Attandance();

                a.setSession(ses);
                a.setPresent(rs.getBoolean("present"));
                a1.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("adescription"));
                a1.setDescription(rs.getString("description"));
                Timestamp time = rs.getTimestamp("record_time");
                if (time == null) {
                    a.setRecordTime(null);
                    a1.setRecordTime(null);
                } else {
                    a.setRecordTime(time.toLocalDateTime());
                    a1.setRecordTime(time.toLocalDateTime());
                }
                ses.setAtten(a1);
                // set ses to student
                s.getAttandances().add(a);
            }
            return s;
        } catch (Exception ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Student> getAllAttendent(int lid, int gid, int tid) {
        ArrayList<Student>  list = new ArrayList<>();
        try {
            String sql = "select s.stdid, s.stdname, s.[login],\n"
                    + "ses.sesid, ses.[date], ses.[index], ses.attanded,\n"
                    + "l.lid, l.lname,\n"
                    + "r.rid, r.rname,\n"
                    + "ts.tid, ts.tname, ts.[description],\n"
                    + "g.gid, g.gname, g.startdate,\n"
                    + "sub.subid, sub.subname,\n"
                    + "l.lid, l.lname,\n"
                    + "t.termid, \n"
                    + "sem.semid, sem.semname, sem.shortname,\n"
                    + "y.yid, y.yname,\n"
                    + "a.present, a.[description] as adescription, a.record_time\n"
                    + "from [Session] ses join [Group] g on ses.gid = g.gid\n"
                    + "join Student_Group sg on sg.gid = g.gid\n"
                    + "join Student s on s.stdid = sg.stdid\n"
                    + "join Term t on g.termid = t.termid\n"
                    + "join Semester sem on sem.semid = t.semid\n"
                    + "join [Year] y on y.yid = t.yid\n"
                    + "join Lecturer l on l.lid = g.lid\n"
                    + "join [Subject] sub on sub.subid = g.subid\n"
                    + "join Room r on r.rid = ses.rid\n"
                    + "join TimeSlot ts on ts.tid = ses.tid\n"
                    + "left join Attandance a on a.sesid = ses.sesid and a.stdid = s.stdid\n"
                    + "where l.lid = ? \n"
                    + "and g.gid = ? \n"
                    + "and t.termid = ?\n"
                    + "order by s.stdid, sesid";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lid);
            ps.setInt(2, gid);
            ps.setInt(3, tid);
            ResultSet rs  = ps.executeQuery();
            Student s = null;
            while (rs.next()) {                
                if (s == null ) {
                    s = new Student();
                    s.setId(rs.getInt("stdid"));
                    s.setName(rs.getString("stdname"));
                } else if (s.getId() != rs.getInt("stdid")) {
                    list.add(s);
                    s = new Student();
                    s.setId(rs.getInt("stdid"));
                    s.setName(rs.getString("stdname"));
                }
                Attandance a = new Attandance();

                // set session
                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));
                ses.setIndex(rs.getInt("index"));
                ses.setDate(rs.getDate("date").toLocalDate());
                ses.setAttandated(rs.getBoolean("attanded"));

                //set lecture to session
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                ses.setLecturer(l);

                //set room to session
                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot ts = new TimeSlot();
                ts.setId(rs.getInt("tid"));
                ts.setName(rs.getString("tname"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);
                // set term to group 
                Term t = new Term();
                t.setTearmId(rs.getInt("termid"));
                Semester sem = new Semester();
                sem.setSemId(rs.getInt("semid"));
                sem.setSemesterName(rs.getString("semname"));
                sem.setShortName(rs.getString("shortname"));
                t.setSemester(sem);
                Year y = new Year();
                y.setYid(rs.getInt("yid"));
                y.setYearName(rs.getString("yname"));
                t.setYear(y);
                g.setTerm(t);
                ses.setGroup(g);
                Attandance a1 = new Attandance();

                a.setSession(ses);
                a.setPresent(rs.getBoolean("present"));
                a1.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("adescription"));
                a1.setDescription(rs.getString("description"));
                Timestamp time = rs.getTimestamp("record_time");
                if (time == null) {
                    a.setRecordTime(null);
                    a1.setRecordTime(null);
                } else {
                    a.setRecordTime(time.toLocalDateTime());
                    a1.setRecordTime(time.toLocalDateTime());
                }
                ses.setAtten(a1);
                // set ses to student
                s.getAttandances().add(a);
            }
            list.add(s);
        } catch (Exception ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Student getAllCourse(int stdId, int termId) {
        try {
            String sql = "select s.stdid, s.stdname,\n"
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
            ps.setInt(1, stdId);
            ps.setInt(2, termId);
            ResultSet rs = ps.executeQuery();
            Student s = null;

            while (rs.next()) {
                if (s == null) {
                    s = new Student();
                    s.setId(rs.getInt("stdid"));
                    s.setName(rs.getString("stdname"));
                }
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

                s.getGroups().add(g);
            }
            return s;
        } catch (Exception ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Student> list = new StudentDAO().getAllAttendent(1, 1, 1);
        for (Student s : list) {
            System.out.println(s.getId() + "\t" + s.getName());
            for (Attandance a : s.getAttandances()) {
                System.out.println(a.toString());
            }
        }
        
    }

}
