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

import com.parse.ParseUser;

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
                currentUser.saveInBackground();
                Toast.makeText(getApplicationContext(), "Your account has been updated!",
                        Toast.LENGTH_LONG).show();
                UtilityFunctions.updateMasterListDueDates();
                Intent intent = new Intent(UpdateInformation.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }

}
