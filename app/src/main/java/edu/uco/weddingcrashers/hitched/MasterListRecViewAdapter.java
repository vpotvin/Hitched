package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    private Calendar weddingDate = Calendar.getInstance() ;
    private boolean list1 = false;
    private boolean list2 = false;
    //private LayoutInflater inflater;

    public MasterListRecViewAdapter() {
        ParseQuery<MasterListItem> query = ParseQuery.getQuery(MasterListItem.class);

        query.findInBackground(new FindCallback<MasterListItem>() {
            @Override
            public void done(List<MasterListItem> objects, ParseException e) {
                theList = new ArrayList<Object>(objects);
                Collections.sort(theList, new theListComparator());
                notifyDataSetChanged();
                list1 = true;
                addLabels();
            }
        });
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("WeddingDate");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null && scoreList.size() != 0) {
                    ParseObject date = scoreList.get(0);
                    day = Integer.parseInt(date.getString("Day"));
                    month = Integer.parseInt(date.getString("Month"));
                    year = Integer.parseInt(date.getString("Year"));
                    weddingDate.set(year, month - 1, day, 0, 0);
                    list2 = true;
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
        if( list1 && list2)
        {
            Calendar calendar;
            Calendar calendar2;

            calendar = (Calendar)weddingDate.clone();
            calendar2 = (Calendar)weddingDate.clone();
            //old date new date
            calendar.add(Calendar.WEEK_OF_MONTH,-1);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Week Of Wedding "));
            calendar.add(Calendar.WEEK_OF_MONTH,-3);
            calendar2.add(Calendar.WEEK_OF_MONTH,-1);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "One Months Before "));
            calendar.add(Calendar.MONTH,-1);
            calendar2.add(Calendar.WEEK_OF_MONTH,-3);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Two Months Before "));
            calendar.add(Calendar.MONTH,-1);
            calendar2.add(Calendar.MONTH,-1);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Three Months Before "));
            calendar.add(Calendar.MONTH,-2);
            calendar2.add(Calendar.MONTH,-1);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Five to Four Months Before "));
            calendar.add(Calendar.MONTH,-2);
            calendar2.add(Calendar.MONTH,-2);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Seven to Six Months Before "));
            calendar.add(Calendar.MONTH, -1);
            calendar2.add(Calendar.MONTH, -2);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Eight Months Before "));
            calendar.add(Calendar.MONTH, -8);
            calendar2.add(Calendar.MONTH, -1);
            theList.add(new DateLabel(calendar.getTime(), calendar2.getTime(), "Sixteen to Nine Months Before "));
            //calendar.add(Calendar.MONTH, -15);
            //calendar2.add(Calendar.MONTH, -8);
            /*
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
            */
            Collections.sort(theList, new theListComparator());
            notifyDataSetChanged();
        }

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
        holder.dateLabel.setText(label.getTitle().trim());
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

        colorCard(holder, position);

        holder.guests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasterListAssignementActivity.class);
                intent.putExtra("task", (MasterListItem) theList.get(position));
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterListItem toDelete = (MasterListItem)theList.get(position);
                toDelete.deleteEventually();
                theList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterListItem toEdit = (MasterListItem) theList.get(position);
                DialogFragment newFragment = new MasterListEditItemInput();
                Bundle args = new Bundle();
                args.putInt("pos", position);
                args.putSerializable("item", toEdit);
                newFragment.setArguments(args);
                FragmentManager manager = ((Activity) context).getFragmentManager();
                newFragment.show(manager, "Edit Entry");
            }
        });
    }

    private void colorCard(ListItem holder, int position) {
        MasterListItem current = (MasterListItem)theList.get(position);
        Date dueDate1 = current.getDueDate();
        Calendar dueDate = Calendar.getInstance();
        dueDate.setTime(dueDate1);
        Calendar today = Calendar.getInstance();
        //today.add(Calendar.MONTH, 10);
        long days = UtilityFunctions.daysBetween(today, dueDate);
        if(!(current.isCompleted()))
        {
            if(days >= 30)
            {
                holder.title.setBackgroundColor(Color.GREEN);
            }
            else if(days < 30 && days > -1)
            {
                holder.title.setBackgroundColor(Color.YELLOW);
            }
            else
            {
                holder.title.setBackgroundColor(Color.RED);
            }
        }
        else
        {
            holder.title.setBackgroundColor(Color.WHITE);
        }
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

    public void editTheList(int pos, String notes, String title, Date dueDate, boolean completed)
    {
        MasterListItem toEdit = (MasterListItem) theList.get(pos);
        toEdit.setDueDate(dueDate);
        toEdit.setNotes(notes);
        toEdit.setTitle(title);
        toEdit.setCompleted(completed);
        toEdit.setDistanceFromWeddingDay("static");
        toEdit.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Collections.sort(theList, new theListComparator());
                    notifyDataSetChanged();
                } else {

                }
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
        private Button edit;

        public ListItem(View itemView){
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.masterListTitle);
            this.dueDate = (TextView) itemView.findViewById(R.id.masterListDueDate);
            this.completedDate = (TextView) itemView.findViewById(R.id.masterListCompletedDate);
            this.notes = (TextView) itemView.findViewById(R.id.masterListNotes);
            this.completed = (CheckBox) itemView.findViewById(R.id.masterListCompleted);
            this.guests = (Button) itemView.findViewById(R.id.assignementsButton);
            this.delete = (Button) itemView.findViewById(R.id.masterListDeleteButton);
            this.edit = (Button) itemView.findViewById(R.id.masterListEditButton);
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
                if(((MasterListItem) c1).getDueDate() == null && ((MasterListItem) c2).getDueDate() == null)
                {
                    return 0;
                }
                else if(((MasterListItem) c1).getDueDate() == null)
                {
                    return 0;
                }
                else if(((MasterListItem) c2).getDueDate() == null)
                {
                    return 0;
                }

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
                    return -1;
                }
                else
                {
                    return 0;
                }
            }//pos c1 is bigger neg c2 is bigger
            else if(c1 instanceof  DateLabel && c2 instanceof  MasterListItem)
            {
                if(((DateLabel) c1).getStartDate().before(((MasterListItem) c2).getDueDate()) && ((DateLabel) c1).getEndDate().after(((MasterListItem) c2).getDueDate()))
                {
                    return -1;
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
