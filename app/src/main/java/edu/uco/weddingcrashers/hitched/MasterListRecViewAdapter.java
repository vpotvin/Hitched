package edu.uco.weddingcrashers.hitched;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by drenf on 2/2/2016.
 */
public class MasterListRecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    TextView title;
    TextView dueDate;
    TextView completedDate;
    TextView notes;
    CheckBox completed;
    private List<Object> theList;
    private final int TASK = 0, LABEL = 1;
    //private LayoutInflater inflater;

    public MasterListRecViewAdapter(List<Object> theList) {
        this.theList = theList;
        //inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (theList.get(position) instanceof MasterListItem) {
            return TASK;
        } else if (theList.get(position) instanceof DateLabel) {
            return LABEL;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TASK:
                View v1 = inflater.inflate(R.layout.master_list_item, parent, false);
                viewHolder = new ListItem(v1);
                break;
            case LABEL:
                View v2 = inflater.inflate(R.layout.master_list_date_label, parent, false);
                viewHolder = new DateLabelHolder(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.master_list_date_label, parent, false);
                viewHolder = new DateLabelHolder(v3);

        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        switch (holder.getItemViewType()) {
            case TASK:
                ListItem vh1 = (ListItem) holder;
                configureListItem(vh1, position);
                break;
            case LABEL:
                DateLabelHolder vh2 = (DateLabelHolder) holder;
                configureDateLabel(vh2, position);
                break;
            default:
                ListItem vh3 = (ListItem) holder;
                configureListItem(vh3, position);
                break;
        }


    }

    private void configureDateLabel(DateLabelHolder holder, int position) {
        DateLabel label = (DateLabel) theList.get(position);
        holder.dateLabel.setText(label.getTitle());
    }

    private void configureListItem(ListItem holder, int position) {
        final MasterListItem currentItem = (MasterListItem) theList.get(position);
        holder.title.setText(currentItem.getTitle());
        if(currentItem.getDueDate() != null)
        {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String reportDate = df.format(currentItem.getDueDate());
            holder.dueDate.setText(reportDate);
        }
        if(currentItem.getCompletedDate() != null)
        {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String reportDate = df.format(currentItem.getCompletedDate());
            holder.completedDate.setText(reportDate);
        }
        else
        {
            holder.completedDate.setText("");
        }

        holder.notes.setText(currentItem.getNotes());
        holder.completed.setChecked(currentItem.isCompleted());

        holder.guests.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("task",currentItem);
            }
        });
    }


    @Override
    public int getItemCount() {
        return theList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ListItem extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView dueDate;
        private TextView completedDate;
        private TextView notes;
        private CheckBox completed;
        private Button guests;
        public ListItem(View itemView){
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.masterListTitle);
            this.dueDate = (TextView) itemView.findViewById(R.id.masterListDueDate);
            this.completedDate = (TextView) itemView.findViewById(R.id.masterListCompletedDate);
            this.notes = (TextView) itemView.findViewById(R.id.masterListNotes);
            this.completed = (CheckBox) itemView.findViewById(R.id.masterListCompleted);
            this.guests = (Button) itemView.findViewById(R.id.assignementsButton);

        }
    }

    public class DateLabelHolder extends RecyclerView.ViewHolder{
        private TextView dateLabel;

        public DateLabelHolder(View itemView){
            super(itemView);
            this.dateLabel = (TextView) itemView.findViewById(R.id.masterListDateLabel);
        }

    }



}
