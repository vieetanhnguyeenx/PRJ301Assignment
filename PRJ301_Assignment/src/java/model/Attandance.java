/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Ngo Tung Son
 */
public class Attandance {
    private Session session;
    private Student student;
    private boolean present;
    private String description;
    private LocalDateTime recordTime;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }
    
    public int isPresentInterger() {
        int a;
        if(present == true && recordTime != null && session.getAttandated() == true) {
            a = 1;
        } else if (present == false && recordTime != null && session.getAttandated() == true) {
            a = 0;
        } else {
            a = -1;
        }
        return a;
    }
    
    public String isPresentString() {
        String s;
        if(present == true && recordTime != null && session.getAttandated() == true) {
            s = "Present";
        } else if (present == false && recordTime != null && session.getAttandated() == true) {
            s = "Absent";
        } else {
            s = "Future";
        }
        return s;
    }
    
    @Override
    public String toString() {
        return "Attandance{" + "session=" + session + ", present=" + present + ", description=" + description + ", recordTime=" + recordTime + '}';
    }
    
    
    
    
}
