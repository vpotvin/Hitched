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
public class RecViewAdapter extends RecyclerView.Adapter {

    private ArrayList<MasterListItem> theList;
    private LayoutInflater inflater;

    public RecViewAdapter(Context context,ArrayList<MasterListItem> theList) {
        this.theList = theList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.master_list_item,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //MasterListItem current = theList.get(position);

        MyViewHolder holder1 = (MyViewHolder) holder;
        TextView title = holder1.title;
        TextView dueDate = holder1.dueDate;
        TextView completedDate = holder1.completedDate;
        TextView notes = holder1.notes;
        CheckBox completed = holder1.completed;

        title.setText(theList.get(position).getTitle());
        dueDate.setText(theList.get(position).getDueDate().toString());
        completedDate.setText(theList.get(position).getCompletedDate().toString());
        notes.setText(theList.get(position).getNotes());
        completed.setChecked(theList.get(position).isCompleted());
    }

    @Override
    public int getItemCount() {
        return theList.size();
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
