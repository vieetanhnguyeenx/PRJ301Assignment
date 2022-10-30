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
import model.Semester;
import model.Term;
import model.Year;

/**
 *
 * @author Admin
 */
public class TermDAO {

    public ArrayList<Term> getAllTerm() {
        ArrayList<Term> t = new ArrayList<>();
        try {
            String sql = "select t.termid,\n"
                    + "s.semid, s.semname, s.shortname,\n"
                    + "y.yid, y.yname\n"
                    + "from Term t\n"
                    + "join  Semester s on s.semid = t.semid\n"
                    + "join [Year] y on y.yid = t.yid\n"
                    + "order by semid";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Term term = new Term();
                term.setTearmId(rs.getInt("termid"));

                Semester sem = new Semester();
                sem.setSemId(rs.getInt("semid"));
                sem.setSemesterName(rs.getString("semname"));
                sem.setShortName(rs.getString("shortname"));
                term.setSemester(sem);

                Year y = new Year();
                y.setYid(rs.getInt("yid"));
                y.setYearName(rs.getString("yname"));
                term.setYear(y);

                t.add(term);

            }
        } catch (Exception ex) {
            Logger.getLogger(TermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public static void main(String[] args) {
        ArrayList<Term> t = new TermDAO().getAllTerm();
        for (Term t1 : t) {
            System.out.println(t1.toString());
        }
        System.out.println(t.size());
        System.out.println(t.get(t.size() - 1).getTearmId());

    }
}
