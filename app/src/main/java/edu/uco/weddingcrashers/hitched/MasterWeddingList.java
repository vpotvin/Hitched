package edu.uco.weddingcrashers.hitched;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MasterWeddingList extends FragmentActivity implements MasterListNewItemInput.NoticeDialogListener{

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Object> theList;
    private MasterListRecViewAdapter masterListAdapter;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_wedding_list);
        // addButton = (Button) findViewById(R.id.addTaskButton);// Rehana Moved this button to toolbar

        recyclerView = (RecyclerView) findViewById(R.id.masterListRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(30));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        theList = new ArrayList<Object>();

        buildParse();

        int day = 1;
        boolean com = false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015,9,1);
        for(int x = 0; x < 30 ; x++)
        {


            theList.add(new MasterListItem("Test Item"+ x,calendar.getTime(), new Date(), "here are some notes", com,""));
            calendar.add(Calendar.DAY_OF_MONTH, 10);
            if(com == false)
            {
                com = true;
            }
            else
            {
                com = false;
            }
        }
        calendar.set(2015,1,1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2015,8,1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Sixteen to Nine Months Before "));
        calendar.add(Calendar.MONTH, 7);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Eight Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Seven to Six Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Five to Four Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Three Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Two Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "One Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.WEEK_OF_MONTH, 3);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Week Of Wedding "));


        Collections.sort(theList, new theListComparator());
        masterListAdapter = new MasterListRecViewAdapter(theList);
        recyclerView.setAdapter(masterListAdapter);

        //masterListAdapter.notifyDataSetChanged();


//        addButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                DialogFragment newFragment = new MasterListNewItemInput();
//                newFragment.show(getFragmentManager(), "New Entry");
//            }
//        }); // Rehana Moved this button to toolbar

    }

    private void buildParse() {
        ParseObject masterListItem = new ParseObject("MasterListItem");
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        //FragmentManager fragmentManager = getActivity().getFragmentManager();
        DialogFragment dialog = new MasterListNewItemInput();
        dialog.show(getFragmentManager(), "MasterListNewItemInput");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        EditText Title = (EditText) dialogView.findViewById(R.id.masterTitleInputEdit);
        EditText Notes = (EditText) dialogView.findViewById(R.id.MasterNotesInputEdit);
        DatePicker dueDate = (DatePicker) dialogView.findViewById(R.id.MasterDueDateInputPicker);
        int day = dueDate.getDayOfMonth();
        int month = dueDate.getMonth();
        int year =  dueDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        addTask(Title.getText().toString(),Notes.getText().toString(),calendar.getTime());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();

    }

    private void addTask(String Title, String Notes, Date dueDate)
    {
        theList.add(new MasterListItem(Title, dueDate, null, Notes, false,""));
        Collections.sort(theList, new theListComparator());
        masterListAdapter.notifyDataSetChanged();
    }
    ///Rehana Added Toolbar from here

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master_wedding_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_add){
            DialogFragment newFragment = new MasterListNewItemInput();
            newFragment.show(getFragmentManager(), "New Entry");

        }

        return super.onOptionsItemSelected(item);
    }

    static class theListComparator implements Comparator<Object>
    {
        public int compare(Object c1, Object c2)
        {
            if(c1 instanceof  MasterListItem && c2 instanceof  MasterListItem)
            {
                if(((MasterListItem) c1).getDueDate().before(((MasterListItem) c2).getDueDate()))
                {
                    return -1;
                }
                else if(((MasterListItem) c1).getDueDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else if(c1 instanceof MasterListItem && c2 instanceof DateLabel)
            {
                if( ((MasterListItem) c1).getDueDate().after(((DateLabel) c2).getStartDate()) && ((MasterListItem) c1).getDueDate().before(((DateLabel) c2).getEndDate()) )
                {
                    return 1;
                }
                else if(((MasterListItem) c1).getDueDate().after(((DateLabel) c2).getEndDate()))
                {
                    return 1;
                }
                else if(((MasterListItem) c1).getDueDate().before(((DateLabel) c2).getStartDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else if(c1 instanceof  DateLabel && c2 instanceof  MasterListItem)
            {
                if(((DateLabel) c1).getStartDate().before(((MasterListItem) c2).getDueDate()) && ((DateLabel) c1).getEndDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else if(((DateLabel) c1).getStartDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else if(((DateLabel) c1).getEndDate().before(((MasterListItem) c2).getDueDate()))
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                if(((DateLabel) c1).getStartDate().before(((DateLabel) c2).getStartDate()))
                {
                    return -1;
                }
                else if(((DateLabel) c1).getStartDate().after(((DateLabel) c2).getStartDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }
}
