/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Attandance;
import model.Session;
import model.Student;

/**
 *
 * @author Admin
 */
public class AttendentTaking extends BaseRoleController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        Session ses = new Session();
        ses.setId(Integer.parseInt(req.getParameter("sesid")));
        String[] stdids = req.getParameterValues("stdid");
        for (String stdid : stdids) {
            Attandance a = new Attandance();
            Student s = new Student();
            a.setStudent(s);
            a.setDescription(req.getParameter("description" + stdid));
            a.setPresent(req.getParameter("present" + stdid).equals("present"));
            s.setId(Integer.parseInt(stdid));
            ses.getAttandances().add(a);
        }

        SessionDAO sd = new SessionDAO();
        sd.updateAttendent(ses);
        resp.sendRedirect("attendent_taking?id=" + ses.getId());
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String rawSesId = req.getParameter("id");
        int sesid = Integer.parseInt(rawSesId);
        ArrayList<Session> sessionList = new SessionDAO().getAllSessionForLecturer(account.getId());
        boolean isWorkingOn = false;
        for (Session s : sessionList) {
            if (s.getId() == sesid) {
                isWorkingOn = true;
                break;
            }
        }
        if (isWorkingOn) {
            Session ses = new SessionDAO().getSessionForAttending(sesid);
            req.setAttribute("ses", ses);
            req.getRequestDispatcher("attendent_taking.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("access denied!");
        }

    }

}
