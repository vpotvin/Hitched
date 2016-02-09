package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;


public class BudgetUpdateFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "budget";
    private static final String ARG_PARAM2 = "used";

    private double budget;
    private double used;

    EditText budgetText;
    EditText usedText;

    public interface BudgetUpdateListener {
        void updateBudgetAndUsed(double budget, double used);
    }

    // TODO: Rename and change types and number of parameters
    public static BudgetUpdateFragment newInstance(double budget, double used) {
        BudgetUpdateFragment fragment = new BudgetUpdateFragment();
        Bundle args = new Bundle();
        args.putDouble("budget", budget);
        args.putDouble("used", used);
        fragment.setArguments(args);
        return fragment;
    }

    public BudgetUpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            budget = getArguments().getDouble(ARG_PARAM1);
            used = getArguments().getDouble(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_budget_update, container, false);

        budgetText = (EditText) v.findViewById(R.id.budgetValueEdit);
        usedText = (EditText) v.findViewById(R.id.budgetUsedEdit);

        budgetText.setText(NumberFormat.getNumberInstance().format(budget));
        usedText.setText(NumberFormat.getNumberInstance().format(used));
        ((Button) v.findViewById(R.id.updateBudgetBtnDialog)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rawText = budgetText.getText().toString();
                        rawText = rawText.replaceAll("[^0-9.]", "");
                        budget = Double.valueOf(rawText);
                        rawText = usedText.getText().toString();
                        rawText = rawText.replaceAll("[^0-9.]", "");
                        used = Double.valueOf(rawText);
                        BudgetUpdateListener listener = (BudgetUpdateListener) getActivity();
                        listener.updateBudgetAndUsed(budget, used);
                    }
                }
        );
        return v;
    }





}
