package edu.uco.weddingcrashers.hitched;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GuestListActivity extends FragmentActivity implements GuestListNewItem.NoticeDialogListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<GuestListItem> theList;
    private GuestListRecViewAdapter guestListAdapter;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        addButton = (Button) findViewById(R.id.addGuestButton);

        recyclerView = (RecyclerView) findViewById(R.id.guestListRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(30));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        theList = new ArrayList<GuestListItem>();

        theList.add(new GuestListItem("Derek Renfro", "6816 Bear Canyon", "Oklahoma City", "OK", "73162", "Groom", "405-517-7375", true));
        theList.add(new GuestListItem("Derek Renfro", "6816 Bear Canyon", "Oklahoma City", "OK", "73162", "Groom", "405-517-7375", true));

        guestListAdapter = new GuestListRecViewAdapter(theList);
        recyclerView.setAdapter(guestListAdapter);

        /* moved to menu bar
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new GuestListNewItem();
                newFragment.show(getFragmentManager(), "New Entry");
            }
        });
        */

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        EditText name = (EditText) dialogView.findViewById(R.id.GuestListNameInputEdit);
        EditText phone = (EditText) dialogView.findViewById(R.id.GuestListPhoneNumberInputEdit);
        EditText address = (EditText) dialogView.findViewById(R.id.GuestListAddressInputEdit);
        EditText state = (EditText) dialogView.findViewById(R.id.GuestListStateInputEdit);
        EditText zip = (EditText) dialogView.findViewById(R.id.GuestListZipcodeInputEdit);
        EditText city = (EditText) dialogView.findViewById(R.id.GuestListCityInputEdit);
        EditText role = (EditText) dialogView.findViewById(R.id.GuestListRoleInputEdit);
        CheckBox WP = (CheckBox) dialogView.findViewById(R.id.GuestListWeddingPartuInputCheckBox);


        addTask(name.getText().toString(), address.getText().toString(), city.getText().toString(), state.getText().toString(), zip.getText().toString(), role.getText().toString(),phone.getText().toString(), WP.isChecked());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button

    }

    private void addTask(String name, String address, String city, String state, String zip, String role,String phone, boolean wp)
    {
        theList.add(new GuestListItem(name, address, city,state,zip,role,phone,wp));
        guestListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guest_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_add){
            DialogFragment newFragment = new GuestListNewItem();
            newFragment.show(getFragmentManager(), "New Entry");

        }

        return super.onOptionsItemSelected(item);
    }

}
