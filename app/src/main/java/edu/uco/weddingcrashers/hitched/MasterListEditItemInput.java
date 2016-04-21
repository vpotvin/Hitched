package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * Created by drenf on 4/5/2016.
 */
public class MasterListEditItemInput extends DialogFragment {



    public interface NoticeDialogListener2 {
        public void onDialogPositiveClick2(DialogFragment dialog);
        public void onDialogNegativeClick2(DialogFragment dialog);
    }

    NoticeDialogListener2 mListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        View view = getActivity().getLayoutInflater().inflate(R.layout.master_list_edit_item_input, null);
        int pos = args.getInt("pos", 0);
        MasterListItem toEdit = (MasterListItem)args.getSerializable("item");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        EditText title = (EditText) view.findViewById(R.id.masterTitleInput);
        DatePicker dueDate = (DatePicker) view.findViewById(R.id.MasterDueDateInput);
        EditText notes = (EditText) view.findViewById(R.id.MasterNotesEdit);
        CheckBox comp = (CheckBox) view.findViewById(R.id.MasterCompletedEditBox);
        title.setText(toEdit.getTitle());
        dueDate.updateDate(toEdit.getDueDate().getYear(), toEdit.getDueDate().getMonth(), toEdit.getDueDate().getDay());
        notes.setText(toEdit.getNotes());
        comp.setChecked(toEdit.isCompleted());
        TextView hisPos = (TextView) view.findViewById(R.id.hiddenPos);
        hisPos.setText(Integer.toString(pos));
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick2(MasterListEditItemInput.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick2(MasterListEditItemInput.this);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener2) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
