/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SessionDAO;
import dao.StudentDAO;
import dao.TimeSlotDAO;
import helper.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class ScheduleOfWeekStudent extends BaseRoleController {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String rawSid = request.getParameter("sid");
        String rawFrom = request.getParameter("from");
        String rawTo = request.getParameter("to");
        int sid = account.getId();
        LocalDate from = null;
        LocalDate to = null;
        
        if(rawFrom == null || rawFrom.length() == 0) {
            LocalDate today = LocalDate.now();
            ArrayList<LocalDate> sunAndMon = DateTimeHelper.getNearestSunAndMon(today);
            from = sunAndMon.get(0);
            to = sunAndMon.get(1);
        } else {
            from = LocalDate.parse(rawFrom);
            to = LocalDate.parse(rawTo);
        }
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("dates", DateTimeHelper.getDateList(from, to));
        
        ArrayList<TimeSlot> slots = new TimeSlotDAO().getAllTimeSlot();
        request.setAttribute("slots", slots);
        
        Student s = new StudentDAO().getStudentById(sid);
        request.setAttribute("student", s);
        
        ArrayList<Session> sessions = new SessionDAO().getSessionByStudentId(sid, from, to);
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("schedule_of_week.jsp").forward(request, response);
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
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
