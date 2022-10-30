/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Ngo Tung Son
 */
public class Session {
    private int id; // ok
    private Lecturer lecturer; //ok
    private Room room; //ok
    private TimeSlot timeslot; //ok
    private LocalDate date; //ok
    private Group group;
    private int index; // ok
    private boolean attandated; // ok
    private Attandance atten = new Attandance();
    private ArrayList<Attandance> attandances = new ArrayList<>();

    public Attandance getAtten() {
        return atten;
    }

    public void setAtten(Attandance atten) {
        this.atten = atten;
    }
    
    public boolean getAttandated() {
        return attandated;
    }
    
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isAttandated() {
        return attandated;
    }

    public void setAttandated(boolean attandated) {
        this.attandated = attandated;
    }

    public ArrayList<Attandance> getAttandances() {
        return attandances;
    }

    public void setAttandances(ArrayList<Attandance> attandances) {
        this.attandances = attandances;
    }
    
    public String isAttended() {
        String s;
        if (attandated == true && atten.getRecordTime() != null && atten.isPresent() == true) {
            s = "Attended";
        } else if (attandated == true && atten.getRecordTime() != null && atten.isPresent() == false) {
            s = "Absent";
        } else{
            s = "Not yet";
        }
        return s;
    }

    @Override
    public String toString() {
        return id + "\t" + date + "\t" + group.getName() + "\t" + group.getSubject().getName() + "\t" + lecturer.getName() + "\t" + timeslot.getName() + "\t" + isAttended();
        
    }
    
    public String getSes() {
        return id + "\t" + date + "\t" + group.getName() + "\t" + group.getSubject().getName() + "\t" + lecturer.getName() + "\t" + timeslot.getName() ;
    }
    
}
