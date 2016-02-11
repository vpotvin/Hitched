package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by drenf on 2/2/2016.
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.MyViewHolder> {
    TextView title;
    TextView dueDate;
    TextView completedDate;
    TextView notes;
    CheckBox completed;
    private ArrayList<MasterListItem> theList;
    //private LayoutInflater inflater;

    public RecViewAdapter(ArrayList<MasterListItem> theList) {
        this.theList = theList;
        //inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.master_list_item,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*
        title = holder.title;
        dueDate = holder.dueDate;
        completedDate = holder.completedDate;
        notes = holder.notes;
        completed = holder.completed;

        title.setText(theList.get(position).getTitle());
        dueDate.setText(theList.get(position).getDueDate().toString());
        completedDate.setText(theList.get(position).getCompletedDate().toString());
        notes.setText(theList.get(position).getNotes());
        completed.setChecked(theList.get(position).isCompleted());
        */
        holder.title.setText(theList.get(position).getTitle());
        if(theList.get(position).getDueDate() != null)
        {
            holder.dueDate.setText(theList.get(position).getDueDate().toString());
        }
        if(theList.get(position).getCompletedDate() != null)
        {
            holder.completedDate.setText(theList.get(position).getCompletedDate().toString());
        }

        holder.notes.setText(theList.get(position).getNotes());
        holder.completed.setChecked(theList.get(position).isCompleted());
    }



    @Override
    public int getItemCount() {
        return theList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView dueDate;
        private TextView completedDate;
        private TextView notes;
        private CheckBox completed;
        public MyViewHolder(View itemView){
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.masterListTitle);
            this.dueDate = (TextView) itemView.findViewById(R.id.masterListDueDate);
            this.completedDate = (TextView) itemView.findViewById(R.id.masterListCompletedDate);
            this.notes = (TextView) itemView.findViewById(R.id.masterListNotes);
            this.completed = (CheckBox) itemView.findViewById(R.id.masterListCompleted);
        }
    }



}