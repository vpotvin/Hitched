package edu.uco.weddingcrashers.hitched;

import java.util.Date;

/**
 * Created by drenf on 2/2/2016.
 */
public class MasterListItem {

    private String title;
    private Date dueDate;
    private Date completedDate;
    private String notes;
    private boolean completed;

    public MasterListItem(String title, Date dueDate, Date completedDate,String notes,boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.notes = notes;
        this.completed = completed;
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
