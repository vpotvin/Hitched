package edu.uco.weddingcrashers.hitched;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class ParseDatabase extends Application {

    public static String USER_ID;
    public static String USER_NAME;
    public static String COMBINED_USERNAME;
    private String userState = "Oklahoma";

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //RegisterItineraryItemSubclass
        ParseObject.registerSubclass(ItineraryItem.class);
        ParseObject.registerSubclass(GuestListItem.class);
        ParseObject.registerSubclass(MasterListItem.class);
        ParseObject.registerSubclass(BudgetItem.class);
        ParseObject.registerSubclass(WeddingVows.class);
        ParseObject.registerSubclass(Table.class);
        //ParseObject.registerSubclass(BudgetItem.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // Optionally enable public read access.
        //defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


    }
}