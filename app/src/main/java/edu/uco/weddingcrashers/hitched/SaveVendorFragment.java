package edu.uco.weddingcrashers.hitched;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by PC User on 2/18/2016.
 */
public class SaveVendorFragment extends DialogFragment {
    // private static final String ARG_URL = "url";
    private String userState;
    private Spinner mSpinner;
    private Button mDealButton, mMusicButton, mDetailButton, mWebsiteButton;

//    public static SaveVendorFragment newInstance(String url){
//        Bundle args = new Bundle();
//        args.putString(ARG_URL,url);
//
//        SaveVendorFragment fragment = new SaveVendorFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_save_vendor, null);
        // mSpinner = (Spinner)view.findViewById(R.id.state_spinner);
        mDealButton = (Button) view.findViewById(R.id.deal_button);
        mDealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), VendorDealActivity.class);
                startActivity(i);
            }
        });

        mDetailButton = (Button) view.findViewById(R.id.detail_button);
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                startActivity(i);
            }
        });

        mMusicButton = (Button) view.findViewById(R.id.music_button);
        mMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MusicListActivity.class);
                startActivity(i);
            }
        });

        mWebsiteButton = (Button) view.findViewById(R.id.website_button);
        mWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),WebTutorialActivity.class);
                startActivity(i);
            }
        });
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.StateList, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        mSpinner.setAdapter(adapter);
//        mSpinner.setOnItemSelectedListener(this);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Menu")
                .create();
//                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ((ParseDatabase)getActivity().getApplication()).setUserState(userState);
//                        Toast.makeText(getActivity(),"Marked " + userState+" as default location" ,Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .setNegativeButton("Go to Music List", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(getActivity(),MusicListActivity.class);
//                        startActivity(intent);
//                    }
//                })
//                .create();
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
//        userState = adapterView.getItemAtPosition(pos).toString();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//        userState="Oklahoma";
//    }


    }
}
