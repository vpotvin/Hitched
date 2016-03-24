package edu.uco.weddingcrashers.hitched;

/**
 * Created by vdpotvin on 2/2/16.
 */
public class BudgetItem {
    private String title;
    private double value;
    private double used;

    public BudgetItem(String title, double value, double used) {
        this.title = title;
        this.value = value;
        this.used = used;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }
}
