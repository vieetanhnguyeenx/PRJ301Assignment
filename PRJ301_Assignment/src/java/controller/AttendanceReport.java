/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.GroupDAO;
import dao.StudentDAO;
import dao.TermDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Group;
import model.Student;
import model.Term;

/**
 *
 * @author Admin
 */
public class AttendanceReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawStudentId = request.getParameter("stid");
        String rawTermId = request.getParameter("termid");
        String rawGroupId = request.getParameter("gid");
        int studentId = Integer.parseInt(rawStudentId);
        int groupId = Integer.parseInt(rawGroupId);
        int termId = Integer.parseInt(rawTermId);

        ArrayList<Term> t = new TermDAO().getAllTerm();
        if (t.isEmpty() != true) {
            request.setAttribute("term", t);
        }
        request.setAttribute("studentid", studentId);

        if (termId == -1) {
            termId = t.get(t.size() - 1).getTearmId();
        }
        ArrayList<Group> g = new GroupDAO().getGroupsForStudent(studentId, termId);
        request.setAttribute("group", g);
        Student s = new StudentDAO().getAttandance(studentId, termId, groupId);
        if (s != null) {
            request.setAttribute("attend", s.getAttandances());
        }

        request.getRequestDispatcher("attendance_report_student.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
