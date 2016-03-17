package edu.uco.weddingcrashers.hitched;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by PC User on 3/17/2016.
 */
public class ChooseStateFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private Spinner mSpinner;
    private String userState;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choose_state,null);
        mSpinner = (Spinner) view.findViewById(R.id.state_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.StateList, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Choose your state")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((ParseDatabase)getActivity().getApplication()).setUserState(userState);
                        Toast.makeText(getActivity(),"Marked " + userState+" as default location" ,Toast.LENGTH_SHORT).show();
                        FragmentManager manager = getFragmentManager();
                        SaveVendorFragment dialog = new SaveVendorFragment();
                        dialog.show(manager,"MenuDialog");

                    }
                })
//                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                       //do something
//                    }
//                })
                .create();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        userState = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getActivity(),"Please choose your default state",Toast.LENGTH_SHORT).show();
    }
}
