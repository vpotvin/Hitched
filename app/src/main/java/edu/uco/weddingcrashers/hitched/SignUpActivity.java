package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

    protected EditText usernameEditText,passwordEditText, emailEditText,bridename,groomname;
    protected CheckBox checkBox;
    protected DatePicker datePicker;
    protected Button signUpButton;
    private String date, bride, groom;
    private TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        usernameEditText = (EditText)findViewById(R.id.usernameField);
        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        bridename = (EditText)findViewById(R.id.bridename);
        groomname = (EditText)findViewById(R.id.groomname);
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        signUpButton = (Button)findViewById(R.id.signupButton);
        message = (TextView)findViewById(R.id.message);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    datePicker.setVisibility(View.INVISIBLE);
                    date="No Set Date";
                }else if(checkBox.isChecked()){
                    datePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                bride = bridename.getText().toString();
                groom = groomname.getText().toString();
                String date = "";

                if(password.length()<8||password.isEmpty()){
                    message.setVisibility(View.VISIBLE);
                    message.setText("You must pick a valid password.");

                }else{
                username = username.trim();
                password = password.trim();
                email = email.trim();
                bride = bride.trim();
                groom = groom.trim();
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth();
                    int year = datePicker.getYear();
                date = "" + day + "-" + month  + "-" + year;
                String wday= ""+day;
                String wmonth= ""+month;
                String wyear= ""+year;
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    setProgressBarIndeterminateVisibility(true);

                    final ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.put("groom", groom);
                    newUser.put("bride", bride);
                    newUser.put("date", date);
                    newUser.put("day", wday);
                    newUser.put("month", wmonth);
                    newUser.put("year", wyear);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);

                            if (e == null) {
                                // Success!

                                ParseDatabase.USER_ID = newUser.getObjectId().toString();
                                ParseDatabase.USER_NAME = newUser.getUsername();
                                ParseDatabase.COMBINED_USERNAME = ParseDatabase.USER_ID + ParseDatabase.USER_NAME;


                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}