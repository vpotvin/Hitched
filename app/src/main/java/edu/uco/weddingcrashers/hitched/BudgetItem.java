package edu.uco.weddingcrashers.hitched;

import com.parse.ParseObject;

/**
 * Created by vdpotvin on 2/2/16.
 */
public class BudgetItem extends ParseObject {

    public BudgetItem(){}

    public BudgetItem(String title, double value, double used) {
        setTitle(title);
        setValue(value);
        setUsed(used);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public double getValue() {
        return getDouble("value");
    }

    public void setValue(double value) {
         put("value", value);
    }

    public double getUsed() {
        return getDouble("used");
    }

    public void setUsed(double used) {
        put("used", used);
    }
}
