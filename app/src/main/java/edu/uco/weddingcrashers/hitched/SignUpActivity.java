package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    protected EditText usernameEditText,passwordEditText, emailEditText,bridename,groomname;
    protected CheckBox checkBox;
    protected DatePicker datePicker;
    protected Button signUpButton;
    private String date, bride, groom;
    private TextView message;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);






        usernameEditText = (EditText)findViewById(R.id.usernameField);
        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        bridename = (EditText)findViewById(R.id.bridename);
        groomname = (EditText)findViewById(R.id.groomname);
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        signUpButton = (Button)findViewById(R.id.signupButton);
        message = (TextView)findViewById(R.id.message);
        date="No Set Date";
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
                    /*****************Tung Part to save to the wedding date table*******************************/
                    ParseObject wDate = new ParseObject("WeddingDate");
                    wDate.put("Day",wday);
                    wDate.put("Month",wmonth);
                    wDate.put("Year",wyear);

                    final String finalDate = date;
                    wDate.saveInBackground();

                    /*****************End of Tung Part********************************************************/

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
                                createMasterWeddingList();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signuptologin) {

            Intent intent1 = new Intent(SignUpActivity.this, Login.class);
            startActivity(intent1);


        }
        return super.onOptionsItemSelected(item);
    }

    public void createMasterWeddingList()
    {
        MasterListItem item = new MasterListItem("Start a wedding folder or binder",null,null,"Begin leafing through bridal, lifestyle, fashion, gardening, design, and food magazines for inspiration",false,"Nine Months");
        item.saveInBackground();
        MasterListItem item1 = new MasterListItem("Work out your budget",null,null,"Determine how much you have to spend, based on your families’ contributions and your own",false,"Nine Months");
        item1.saveInBackground();
        MasterListItem item2 = new MasterListItem("Pick your wedding party",null,null,"As soon as you’re engaged, people will start wondering who’s in",false,"Nine Months");
        item2.saveInBackground();
        MasterListItem item3 = new MasterListItem("Start the guest list",null,null,"Make a head count database to use throughout your planning process, with columns for contact info, RSVPs, gifts, and any other relevant information (Want to keep costs low? It may be brutal, but the best way to do it is to reduce your guest list)",false,"Nine Months");
        item3.saveInBackground();
        MasterListItem item4 = new MasterListItem("Hire a planner, if desired",null,null,"A planner will have relationships with—and insights about—vendors",false,"Nine Months");
        item4.saveInBackground();
        MasterListItem item5 = new MasterListItem("Reserve your date and venues",null,null,"Decide whether to have separate locations for the ceremony and the reception, factoring in travel time between the two places",false,"Nine Months");
        item5.saveInBackground();
        MasterListItem item6 = new MasterListItem("Book your officiant",null,null,"",false,"Nine Months");
        item6.saveInBackground();
        MasterListItem item7 = new MasterListItem("Research photographers, bands, florists, and caterers",null,null,"Keep their contact information in your binder",false,"Nine Months");
        item7.saveInBackground();
        MasterListItem item8 = new MasterListItem("Throw an engagement party, if you wish",null,null,"But remember that your invitees should be on your wedding guest list as well",false,"Nine Months");
        item8.saveInBackground();
        MasterListItem item9 = new MasterListItem("Hire the photographer and the videographer",null,null,"No need to talk specifics yet, but be sure that the people you hire are open to doing the shots that you want",false,"Eight Months");
        item9.saveInBackground();
        MasterListItem item10 = new MasterListItem("Book the entertainment",null,null,"Attend gigs of potential acts to see how they perform in front of audiences, then reserve your favorite",false,"Eight Months");
        item10.saveInBackground();
        MasterListItem item11 = new MasterListItem("Meet caterers",null,null,"If your wedding venue doesn’t offer its own catering service, look for one now and hire the service this month or early next",false,"Eight Months");
        item11.saveInBackground();
        MasterListItem item12 = new MasterListItem("Purchase a dress",null,null,"You’ll need to schedule time for at least three fittings Veil shopping can be postponed for another two to three months",false,"Eight Months");
        item12.saveInBackground();
        MasterListItem item13 = new MasterListItem("Reserve a block of hotel rooms for out-of-town guests",null,null,"Pick three hotels at different price points close to the reception venue",false,"Eight Months");
        item13.saveInBackground();
        MasterListItem item14 = new MasterListItem("Register",null,null,"Sign up at a minimum of three retailers",false,"Eight Months");
        item14.saveInBackground();
        MasterListItem item15 = new MasterListItem("Launch a wedding website",null,null,"Create your personal page through a free provider such as weddingchannel.com. Note the date of the wedding, travel information, and accommodations. Then send the link to invitees",false,"Eight Months");
        item15.saveInBackground();
        MasterListItem item16 = new MasterListItem("Select and purchase invitations",null,null,"Hire a calligrapher, if desired. Addressing cards is time-consuming, so you need to budget accordingly",false,"Six Months");
        item16.saveInBackground();
        MasterListItem item17 = new MasterListItem("Start planning a honeymoon",null,null,"Make sure that your passports are up-to-date, and schedule doctors’ appointments for any shots you may need",false,"Six Months");
        item17.saveInBackground();
        MasterListItem item18 = new MasterListItem("Shop for bridesmaids’ dresses",null,null,"Allow at least six months for the dresses to be ordered and sized",false,"Six Months");
        item18.saveInBackground();
        MasterListItem item19 = new MasterListItem("Meet with the officiant",null,null,"Map out the ceremony and confirm that you have all the official documents for the wedding (these vary by county and religion)",false,"Six Months");
        item19.saveInBackground();
        MasterListItem item20 = new MasterListItem("Send save-the-date cards",null,null,"",false,"Six Months");
        item20.saveInBackground();
        MasterListItem item21 = new MasterListItem("Reserve structural and electrical necessities",null,null,"Book portable toilets for outdoor events, extra chairs if you need them, lighting components, and so on",false,"Six Months");
        item21.saveInBackground();
        MasterListItem item22 = new MasterListItem("Book a florist",null,null,"Florists can serve multiple clients on one day, which is why you can wait a little longer to engage one. Plus, at this point, you’ll be firm on what your wedding palette will be",false,"Six Months");
        item22.saveInBackground();
        MasterListItem item23 = new MasterListItem("Arrange transportation",null,null,"Consider limos, minibuses, trolleys, and town cars. (But know that low-to-the-ground limos can make entries and exits dicey if you’re wearing a fitted gown.)",false,"Six Months");
        item23.saveInBackground();
        MasterListItem item24 = new MasterListItem("Start composing a day-of timeline",null,null,"Draw up a schedule of the event and slot in each component (the cake-cutting, the first dance)",false,"Six Months");
        item24.saveInBackground();
        MasterListItem item25 = new MasterListItem("Book the rehearsal and rehearsal-dinner venues",null,null,"Negotiate the cost and the menu. If you’re planning to host a day-after brunch for guests, book that place as well",false,"Four Months");
        item25.saveInBackground();
        MasterListItem item26 = new MasterListItem("Check on the wedding invitations",null,null,"Ask the stationer for samples of the finished invitations and revise them to suit your needs",false,"Four Months");
        item26.saveInBackground();
        MasterListItem item27 = new MasterListItem("Select and order the cake",null,null,"Some bakers require a long lead time. Attend several tastings before committing to any baker",false,"Four Months");
        item27.saveInBackground();
        MasterListItem item28 = new MasterListItem("Send your guest list to the host of your shower",null,null,"Provided you, ahem, know about the shower",false,"Four Months");
        item28.saveInBackground();
        MasterListItem item29 = new MasterListItem("Purchase wedding shoes and start dress fittings",null,null,"Bring the shoes along to your first fitting so the tailor can choose the appropriate length for your gown",false,"Four Months");
        item29.saveInBackground();
        MasterListItem item30 = new MasterListItem("Schedule hair and makeup artists",null,null,"Make a few appointments with local experts to try them out. Snap a photo at each so you can compare results",false,"Four Months");
        item30.saveInBackground();
        MasterListItem item31 = new MasterListItem("Choose your music",null,null,"What should be playing when the wedding party is announced? During dinner? To kick off the dancing? Keep a running list of what you want—and do not want—played",false,"Four Months");
        item31.saveInBackground();
        MasterListItem item32 = new MasterListItem("Finalize the menu and flowers",null,null,"You’ll want to wait until now to see what will be available, since food and flowers are affected by season",false,"Three Months");
        item32.saveInBackground();
        MasterListItem item33 = new MasterListItem("Order favors, if desired",null,null,"Some safe bets: monogrammed cookies or a treat that represents your city or region. If you’re planning to have welcome baskets for out-of-town guests, plan those now too",false,"Three Months");
        item33.saveInBackground();
        MasterListItem item34 = new MasterListItem("Make a list of the people giving toasts",null,null,"Which loved ones would you like to have speak at the reception? Ask them now",false,"Three Months");
        item34.saveInBackground();
        MasterListItem item35 = new MasterListItem("Finalize the readings",null,null,"Determine what you would like to have read at the ceremony—and whom you wish to do the readings",false,"Three Months");
        item35.saveInBackground();
        MasterListItem item36 = new MasterListItem("Purchase your undergarments",null,null,"And schedule your second fitting",false,"Three Months");
        item36.saveInBackground();
        MasterListItem item37 = new MasterListItem("Finalize the order of the ceremony and the reception",null,null,"",false,"Three Months");
        item37.saveInBackground();
        MasterListItem item38 = new MasterListItem("Print menu cards, if you like, as well as programs",null,null,"No need to go to a printer, if that’s not in your budget: You can easily create these on your computer",false,"Three Months");
        item38.saveInBackground();
        MasterListItem item39 = new MasterListItem("Purchase the rings",null,null,"This will give you time for resizing and engraving",false,"Three Months");
        item39.saveInBackground();
        MasterListItem item40 = new MasterListItem("Send your event schedule to the vendors",null,null,"Giving them a first draft now allows ample time for tweaks and feedback",false,"Three Months");
        item40.saveInBackground();
        MasterListItem item41 = new MasterListItem("Touch base again with all the vendors",null,null,"Make sure any questions you or they had on your first draft have been answered",false,"Two Months");
        item41.saveInBackground();
        MasterListItem item42 = new MasterListItem("Meet with the photographer",null,null,"Discuss specific shots, and walk through the locations to note spots that appeal to you",false,"Two Months");
        item42.saveInBackground();
        MasterListItem item43 = new MasterListItem("Review the playlist with the band or deejay",null,null,"Though you probably won’t be able to dictate every single song played, you should come prepared with a wish list",false,"Two Months");
        item43.saveInBackground();
        MasterListItem item44 = new MasterListItem("Send out the invitations",null,null,"The rule of thumb: Mail invitations six to eight weeks before the ceremony, setting the RSVP cutoff at three weeks after the postmark date",false,"Two Months");
        item44.saveInBackground();
        MasterListItem item45 = new MasterListItem("Submit a newspaper wedding announcement",null,null,"If you’re planning to include a photograph, check the publication’s website: Some have strict rules about how the photo should look",false,"Two Months");
        item45.saveInBackground();
        MasterListItem item46 = new MasterListItem("Enjoy a bachelorette party",null,null,"Arranging a night out with your girlfriends generally falls to the maid of honor. But if she hasn’t mentioned one to you by now, feel free to ask—for scheduling purposes, of course!—if a celebration is in the works",false,"Two Months");
        item46.saveInBackground();
        MasterListItem item47 = new MasterListItem("Enter RSVPs into your guest-list database",null,null,"Phone people who have not yet responded",false,"One Month");
        item47.saveInBackground();
        MasterListItem item48 = new MasterListItem("Get your marriage license",null,null,"The process can take up to six days, but it’s good to give yourself some leeway. If you are changing your name, order several copies",false,"One Month");
        item48.saveInBackground();

        MasterListItem item49 = new MasterListItem("Mail the rehearsal-dinner invitations",null,null,"",false,"One Month");
        item49.saveInBackground();
        MasterListItem item50 = new MasterListItem("Visit the dressmaker for (with luck!) your last dress fitting",null,null,"For peace of mind, you may want to schedule a fitting the week of your wedding. You can always cancel the appointment if you try on the dress then and it fits perfectly",false,"One Month");
        item50.saveInBackground();
        MasterListItem item51 = new MasterListItem("Stock the bar",null,null,"Now that you have a firm head count you can order accordingly",false,"One Month");
        item51.saveInBackground();
        MasterListItem item52 = new MasterListItem("Send out as many final payments as you can",null,null,"",false,"One Month");
        item52.saveInBackground();
        MasterListItem item53 = new MasterListItem("Confirm times for hair and makeup and all vendors",null,null,"",false,"One Month");
        item53.saveInBackground();
        MasterListItem item54 = new MasterListItem("E-mail and print directions for drivers of transport vehicles",null,null,"This gives the chauffeurs ample time to navigate a route",false,"One Month");
        item54.saveInBackground();
        MasterListItem item55 = new MasterListItem("Assign seating",null,null,"Draw out table shapes on a layout of the room to help plan place settings. Write the names of female guests on pink sticky notes and the names of male guests on blue sticky notes so you can move people about without resketching the entire setting",false,"One Month");
        item55.saveInBackground();
        MasterListItem item56 = new MasterListItem("Purchase bridesmaids’ gifts",null,null,"You’ll present them at the rehearsal dinner",false,"One Month");
        item56.saveInBackground();
        MasterListItem item57 = new MasterListItem("Write vows, if necessary",null,null,"Get your hair cut and colored, if desired",false,"One Month");
        item57.saveInBackground();
        MasterListItem item58 = new MasterListItem("Reconfirm arrival times with vendors",null,null,"",false,"Week of the Wedding");
        item58.saveInBackground();
        MasterListItem item59 = new MasterListItem("Delegate small wedding-day tasks",null,null,"Choose someone to bustle your dress, someone to carry your things, someone to be in charge of gifts (especially the enveloped sort), someone to hand out tips, and someone to be the point person for each vendor",false,"Week of the Wedding");
        item59.saveInBackground();
        MasterListItem item60 = new MasterListItem("Send a timeline to the bridal party",null,null,"Include every member’s contact information, along with the point people you’ve asked to deal with the vendors, if problems arise",false,"Week of the Wedding");
        item60.saveInBackground();
        MasterListItem item61 = new MasterListItem("Pick up your dress",null,null,"Or make arrangements for a delivery",false,"Week of the Wedding");
        item61.saveInBackground();
        MasterListItem item62 = new MasterListItem("Check in one last time with the photographer",null,null,"Supply him or her with a list of moments you want captured on film",false,"Week of the Wedding");
        item62.saveInBackground();
        MasterListItem item63 = new MasterListItem("Set aside checks for the vendors",null,null,"And put tips in envelopes to be handed out at the event",false,"Week of the Wedding");
        item63.saveInBackground();
        MasterListItem item64 = new MasterListItem("Book a spa treatment",null,null,"Make an appontment for a manicure and a pedicure the day before the wedding. (You might want to get a stress-relieving massage, too.)",false,"Week of the Wedding");
        item64.saveInBackground();
        MasterListItem item65 = new MasterListItem("Send the final guest list to the caterer and all venues hosting your wedding-related events",null,null,"Typically, companies close their lists 72 hours in advance",false,"Week of the Wedding");
        item65.saveInBackground();
        MasterListItem item66 = new MasterListItem("Break in your shoes",null,null,"",false,"Week of the Wedding");
        item66.saveInBackground();
        MasterListItem item67 = new MasterListItem("Assemble and distribute the welcome baskets",null,null,"",false,"Week of the Wedding");
        item67.saveInBackground();
        MasterListItem item68 = new MasterListItem("Pack for your honeymoon",null,null,"",false,"Week of the Wedding");
        item68.saveInBackground();

        if(!(date.equals("No Set Date")))
        {

            UtilityFunctions.updateMasterListDueDates();
        }


    }

}