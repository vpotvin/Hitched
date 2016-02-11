package edu.uco.weddingcrashers.hitched;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MasterWeddingList extends FragmentActivity implements MasterListNewItemInput.NoticeDialogListener{

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<MasterListItem> theList;
    private MasterListRecViewAdapter masterListAdapter;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_wedding_list);

        addButton = (Button) findViewById(R.id.addTaskButton);

        recyclerView = (RecyclerView) findViewById(R.id.masterListRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(30));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        theList = new ArrayList<MasterListItem>();

        theList.add(new MasterListItem("Test Item1",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item2",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item3",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item4",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item5",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item6",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item7",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item8",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item9",new Date(),new Date(),"here are some notes",false));
        theList.add(new MasterListItem("Test Item10",new Date(),new Date(),"here are some notes",false));

        masterListAdapter = new MasterListRecViewAdapter(theList);
        recyclerView.setAdapter(masterListAdapter);

        //masterListAdapter.notifyDataSetChanged();


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new MasterListNewItemInput();
                newFragment.show(getFragmentManager(), "New Entry");
            }
        });

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
        // User touched the dialog's negative button

    }

    private void addTask(String Title, String Notes, Date dueDate)
    {
        theList.add(new MasterListItem(Title,dueDate, null, Notes, false));
        masterListAdapter.notifyDataSetChanged();
    }
}
