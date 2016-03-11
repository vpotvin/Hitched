package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class ShareActivity extends Activity {

    private Button vshare;
    private EditText shareWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        vshare = (Button) findViewById(R.id.shareVenueButton);
        shareWith = (EditText) findViewById(R.id.shareEditText);

        shareWith.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (shareWith.getText().toString().equals("Enter Username You Want to Share With")) {
                    shareWith.setText("");
                }
            }
        });

        vshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sharedUser = shareWith.getText().toString().trim();

                Log.d("WHAT", sharedUser);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("My_Users");
                query.whereEqualTo("My_Username", shareWith.getText().toString().trim());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {

                            Toast.makeText(getApplicationContext(), "User does not exist. Please Enter a valid username.",
                                    Toast.LENGTH_LONG).show();

                        } else {

                            String getUser = shareWith.getText().toString().trim();

                            ParseQuery pushQuery = ParseInstallation.getQuery();
                            pushQuery.whereEqualTo("myusername", getUser);

                            // Send push notification to query
                            ParsePush push = new ParsePush();
                            push.setQuery(pushQuery); // Set our Installation query
                            push.setMessage(ParseDatabase.USER_NAME + " shared a list with you");
                            push.sendInBackground();


                            Log.d("WHAT", "QUERY: " + getUser);

                            Toast.makeText(getApplicationContext(), "Your list has been shared with " + getUser,
                                    Toast.LENGTH_LONG).show();

                            Intent data = new Intent();
                            setResult(RESULT_OK, data);
                            ShareActivity.this.finish();

                        }
                    }
                });
            }
        });

    }
}
