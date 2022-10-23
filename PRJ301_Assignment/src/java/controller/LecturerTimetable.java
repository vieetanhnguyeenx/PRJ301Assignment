/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.LecturerDAO;
import dao.SessionDAO;
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
import model.Lecturer;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class LecturerTimetable extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String rawLid = request.getParameter("lid");
        String rawFrom = request.getParameter("from");
        String rawTo = request.getParameter("to");
        int lid = Integer.parseInt(rawLid);
        LocalDate from = null;
        LocalDate to = null;
        
        if (rawFrom == null || rawFrom.length() == 0) {
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
        
        ArrayList<Session> sessions = new SessionDAO().getFilteredSession(lid, from, to);
        request.setAttribute("sessions", sessions);
        
        Lecturer lecturer = new LecturerDAO().getLecturerById(lid);
        request.setAttribute("lecturer", lecturer);
        
        request.getRequestDispatcher("lecturer_timetable.jsp").forward(request, response);
        
        
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
    }// </editor-fold>

}
