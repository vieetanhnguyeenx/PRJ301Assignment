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
import model.Attandance;
import model.Session;
import model.Student;

/**
 *
 * @author Admin
 */
public class AttendentTaking extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawSesId = request.getParameter("id");
        int sesid = Integer.parseInt(rawSesId);
        Session ses = new SessionDAO().getSessionForAttending(sesid);
        request.setAttribute("ses", ses);
        request.getRequestDispatcher("attendent_taking.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session ses = new Session();
        ses.setId(Integer.parseInt(request.getParameter("sesid")));
        String[] stdids = request.getParameterValues("stdid");
        for (String stdid : stdids) {
            Attandance a = new Attandance();
            Student s = new Student();
            a.setStudent(s);
            a.setDescription(request.getParameter("description"+stdid));
            a.setPresent(request.getParameter("present"+stdid).equals("present"));
            s.setId(Integer.parseInt(stdid));
            ses.getAttandances().add(a);
        }
        
        SessionDAO sd = new SessionDAO();
        sd.updateAttendent(ses);
        response.sendRedirect("attendent_taking?id="+ses.getId());
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
