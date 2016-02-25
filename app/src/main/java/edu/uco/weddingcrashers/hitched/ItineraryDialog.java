package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vdpotvin on 2/18/16.
 */
public class ItineraryDialog extends DialogFragment {

    public static final String POSITION_PARAM = "position";
    public static final String TITLE_PARAM = "title";
    public static final String ASSIGNED_PARAM = "assigned";

    private String title;
    private String assigned;
    private int position;
    private Date time;
    private double tip;

    EditText titleEdit;
    EditText assignedEdit;
    EditText tipEdit;

    public interface ItineraryUpdateListener {
        void updateItinerary(String title, String assigned, int position, double tip, Date time);
        void deleteItineraryItem(int position);
    }

    public static ItineraryDialog newInstance(String title, String assigned, int position) {
        ItineraryDialog dialog = new ItineraryDialog();
        Bundle args = new Bundle();
        args.putString(TITLE_PARAM, title);
        args.putString(ASSIGNED_PARAM, assigned);
        args.putInt(POSITION_PARAM, position);
        dialog.setArguments(args);

        return dialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(TITLE_PARAM);
            assigned = getArguments().getString(ASSIGNED_PARAM);
            position = getArguments().getInt(POSITION_PARAM);

            if(title.isEmpty() || title == null) title = "New Item";
            if(assigned.isEmpty() || assigned == null) assigned = "Assigned";

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_itinerary, container, false);
        getDialog().setTitle(title);

        titleEdit = (EditText) v.findViewById(R.id.titleEdit_itin);
        assignedEdit = (EditText) v.findViewById(R.id.assignedEdit);
        tipEdit = (EditText) v.findViewById(R.id.tipEdit);

        titleEdit.setText(title);
        assignedEdit.setText(assigned);
        tipEdit.setText(NumberFormat.getCurrencyInstance().format(tip));

        if(position >= 0) {
            (v.findViewById(R.id.bu_itin_delete)).setVisibility(View.VISIBLE);
            (v.findViewById(R.id.bu_itin_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ItineraryActivity) getActivity()).deleteItineraryItem(position);
                }

            });
        }



        (v.findViewById(R.id.bu_itin_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ((ItineraryActivity)
                        getActivity()).updateItinerary(titleEdit.getText().toString(),
                        assignedEdit.getText().toString(), position,
                    Double.parseDouble(tipEdit.getText().toString().substring(1)), time);
            }
        });

        (v.findViewById(R.id.itinerary_time_bu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar currentTime = Calendar.getInstance();
                time = currentTime.getTime();
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        currentTime.set(Calendar.MINUTE, minute);
                        time = currentTime.getTime();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                        ((Button) getDialog().findViewById(R.id.itinerary_time_bu)).setText(
                                simpleDateFormat.format(time));
                    }
                },
                        currentTime.get(Calendar.HOUR_OF_DAY),
                        currentTime.get(Calendar.MINUTE), true);

                timePickerDialog.setTitle("Set the Time");
                timePickerDialog.show();
            }
        });

        return v;
    }


}
