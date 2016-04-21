package edu.uco.weddingcrashers.hitched;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by drenf on 2/2/2016.
 */
public class UtilityFunctions {



    public static void setListViewHeightBasedOnChildren(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }

    public static void updateMasterListDueDates()
    {

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("WeddingDate");
        //query2.whereEqualTo("username", Login.theUsername);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    ParseObject date = scoreList.get(0);
                    int day = Integer.parseInt(date.getString("Day"));
                    int month = Integer.parseInt(date.getString("Month"));
                    int year = Integer.parseInt(date.getString("Year"));


                    Calendar weddingDate = Calendar.getInstance();
                    weddingDate.set(year, month - 1, day, 0, 0);
                    //weddingDate.set(2017, 3 - 1, 9, 0, 0);
                    final Calendar finalDate = weddingDate;

                    try {
                        Thread.sleep(5000);                 //1000 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    ParseQuery<MasterListItem> query = ParseQuery.getQuery(MasterListItem.class);
                    query.findInBackground(new FindCallback<MasterListItem>() {
                        @Override
                        public void done(List<MasterListItem> objects, ParseException e) {
                            ArrayList<MasterListItem> theList = new ArrayList<MasterListItem>(objects);

                            Calendar cal = Calendar.getInstance();


                            for (int x = 0; x < theList.size(); x++) {
                                cal.setTime(finalDate.getTime());
                                MasterListItem current = (MasterListItem) theList.get(x);
                                String distance = current.getDistanceFromWeddingDay();
                                if (current.getDistanceFromWeddingDay().equals("static")) {

                                } else if (distance.equals("Nine Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -40);
                                } else if (distance.equals("Eight Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -31);
                                } else if (distance.equals("Six Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -22);
                                } else if (distance.equals("Four Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -14);
                                } else if (distance.equals("Three Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -10);
                                } else if (distance.equals("Two Months")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -6);
                                } else if (distance.equals("One Month")) {
                                    cal.add(Calendar.WEEK_OF_MONTH, -2);
                                } else if (distance.equals("Week of the Wedding")) {
                                    cal.add(Calendar.DAY_OF_WEEK, -1);
                                }

                                current.setDueDate(cal.getTime());
                                current.saveInBackground();
                            }

                        }


                    });
                } else {

                }
            }
        });


    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();

        if(end < start)
        {
            return -1;
        }

        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}
