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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

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

       addButton = (Button) findViewById(R.id.addTaskButton);// Rehana Moved this button to toolbar

        recyclerView = (RecyclerView) findViewById(R.id.masterListRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(30));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        theList = new ArrayList<Object>();




        masterListAdapter = new MasterListRecViewAdapter();
        recyclerView.setAdapter(masterListAdapter);



        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new MasterListNewItemInput();
                newFragment.show(getFragmentManager(), "New Entry");
            }
        }); // Rehana Moved this button to toolbar

    }

    /*private void buildParse() {
        ParseObject masterListItem = new ParseObject("MasterListItem");
    }
*/
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
        MasterListItem item = new MasterListItem(Title, dueDate, null,Notes,false,"static");
        item.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    masterListAdapter.updateTheList();
                }
            }
        });
        //theList.add(new MasterListItem(Title, dueDate, null, Notes, false));
        //Collections.sort(theList, new theListComparator());
        //masterListAdapter.notifyDataSetChanged();
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


}
