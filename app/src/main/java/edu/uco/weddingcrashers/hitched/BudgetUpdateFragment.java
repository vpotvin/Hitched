package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.NumberFormat;


public class BudgetUpdateFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BUDGET_PARAM = "budget";
    private static final String USED_PARAM = "used";
    private static final String POS_PARAM = "position";
    private static final String TITLE_PARAM = "title";
    protected static final String NEW_FLAG = "New Item";

    private double budget;
    private double used;
    private int position;
    private String title;

    EditText budgetText;
    EditText usedText;
    EditText titleEdit;

    public interface BudgetUpdateListener {
        void updateBudgetAndUsed(double budget, double used, int position);
    }


    public static BudgetUpdateFragment newInstance(double budget, double used, int position,
                                                   String title) {
        BudgetUpdateFragment fragment = new BudgetUpdateFragment();
        Bundle args = new Bundle();
        args.putDouble(BUDGET_PARAM, budget);
        args.putDouble(USED_PARAM, used);
        args.putInt(POS_PARAM, position);
        args.putString(TITLE_PARAM, title);
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
            budget = getArguments().getDouble(BUDGET_PARAM);
            used = getArguments().getDouble(USED_PARAM);
            position = getArguments().getInt(POS_PARAM);
            title = getArguments().getString(TITLE_PARAM);
            if(title == null) title = "Budget";

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_budget_update, container, false);
        getDialog().setTitle(title);


        budgetText = (EditText) v.findViewById(R.id.budgetValueEdit);
        usedText = (EditText) v.findViewById(R.id.budgetUsedEdit);

        budgetText.setText(NumberFormat.getNumberInstance().format(budget));
        usedText.setText(NumberFormat.getNumberInstance().format(used));


        if(title.equals(NEW_FLAG)) {
            (v.findViewById(R.id.titleBULabel)).setVisibility(View.VISIBLE);
            titleEdit = (EditText) v.findViewById(R.id.titleBUEdit);
            titleEdit.setVisibility(View.VISIBLE);
        }
        (v.findViewById(R.id.updateBudgetBtnDialog)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rawText = budgetText.getText().toString();
                        rawText = rawText.replaceAll("[^0-9.]", "");
                        budget = Double.valueOf(rawText);
                        rawText = usedText.getText().toString();
                        rawText = rawText.replaceAll("[^0-9.]", "");
                        used = Double.valueOf(rawText);
                        if (NEW_FLAG.equals(title)) {
                            title = titleEdit.getText().toString();
                            BudgetItem item = new BudgetItem(title, budget, used);
                            ((BudgetActivity) getActivity()).addItem(item);
                            ((BudgetActivity) getActivity()).removeDialog();
                        } else {
                            BudgetUpdateListener listener = (BudgetUpdateListener) getActivity();
                            listener.updateBudgetAndUsed(budget, used, position);
                        }
                    }
                }
        );

        if(position > -1) {
            (v.findViewById(R.id.updateBudgetBtnDialogDelete)).setVisibility(View.VISIBLE);
            (v.findViewById(R.id.updateBudgetBtnDialogDelete)).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((BudgetActivity) getActivity()).deleteItem(position);
                            ((BudgetActivity) getActivity()).removeDialog();
                        }
                    });
        }


        return v;
    }





}
