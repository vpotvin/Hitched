package edu.uco.weddingcrashers.hitched;

import android.os.Parcelable;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drenf on 2/2/2016.
 */
@ParseClassName("MasterListItem")
public class MasterListItem extends ParseObject implements Serializable {


    private ArrayList<GuestListItem> assignedTo;


    public MasterListItem()
    {}

    public MasterListItem(String title, Date dueDate, Date completedDate,String notes,boolean completed, String distanceFromWeddingDay) {
        this();
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setCompletedDate(completedDate);
        this.setNotes(notes);
        this.setCompleted(completed);
        this.setDistanceFromWeddingDay(distanceFromWeddingDay);
        assignedTo = new ArrayList<>();
    }

    public String getDistanceFromWeddingDay() {
        return getString("getDistanceFromWeddingDay");
    }

    public void setDistanceFromWeddingDay(String distanceFromWeddingDay) {
        put("distanceFromWeddingDay", distanceFromWeddingDay);
    }

    public void addToAssignedTo(GuestListItem guest)
    {
        assignedTo.add(guest);
    }

    public void removeFromAssignedTo(int position)
    {
        assignedTo.remove(position);
    }

    public ArrayList<GuestListItem> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(ArrayList<GuestListItem> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);

    }

    public Date getDueDate() {
       return getDate("dueDate");
    }

    public void setDueDate(Date dueDate) {
        if(dueDate != null)
        {
            put("dueDate", dueDate);
        }


    }

    public Date getCompletedDate() {
        return getDate("completedDate");
    }

    public void setCompletedDate(Date completedDate) {
        if(completedDate != null)
        {
            put("completedDate", completedDate);
        }

    }

    public String getNotes() {
        return getString("notes");
    }

    public void setNotes(String notes) {
        put("notes", notes);
    }

    public boolean isCompleted() {
        return getBoolean("completed");
    }

    public void setCompleted(boolean completed) {
        put("completed", completed);
    }
}
