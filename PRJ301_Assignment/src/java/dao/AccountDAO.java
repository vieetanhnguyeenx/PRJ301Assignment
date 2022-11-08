/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public Account getAccount(String username, String password) {
        try {
            String sql = "select a.username,\n"
                    + "r.rid, r.rname,\n"
                    + "f.fid, f.fname, f.[url]\n"
                    + "from Account a left join Role_Account ra on a.username = ra.username\n"
                    + "left join [Role] r on r.rid = ra.rid\n"
                    + "left join Role_Feature rf on rf.rid = r.rid\n"
                    + "left join Feature f on rf.fid = f.fid\n"
                    + "where a.username = ?\n"
                    + "and [password] = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            Account a = null;
            Role r = new Role();
            while (rs.next()) {
                if (a == null) {
                    a = new Account();
                    a.setUsername(rs.getString("username"));
                    r.setId(rs.getInt("rid"));
                    r.setName(rs.getString("rname"));
                    a.setRole(r);
                }
                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setName(rs.getString("fname"));
                f.setUrl(rs.getString("url"));
                r.getFeature().add(f);
            }
            return a;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getId(Account a) {
        Account account = a;
        try {
            String sql = "";
            if (a.getRole().getId() == 1) {
                sql = "select s.stdid\n"
                        + "from Account a join Student s on a.username = s.[login]\n"
                        + "where a.username = ?";
            } else {
                sql = "select l.lid\n"
                        + "from Account a join Lecturer l on a.username = l.[login]\n"
                        + "where a.username = ?";
            }
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt(1));
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public static void main(String[] args) {
        Account a = new AccountDAO().getAccount("lec2", "lec2");
        if (a != null) {
            System.out.println(a.getUsername());
            System.out.println(a.getRole().getId() + "\t" + a.getRole().getName());
            for (Feature f : a.getRole().getFeature()) {
                System.out.println(f.toString());
            }
            a = new AccountDAO().getId(a);
            System.out.println(a.getId());
        } else {
            System.out.println("null");
        }

    }
}
