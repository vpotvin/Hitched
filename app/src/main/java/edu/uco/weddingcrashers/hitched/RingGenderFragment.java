package edu.uco.weddingcrashers.hitched;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tung on 3/31/2016.
 */
public class RingGenderFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose ring gender")
                .setPositiveButton("Male", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getActivity(),RingActivity.class);
                        i.putExtra("GENDER","women");
                        startActivity(i);
                    }
                })
                .setNegativeButton("Female", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getActivity(),RingActivity.class);
                        i.putExtra("GENDER","men");
                        startActivity(i);
                    }
                })
                .create();
    }
}
