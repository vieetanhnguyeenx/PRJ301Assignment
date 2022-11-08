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
import model.Lecturer;
import model.Semester;
import model.Subject;
import model.Term;
import model.Year;

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

    public ArrayList<Group> getAllGroupForLecture(int lid, int tid) {
        ArrayList<Group> list = new ArrayList<>();
        try {
            String sql = "Select g.gid, g.gname, g.lid, g.startdate,\n"
                    + "sub.subid, sub.subfullname, sub.subname,\n"
                    + "t.termid, t.startdate,\n"
                    + "sem.semid, sem.semname, sem.shortname,\n"
                    + "y.yid, y.yname\n"
                    + "from [Group] g\n"
                    + "join [Subject] sub on g.subid = sub.subid\n"
                    + "join Term t on t.termid = g.termid\n"
                    + "join Semester sem on t.semid = sem.semid\n"
                    + "join [Year] y on y.yid = t.yid\n"
                    + "where g.lid = ?\n"
                    + "and g.termid = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lid);
            ps.setInt(2, tid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                g.setStartDate(rs.getDate("startdate").toLocalDate());

                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setFullName(rs.getString("subfullname"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);

                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                g.setSupervisor(l);

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
                list.add(g);
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Group> g = new GroupDAO().getAllGroupForLecture(1, 2);
        for (Group gr : g) {
            System.out.println(gr.getId() + "\t" + gr.getName());
        }
    }
}
