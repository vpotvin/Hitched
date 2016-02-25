package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    EditText titleEdit;
    EditText assignedEdit;

    public interface ItineraryUpdateListener {
        void updateItinerary(String title, String assigned, int position);
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

            if(title == null) title = "Title";
            if(assigned == null) assigned = "Assigned";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_itinerary, container, false);
        getDialog().setTitle(title);

        titleEdit = (EditText) v.findViewById(R.id.titleEdit_itin);
        assignedEdit = (EditText) v.findViewById(R.id.assignedEdit);

        titleEdit.setText(title);
        assignedEdit.setText(assigned);

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
                        assignedEdit.getText().toString(), position);
            }
        });

        return v;
    }


}
