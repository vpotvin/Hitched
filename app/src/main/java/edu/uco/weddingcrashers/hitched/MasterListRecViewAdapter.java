package edu.uco.weddingcrashers.hitched;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
    private Context context;
    private int day;
    private int month;
    private int year;
    private Calendar weddingDate;
    //private LayoutInflater inflater;

    public MasterListRecViewAdapter() {
        ParseQuery<MasterListItem> query = ParseQuery.getQuery(MasterListItem.class);

        query.findInBackground(new FindCallback<MasterListItem>() {
            @Override
            public void done(List<MasterListItem> objects, ParseException e) {
                theList = new ArrayList<Object>(objects);
                Collections.sort(theList, new theListComparator());
                notifyDataSetChanged();
            }
        });
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("User");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    day=scoreList.get(0).getInt("day");
                    month=scoreList.get(0).getInt("month");
                    year=scoreList.get(0).getInt("year");
                    weddingDate.set(year, month - 1, day, 0, 0);
                    addLabels();

                } else {

                }
            }
        });

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

    public void addLabels()
    {
        Calendar calendar;
        Calendar calendar2;

        calendar = weddingDate;
        calendar2 = weddingDate;

        calendar.add(Calendar.MONTH, -16);
        calendar2.add(Calendar.MONTH, -9);

        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Sixteen to Nine Months Before "));
        calendar.add(Calendar.MONTH, 7);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Eight Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Seven to Six Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Five to Four Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 2);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Three Months Before "));
        calendar.add(Calendar.MONTH, 2);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Two Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.MONTH, 1);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "One Months Before "));
        calendar.add(Calendar.MONTH, 1);
        calendar2.add(Calendar.WEEK_OF_MONTH, 3);
        theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Week Of Wedding "));
        Collections.sort(theList, new theListComparator());
        notifyDataSetChanged();
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

    private void configureListItem(ListItem holder, final int position) {
        final MasterListItem currentItem = (MasterListItem) theList.get(position);
        holder.title.setText(currentItem.getTitle());
        if(currentItem.getDueDate() != null)
        {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String reportDate = df.format(currentItem.getDueDate());
            holder.dueDate.setText(reportDate);
        }
        else
        {
            holder.dueDate.setText("");
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

        holder.guests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasterListAssignementActivity.class);
                intent.putExtra("task", currentItem);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                theList.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        if(theList != null)
        {
            return theList.size();
        }
        else
        {
            return 0;
        }

    }

    public void updateTheList() {
        ParseQuery<MasterListItem> query = ParseQuery.getQuery(MasterListItem.class);

        query.findInBackground(new FindCallback<MasterListItem>() {
            @Override
            public void done(List<MasterListItem> objects, ParseException e) {
                theList = new ArrayList<Object>(objects);
                addLabels();
                notifyDataSetChanged();
            }
        });
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
        private Button delete;

        public ListItem(View itemView){
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.masterListTitle);
            this.dueDate = (TextView) itemView.findViewById(R.id.masterListDueDate);
            this.completedDate = (TextView) itemView.findViewById(R.id.masterListCompletedDate);
            this.notes = (TextView) itemView.findViewById(R.id.masterListNotes);
            this.completed = (CheckBox) itemView.findViewById(R.id.masterListCompleted);
            this.guests = (Button) itemView.findViewById(R.id.assignementsButton);
            this.delete = (Button) itemView.findViewById(R.id.masterListDeleteButton);
            context = itemView.getContext();


        }
    }

    public class DateLabelHolder extends RecyclerView.ViewHolder{
        private TextView dateLabel;

        public DateLabelHolder(View itemView){
            super(itemView);
            this.dateLabel = (TextView) itemView.findViewById(R.id.masterListDateLabel);
        }

    }


    static class theListComparator implements Comparator<Object>
    {
        public int compare(Object c1, Object c2)
        {
            if(c1 instanceof  MasterListItem && c2 instanceof  MasterListItem)
            {
                if(((MasterListItem) c1).getDueDate().before(((MasterListItem) c2).getDueDate()))
                {
                    return -1;
                }
                else if(((MasterListItem) c1).getDueDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else if(c1 instanceof MasterListItem && c2 instanceof DateLabel)
            {
                if( ((MasterListItem) c1).getDueDate().after(((DateLabel) c2).getStartDate()) && ((MasterListItem) c1).getDueDate().before(((DateLabel) c2).getEndDate()) )
                {
                    return 1;
                }
                else if(((MasterListItem) c1).getDueDate().after(((DateLabel) c2).getEndDate()))
                {
                    return 1;
                }
                else if(((MasterListItem) c1).getDueDate().before(((DateLabel) c2).getStartDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else if(c1 instanceof  DateLabel && c2 instanceof  MasterListItem)
            {
                if(((DateLabel) c1).getStartDate().before(((MasterListItem) c2).getDueDate()) && ((DateLabel) c1).getEndDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else if(((DateLabel) c1).getStartDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return 1;
                }
                else if(((DateLabel) c1).getEndDate().before(((MasterListItem) c2).getDueDate()))
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                if(((DateLabel) c1).getStartDate().before(((DateLabel) c2).getStartDate()))
                {
                    return -1;
                }
                else if(((DateLabel) c1).getStartDate().after(((DateLabel) c2).getStartDate()))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }



}
