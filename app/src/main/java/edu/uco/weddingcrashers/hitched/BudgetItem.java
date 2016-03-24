package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by vdpotvin on 2/2/16.
 */
@ParseClassName("BudgetItem")
public class BudgetItem extends ParseObject {
    public static final String TITLE = "title";
    public static final String USED = "used";
    public static final String VALUE = "value";
    public static final String PAID = "paid";
    public static final String MAIN_TITLE = "main_title";

    public BudgetItem(){}

    public BudgetItem(String title, double value, double used) {
        this();
        this.setTitle(title);
        this.setValue(value);
        this.setUsed(used);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    public double getValue() {
        return getDouble(VALUE);
    }

    public void setValue(double value) {
         put(VALUE, value);
    }

    public double getUsed() {
        return getDouble(USED);
    }

    public void setUsed(double used) {
        put(USED, used);
    }

    public void setPaid(boolean paid) {
        put(PAID, paid);
    }

    public boolean getPaid() {
        return getBoolean(PAID);
    }
}
