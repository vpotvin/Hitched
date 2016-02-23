package edu.uco.weddingcrashers.hitched;

import java.util.Date;

/**
 * Created by drenf on 2/18/2016.
 */
public class DateLabel {
    Date startDate;
    Date endDate;
    String title;


    public DateLabel(Date startDate, Date endDate, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
