package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
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

                String sharedUser = shareWith.getText().toString();

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", sharedUser);
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {

                            String getUser = shareWith.getText().toString();

                            Toast.makeText(getApplicationContext(), "Your list has been shared with " + getUser,
                                    Toast.LENGTH_LONG).show();

                            Intent data = new Intent();
                            setResult(RESULT_OK, data);
                            ShareActivity.this.finish();

                        } else {

                            Toast.makeText(getApplicationContext(), "User does not exist. Enter a valid username.",
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

    }
}
