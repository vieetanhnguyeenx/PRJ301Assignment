/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Ngo Tung Son
 */
public class Group {
    private int id;
    private String name;
    private ArrayList<Student> students = new ArrayList<>();
    private Subject subject;
    private Lecturer supervisor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Lecturer supervisor) {
        this.supervisor = supervisor;
    }
    
}
