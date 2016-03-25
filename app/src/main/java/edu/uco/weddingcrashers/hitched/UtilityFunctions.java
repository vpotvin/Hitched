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
        final Calendar weddingDate = null;
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("User");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int day=scoreList.get(0).getInt("day");
                    int month=scoreList.get(0).getInt("month");
                    int year=scoreList.get(0).getInt("year");
                    weddingDate.set(year, month - 1, day, 0, 0);

                } else {

                }
            }
        });
        ParseQuery<MasterListItem> query = ParseQuery.getQuery(MasterListItem.class);

        query.findInBackground(new FindCallback<MasterListItem>() {
            @Override
            public void done(List<MasterListItem> objects, ParseException e) {
                ArrayList<MasterListItem> theList = new ArrayList<MasterListItem>(objects);
                updateHelper(theList,weddingDate.getTime());
            }

            private void updateHelper(ArrayList<MasterListItem> theList,Date weddingDate) {
                Calendar cal = Calendar.getInstance();


                for(int x = 0; x < theList.size(); x++)
                {
                    cal.setTime(weddingDate);
                    MasterListItem current = theList.get(x);

                    if(current.getDistanceFromWeddingDay().equals("static"))
                    {

                    }
                    else if(current.getDistanceFromWeddingDay().equals("Nine Months"))
                    {
                        cal.add(Calendar.MONTH,-9);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Eight Months"))
                    {
                        cal.add(Calendar.MONTH,-8);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Six Months"))
                    {
                        cal.add(Calendar.MONTH,-6);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Four Months"))
                    {
                        cal.add(Calendar.MONTH,-4);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Three Months"))
                    {
                        cal.add(Calendar.MONTH,-3);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Two Months"))
                    {
                        cal.add(Calendar.MONTH,-2);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("One Month"))
                    {
                        cal.add(Calendar.MONTH,-1);
                    }
                    else if(current.getDistanceFromWeddingDay().equals("Week of the Wedding"))
                    {
                        cal.add(Calendar.WEEK_OF_MONTH,-1);
                    }

                    current.setDueDate(cal.getTime());
                    current.saveInBackground();
                }
            }
        });

    }
}
