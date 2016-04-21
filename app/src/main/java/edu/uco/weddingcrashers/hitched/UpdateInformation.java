package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class UpdateInformation extends AppCompatActivity {
    private TextView currentBrideName, currentGroomName, currentWeddingDate;
    private EditText newBrideName, newGroomName;
    private Button update, editBrideButton, editGroomButton;
    protected CheckBox checkBox;
    protected DatePicker datePicker;
    String date, wday,wmonth,wyear,newdate;
    int day,month,year;

    ParseUser currentUser = ParseUser.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        currentBrideName = (TextView) findViewById(R.id.currentBrideName);
        currentGroomName = (TextView) findViewById(R.id.currentGroomName);
        currentWeddingDate = (TextView) findViewById(R.id.currentDate);
        editBrideButton = (Button) findViewById(R.id.editBrideButton);
        editGroomButton = (Button) findViewById(R.id.editGroomButton);
        newBrideName = (EditText) findViewById(R.id.newBrideName);
        newGroomName = (EditText) findViewById(R.id.newGroomName);
        update = (Button) findViewById(R.id.update);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        datePicker = (DatePicker) findViewById(R.id.datePicker);


        if (currentUser != null) {
            currentBrideName.setText(currentUser.getString("bride"));
            currentGroomName.setText(currentUser.getString("groom"));
            currentWeddingDate.setText((currentUser.getString("date")));

        } else {
            currentBrideName.setText("");
            currentGroomName.setText("");
            currentWeddingDate.setText("");
        }
        editBrideButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentBrideName.setVisibility(View.GONE);
                newBrideName.setVisibility(View.VISIBLE);
                editBrideButton.setVisibility(View.GONE);


            }
        });
        editGroomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentGroomName.setVisibility(View.GONE);
                newGroomName.setVisibility(View.VISIBLE);
                editGroomButton.setVisibility(View.GONE);
            }
        });
        newdate = "olddate";
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    datePicker.setVisibility(View.INVISIBLE);
                    date = "No Set Date";
                } else if (checkBox.isChecked()) {
                    datePicker.setVisibility(View.VISIBLE);

                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newbride = newBrideName.getText().toString();
                String newGroom = newGroomName.getText().toString();
                day = datePicker.getDayOfMonth();
                month = (datePicker.getMonth() + 1);
                year = datePicker.getYear();
                date = "" + day + "-" + month + "-" + year;
                wday = "" + day;
                wmonth = "" + month;
                wyear = "" + year;


                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("WeddingDate");
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null && scoreList.size() != 0) {
                            ParseObject object = scoreList.get(0);
                            object.put("Day", Integer.toString(day));
                            object.put("Month", Integer.toString(month));
                            object.put("Year", Integer.toString(year));
                            object.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                UtilityFunctions.updateMasterListDueDates();
                            } else {

                            }
                        }
                    });
                } else {
                    // do something with the error...
                }
            }
        });

                currentUser.put("date", date);
                currentUser.put("day", wday);
                currentUser.put("month", wmonth);
                currentUser.put("year", wyear);

                if (!newbride.equals("")) {
                    currentUser.put("bride", newbride);

                }
                if (!newGroom.equals("")) {
                    currentUser.put("groom", newGroom);
                }
                currentUser.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                        } else {

                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "Your account has been updated!",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(UpdateInformation.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }

}
