/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.GroupDAO;
import dao.LecturerDAO;
import dao.SessionDAO;
import dao.StudentDAO;
import dao.TermDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Group;
import model.Lecturer;
import model.Session;
import model.Student;
import model.Term;

/**
 *
 * @author Admin
 */
public class AttendanceReportForLecture extends BaseRoleController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String rawLecId = request.getParameter("lid");
        String rawTermId = request.getParameter("termid");
        String rawGroupId = request.getParameter("gid");
        int termId = -1;
        int groupId = -1;
        int lectureId = account.getId();
        if (rawTermId != null && rawGroupId != null) {
            termId = Integer.parseInt(rawTermId);
            groupId = Integer.parseInt(rawGroupId);
        }

        ArrayList<Term> t = new TermDAO().getAllTerm();
        if (t.isEmpty() != true) {
            request.setAttribute("term", t);
        }
        request.setAttribute("lid", lectureId);

        ArrayList<Group> g = new GroupDAO().getAllGroupForLecture(lectureId, termId);
        request.setAttribute("group", g);

        ArrayList<Session> sesionDate = new SessionDAO().getAllSessionDate(groupId, termId, lectureId);
        request.setAttribute("date", sesionDate);

        ArrayList<Student> s = new StudentDAO().getAllAttendent(lectureId, groupId, termId);
        request.setAttribute("student", s);
        
        Lecturer lec = new LecturerDAO().getLecturerByIdAndLogin(account.getId(), account.getUsername());
        request.setAttribute("lec", lec);
        request.getRequestDispatcher("attendance_report_lecturer.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

}
