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
    private int id;
    private Lecturer lecturer;
    private Room room;
    private TimeSlot timeslot;
    private LocalDate date;
    private Group group;
    private int index;
    private boolean attandated; 
    private ArrayList<Attandance> attandances = new ArrayList<>();

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
    
    public String getAttandenString() {
        if (attandated) {
            return "Attandated";
        } else {
            return "Not yet";
        }
    }
}
