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
public class MasterListItem implements Serializable {
// extends ParseObject
    private String title;
    private Date dueDate;
    private Date completedDate;
    private String notes;
    private boolean completed;
    private ArrayList<GuestListItem> assignedTo;
    private String distanceFromWeddingDay;

    public MasterListItem(String title, Date dueDate, Date completedDate,String notes,boolean completed, String distanceFromWeddingDay) {
        this.title = title;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.notes = notes;
        this.completed = completed;
        assignedTo = new ArrayList<>();
        this.distanceFromWeddingDay = distanceFromWeddingDay;
    }

    public String getDistanceFromWeddingDay() {
        return distanceFromWeddingDay;
    }

    public void setDistanceFromWeddingDay(String distanceFromWeddingDay) {
        this.distanceFromWeddingDay = distanceFromWeddingDay;
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
